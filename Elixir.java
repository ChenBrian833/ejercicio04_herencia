import java.util.List;

public class Elixir extends Item {
    private int aumentoAtaque;
    
    /**
     * Constructor de Elixir
     * @param nombre Nombre del elixir
     * @param descripcion Descripción del elixir
     * @param cantidad Cantidad inicial
     * @param aumento Cantidad de ataque que aumenta
     */
    public Elixir(String nombre, String descripcion, int cantidad, int aumento) {
        super(nombre, descripcion, cantidad);
        this.aumentoAtaque = aumento;
    }
    
    /**
     * Usa el elixir en los objetivos para aumentar su ataque
     * @param objetivos Lista de combatientes a potenciar
     * @return Descripción del efecto
     */
    @Override
    public String usar(List<Combatiente> objetivos) {
        if (cantidad <= 0) {
            return "No quedan " + nombre + " disponibles!";
        }
        
        StringBuilder resultado = new StringBuilder();
        resultado.append("Se usa ").append(nombre).append("! ");
        
        for (Combatiente objetivo : objetivos) {
            objetivo.aumentarAtaque(aumentoAtaque);
            resultado.append(objetivo.getNombre())
                    .append(" aumenta su ataque en ")
                    .append(aumentoAtaque)
                    .append(" puntos. ");
        }
        
        consumir();
        return resultado.toString();
    }
    
    public int getAumentoAtaque() {
        return aumentoAtaque;
    }
}