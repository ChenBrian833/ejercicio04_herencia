import java.util.List;

/**
 * Clase que representa una Poción que cura puntos de vida.
 */
public class Pocion extends Item {
    private int cantidadCuracion;
    
    /**
     * Constructor de Poción
     * @param nombre Nombre de la poción
     * @param descripcion Descripción de la poción
     * @param cantidad Cantidad inicial
     * @param curacion Cantidad de HP que cura
     */
    public Pocion(String nombre, String descripcion, int cantidad, int curacion) {
        super(nombre, descripcion, cantidad);
        this.cantidadCuracion = curacion;
    }
    
    /**
     * Usa la poción en los objetivos para curarlos
     * @param objetivos Lista de combatientes a curar
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
            int vidaAntes = objetivo.getPuntosVida();
            objetivo.curar(cantidadCuracion);
            int vidaCurada = objetivo.getPuntosVida() - vidaAntes;
            resultado.append(objetivo.getNombre())
                    .append(" recupera ")
                    .append(vidaCurada)
                    .append(" HP. ");
        }
        
        consumir();
        return resultado.toString();
    }
    
    public int getCantidadCuracion() {
        return cantidadCuracion;
    }
}