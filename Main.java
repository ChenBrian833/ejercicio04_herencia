/**
 * Universidad del Valle de Guatemala
 * CC2008 - Introducción a la Programación Orientada a Objetos
 * Ejercicio 4: Modelación con herencia Juego de Batalla
 * Brian Bolaños - 24846
 */
public class Main {
    
    /** 
     * @param args 
     */
    public static void main(String[] args) {
        // Crear la vista
        BatallaView vista = new BatallaView();
        
        // Crear el controlador con la vista
        BatallaController controlador = new BatallaController(vista);
        
        // Iniciar el simulador
        controlador.iniciarSimulador();
    }
}