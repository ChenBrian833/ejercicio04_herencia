/**
 * Clase para todos los combatientes en el juego.
 * Implementa las características comunes de jugadores y enemigos.
 */
public abstract class Combatiente {
    protected String nombre;
    protected int puntosVida;
    protected int puntosVidaMax;
    protected int poderAtaque;
    
    /**
     * Constructor de Combatiente
     * @param nombre Nombre del combatiente
     * @param vida Puntos de vida iniciales
     * @param ataque Poder de ataque base
     */
    public Combatiente(String nombre, int vida, int ataque) {
        this.nombre = nombre;
        this.puntosVida = vida;
        this.puntosVidaMax = vida;
        this.poderAtaque = ataque;
    }
    
    // Getters
    public String getNombre() {
        return nombre;
    }
    
    public int getPuntosVida() {
        return puntosVida;
    }
    
    public int getPuntosVidaMax() {
        return puntosVidaMax;
    }
    
    public int getPoderAtaque() {
        return poderAtaque;
    }
    
    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setPuntosVida(int puntosVida) {
        this.puntosVida = Math.max(0, puntosVida);
    }
    
    public void setPoderAtaque(int poderAtaque) {
        this.poderAtaque = poderAtaque;
    }
    
    /**
     * Método abstracto para desplegar mensaje de inicio
     */
    public abstract String getMensajeInicio();
    
    /**
     * Método abstracto para desplegar mensaje de muerte
     */
    public abstract String getMensajeMuerte();
    
    /**
     * Método abstracto para tomar turno
     */
    public abstract String tomarTurno();
    
    /**
     * Ataca a un combatiente objetivo
     * @param objetivo Combatiente a atacar
     * @return Descripción de la acción
     */
    public String atacar(Combatiente objetivo) {
        int danio = this.poderAtaque;
        objetivo.recibirDanio(danio);
        return this.nombre + " ataca a " + objetivo.getNombre() + 
               " causando " + danio + " de daño";
    }
    
    /**
     * Recibe daño y reduce puntos de vida
     * @param danio Cantidad de daño recibido
     */
    public void recibirDanio(int danio) {
        this.puntosVida = Math.max(0, this.puntosVida - danio);
    }
    
    /**
     * Verifica si el combatiente está vivo
     * @return true si tiene puntos de vida
     */
    public boolean estaVivo() {
        return this.puntosVida > 0;
    }
    
    /**
     * Cura puntos de vida sin exceder el máximo
     * @param cantidad Cantidad de vida a recuperar
     */
    public void curar(int cantidad) {
        this.puntosVida = Math.min(this.puntosVidaMax, this.puntosVida + cantidad);
    }
    
    /**
     * Aumenta el poder de ataque temporalmente
     * @param cantidad Cantidad a aumentar
     */
    public void aumentarAtaque(int cantidad) {
        this.poderAtaque += cantidad;
    }
    
    @Override
    public String toString() {
        return nombre + " [HP: " + puntosVida + "/" + puntosVidaMax + 
               " | ATK: " + poderAtaque + "]";
    }
}