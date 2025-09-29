public class GoblinJefe extends Goblin {
    
    /**
     * Constructor de Goblin Jefe
     * @param nombre Nombre del goblin jefe
     */
    public GoblinJefe(String nombre) {
        super(nombre);
        // Los jefes son más poderosos
        this.puntosVida = 100;
        this.puntosVidaMax = 100;
        this.poderAtaque = 18;
    }
    
    /**
     * Habilidad especial mejorada: Robo de Vida Feroz
     * Causa más daño y se cura más que el Goblin normal
     */
    @Override
    public String habilidadEspecial(Combatiente objetivo) {
        int danio = (int)(this.poderAtaque * 1.5);
        int vidaAntes = objetivo.getPuntosVida();
        objetivo.recibirDanio(danio);
        int danioReal = vidaAntes - objetivo.getPuntosVida();
        int curacion = (int)(danioReal * 0.75);
        this.curar(curacion);
        
        return nombre + " usa Robo de Vida Feroz en " + objetivo.getNombre() + 
               "! Causa " + danioReal + " de daño y se cura " + curacion + " HP";
    }
    
    /**
     * Habilidad especial adicional: Furia Goblin
     * Aumenta su ataque permanentemente
     * @return Descripción de la acción
     */
    public String furiaGoblin() {
        int aumento = 5;
        this.aumentarAtaque(aumento);
        return nombre + " entra en FURIA! Su ataque aumenta " + aumento + " puntos!";
    }
    
    /**
     * Los jefes reciben menos daño (20% de reducción)
     */
    @Override
    public void recibirDanio(int danio) {
        int danioReducido = (int)(danio * 0.8);
        super.recibirDanio(danioReducido);
    }
    
    @Override
    public String getMensajeInicio() {
        return "¡¡" + nombre + " EL GRAN JEFE GOBLIN aparece rugiendo!!";
    }
    
    @Override
    public String getMensajeMuerte() {
        return nombre + " cae derrotado: '¡NOOOO! ¡Imposible!'";
    }
}