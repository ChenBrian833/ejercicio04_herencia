public abstract class Enemigo extends Combatiente {
    
    /**
     * Constructor de Enemigo
     * @param nombre Nombre del enemigo
     * @param vida Puntos de vida iniciales
     * @param ataque Poder de ataque base
     */
    public Enemigo(String nombre, int vida, int ataque) {
        super(nombre, vida, ataque);
    }
    
    /**
     * Método abstracto para habilidad especial del enemigo
     * @param objetivo Combatiente objetivo
     * @return Descripción de la acción
     */
    public abstract String habilidadEspecial(Combatiente objetivo);
    
    @Override
    public String tomarTurno() {
        // Este método será manejado por el controlador
        return "Turno de " + nombre;
    }
}