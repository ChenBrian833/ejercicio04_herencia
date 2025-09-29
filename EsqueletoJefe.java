public class EsqueletoJefe extends Esqueleto {
    
    /**
     * Constructor de Esqueleto Jefe
     * @param nombre Nombre del esqueleto jefe
     */
    public EsqueletoJefe(String nombre) {
        super(nombre);
        // Los jefes son más poderosos
        this.puntosVida = 120;
        this.puntosVidaMax = 120;
        this.poderAtaque = 20;
    }
    
    /**
     * Habilidad especial mejorada: Esquive Perfecto
     * El siguiente ataque será completamente anulado
     */
    @Override
    public String habilidadEspecial(Combatiente objetivo) {
        super.habilidadEspecial(objetivo);
        return nombre + " activa Esquive Perfecto! El próximo ataque será muy reducido!";
    }
    
    /**
     * Habilidad especial adicional: Maldición
     * Reduce el ataque del objetivo
     * @param objetivo Combatiente a maldecir
     * @return Descripción de la acción
     */
    public String maldicion(Combatiente objetivo) {
        int reduccion = 5;
        int ataqueActual = objetivo.getPoderAtaque();
        objetivo.setPoderAtaque(Math.max(1, ataqueActual - reduccion));
        
        return nombre + " lanza una Maldición sobre " + objetivo.getNombre() + 
               "! Su ataque se reduce en " + reduccion + " puntos!";
    }
    
    /**
     * Los jefes reciben menos daño (25% de reducción)
     * Además, si está esquivando, reduce aún más el daño
     */
    @Override
    public void recibirDanio(int danio) {
        if (isEsquivando()) {
            danio = danio / 3; // Esquive más efectivo para el jefe
        } else {
            danio = (int)(danio * 0.75); // Reducción base del jefe
        }
        // Llamar directamente a la versión de Combatiente
        this.puntosVida = Math.max(0, this.puntosVida - danio);
    }
    
    @Override
    public String getMensajeInicio() {
        return "¡¡" + nombre + " EL SEÑOR DE LOS HUESOS se alza ante ti!!";
    }
    
    @Override
    public String getMensajeMuerte() {
        return nombre + " se desintegra lentamente: 'Volveré... desde... el inframundo...'";
    }
}