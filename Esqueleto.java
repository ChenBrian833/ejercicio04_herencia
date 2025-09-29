public class Esqueleto extends Enemigo {
    private boolean esquivando;
    
    /**
     * Constructor de Esqueleto
     * @param nombre Nombre del esqueleto
     */
    public Esqueleto(String nombre) {
        super(nombre, 60, 12);
        this.esquivando = false;
    }
    
    /**
     * Habilidad especial: Preparar Esquive
     * El siguiente ataque que reciba será reducido
     * @param objetivo No se usa, pero se mantiene por la firma del método
     * @return Descripción de la acción
     */
    @Override
    public String habilidadEspecial(Combatiente objetivo) {
        this.esquivando = true;
        return nombre + " se prepara para esquivar el próximo ataque!";
    }
    
    /**
     * Recibe daño, pero si está esquivando, reduce el daño a la mitad
     */
    @Override
    public void recibirDanio(int danio) {
        if (esquivando) {
            danio = danio / 2;
            this.esquivando = false;
        }
        super.recibirDanio(danio);
    }
    
    public boolean isEsquivando() {
        return esquivando;
    }
    
    @Override
    public String getMensajeInicio() {
        return nombre + " el Esqueleto emerge de las sombras, sus huesos crujen...";
    }
    
    @Override
    public String getMensajeMuerte() {
        return nombre + " se desmorona en un montón de huesos...";
    }
}