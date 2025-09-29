import java.util.List;

/**
 * ítem que puede usar el jugador.
 * Los ítems tienen cantidad limitada y se consumen al usarse.
 */
public abstract class Item {
    protected String nombre;
    protected String descripcion;
    protected int cantidad;
    
    /**
     * Constructor de Item
     * @param nombre Nombre del ítem
     * @param descripcion Descripción del ítem
     * @param cantidad Cantidad inicial
     */
    public Item(String nombre, String descripcion, int cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }
    
    // Getters
    public String getNombre() {
        return nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    /**
     * Usa el ítem sobre uno o más combatientes
     * @param objetivos Lista de combatientes objetivo
     * @return Descripción del efecto
     */
    public abstract String usar(List<Combatiente> objetivos);
    
    /**
     * Reduce la cantidad del ítem en 1
     */
    protected void consumir() {
        if (cantidad > 0) {
            cantidad--;
        }
    }
    
    @Override
    public String toString() {
        return nombre + " (x" + cantidad + "): " + descripcion;
    }
}