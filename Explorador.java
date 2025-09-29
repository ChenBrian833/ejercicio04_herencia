public class Explorador extends Jugador {
    
    /**
     * Constructor de Explorador con valores predefinidos
     * @param nombre Nombre del explorador
     */
    public Explorador(String nombre) {
        super(nombre, 100, 15, 7); // Vida normal, ataque normal, amplia capacidad items
    }
    
    @Override
    public String getMensajeInicio() {
        return nombre + " el Explorador ajusta su mochila: 'Veamos qué tengo aquí...'";
    }
    
    @Override
    public String getMensajeMuerte() {
        return nombre + " cae exhausto... 'Debí traer... más pociones...'";
    }
}