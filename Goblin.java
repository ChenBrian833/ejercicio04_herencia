public class Goblin extends Enemigo {
    
    /**
     * Constructor de Goblin
     * @param nombre Nombre del goblin
     */
    public Goblin(String nombre) {
        super(nombre, 50, 10);
    }
    
    /**
     * Habilidad especial: Robo de Vida
     * Ataca al objetivo y se cura la mitad del daño causado
     * @param objetivo Combatiente objetivo
     * @return Descripción de la acción
     */
    @Override
    public String habilidadEspecial(Combatiente objetivo) {
        int danio = this.poderAtaque;
        int vidaAntes = objetivo.getPuntosVida();
        objetivo.recibirDanio(danio);
        int danioReal = vidaAntes - objetivo.getPuntosVida();
        int curacion = danioReal / 2;
        this.curar(curacion);
        
        return nombre + " usa Robo de Vida en " + objetivo.getNombre() + 
               "! Causa " + danioReal + " de daño y se cura " + curacion + " HP";
    }
    
    @Override
    public String getMensajeInicio() {
        return "¡" + nombre + " el Goblin aparece con una risa maligna!";
    }
    
    @Override
    public String getMensajeMuerte() {
        return nombre + " grita y desaparece en una nube de humo...";
    }
}