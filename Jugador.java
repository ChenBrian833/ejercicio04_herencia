import java.util.ArrayList;
import java.util.List;

public class Jugador extends Combatiente {
    private List<Item> items;
    private int capacidadItems;
    
    /**
     * Constructor de Jugador
     * @param nombre Nombre del jugador
     * @param vida Puntos de vida iniciales
     * @param ataque Poder de ataque base
     * @param capacidadItems Cantidad máxima de ítems diferentes
     */
    public Jugador(String nombre, int vida, int ataque, int capacidadItems) {
        super(nombre, vida, ataque);
        this.capacidadItems = capacidadItems;
        this.items = new ArrayList<>();
    }
    
    // Getters
    public List<Item> getItems() {
        return items;
    }
    
    public int getCapacidadItems() {
        return capacidadItems;
    }
    
    /**
     * Agrega un ítem al inventario del jugador
     * @param item Ítem a agregar
     * @return true si se pudo agregar
     */
    public boolean agregarItem(Item item) {
        if (items.size() < capacidadItems) {
            items.add(item);
            return true;
        }
        return false;
    }
    
    /**
     * Usa un ítem sobre uno o más combatientes
     * @param item Ítem a usar
     * @param objetivos Lista de combatientes objetivo
     * @return Descripción de la acción
     */
    public String usarItem(Item item, List<Combatiente> objetivos) {
        if (items.contains(item) && item.getCantidad() > 0) {
            String resultado = item.usar(objetivos);
            if (item.getCantidad() == 0) {
                items.remove(item);
            }
            return resultado;
        }
        return nombre + " no puede usar " + item.getNombre();
    }
    
    /**
     * Habilidad especial del jugador: usar ítems
     * @param item Ítem a usar
     * @param objetivos Combatientes objetivo
     * @return Descripción de la acción
     */
    public String habilidadEspecial(Item item, List<Combatiente> objetivos) {
        return usarItem(item, objetivos);
    }
    
    @Override
    public String getMensajeInicio() {
        return nombre + " entra en combate, listo para la batalla!";
    }
    
    @Override
    public String getMensajeMuerte() {
        return nombre + " ha caído en batalla...";
    }
    
    @Override
    public String tomarTurno() {
        // Este método será manejado por el controlador con menús
        return "Turno de " + nombre;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" | Items: ");
        if (items.isEmpty()) {
            sb.append("ninguno");
        } else {
            for (int i = 0; i < items.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(items.get(i).getNombre()).append("(x")
                  .append(items.get(i).getCantidad()).append(")");
            }
        }
        return sb.toString();
    }
}