public class Guerrero extends Jugador {
    
    /**
     * Constructor de Guerrero con valores predefinidos
     * @param nombre Nombre del guerrero
     */
    public Guerrero(String nombre) {
        super(nombre, 150, 25, 3); // Bastante vida, bastante ataque, poca capacidad items
    }
    
    @Override
    public String getMensajeInicio() {
        return nombre + " el Guerrero desenvaina su espada y ruge: '¡Por la gloria!'";
    }
    
    @Override
    public String getMensajeMuerte() {
        return nombre + " cae de rodillas... 'He... fallado...'";
    }
}