import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase Controlador del patrón MVC para la batalla.
 * Maneja la lógica del juego y coordina la vista con el modelo.
 */
public class BatallaController {
    private Jugador jugador;
    private List<Enemigo> enemigos;
    private BatallaView vista;
    private List<String> registroAcciones;
    private Scanner scanner;
    private Random random;
    private boolean batallaActiva;
    
    /**
     * Constructor del controlador
     * @param vista Vista asociada
     */
    public BatallaController(BatallaView vista) {
        this.vista = vista;
        this.enemigos = new ArrayList<>();
        this.registroAcciones = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.batallaActiva = false;
    }
    
    /**
     * Inicia el simulador de batalla
     */
    public void iniciarSimulador() {
        vista.mostrarBanner();
        crearJugador();
        inicializarItems();
        generarEnemigos();
        iniciarBatalla();
    }
    
    /**
     * Crea el jugador según la elección del usuario
     */
    private void crearJugador() {
        vista.mostrarMenuRol();
        int opcion = leerOpcion(1, 2);
        
        vista.mostrarMensaje("");
        String nombre = leerTexto("Ingresa el nombre de tu personaje: ");
        
        if (opcion == 1) {
            jugador = new Guerrero(nombre);
        } else {
            jugador = new Explorador(nombre);
        }
        
        vista.mostrarMensaje("\n" + jugador.getMensajeInicio());
    }
    
    /**
     * Inicializa los ítems del jugador
     */
    private void inicializarItems() {
        // Pociones de curación
        jugador.agregarItem(new Pocion("Poción Pequeña", "Cura 30 HP", 3, 30));
        jugador.agregarItem(new Pocion("Poción Grande", "Cura 60 HP", 2, 60));
        
        // Elixires de ataque
        jugador.agregarItem(new Elixir("Elixir de Fuerza", "Aumenta ATK +10", 2, 10));
        
        // El explorador tiene más ítems
        if (jugador instanceof Explorador) {
            jugador.agregarItem(new Pocion("Mega Poción", "Cura 100 HP", 1, 100));
            jugador.agregarItem(new Elixir("Elixir Supremo", "Aumenta ATK +15", 1, 15));
        }
    }
    
    /**
     * Genera entre 1 y 3 enemigos aleatoriamente
     */
    private void generarEnemigos() {
        int cantidadEnemigos = random.nextInt(3) + 1; // 1 a 3 enemigos
        
        vista.mostrarMensaje("\n¡Aparecen " + cantidadEnemigos + " enemigo(s)!\n");
        
        for (int i = 0; i < cantidadEnemigos; i++) {
            Enemigo enemigo = crearEnemigoAleatorio(i + 1);
            enemigos.add(enemigo);
            vista.mostrarMensaje(enemigo.getMensajeInicio());
        }
    }
    
    /**
     * Crea un enemigo aleatorio (Goblin o Esqueleto, normal o jefe)
     * @param numero Número del enemigo
     * @return Enemigo creado
     */
    private Enemigo crearEnemigoAleatorio(int numero) {
        int tipo = random.nextInt(2); // 0: Goblin, 1: Esqueleto
        int esJefe = random.nextInt(4); // 25% de probabilidad de ser jefe
        
        if (tipo == 0) {
            if (esJefe == 0) {
                return new GoblinJefe("Goblin Jefe #" + numero);
            } else {
                return new Goblin("Goblin #" + numero);
            }
        } else {
            if (esJefe == 0) {
                return new EsqueletoJefe("Señor Esqueleto #" + numero);
            } else {
                return new Esqueleto("Esqueleto #" + numero);
            }
        }
    }
    
    /**
     * Inicia la batalla por turnos
     */
    private void iniciarBatalla() {
        batallaActiva = true;
        
        while (batallaActiva) {
            // Mostrar estado actual
            vista.mostrarEstadoBatalla(jugador, enemigos);
            vista.mostrarRegistroAcciones(registroAcciones);
            
            // Verificar condiciones de victoria/derrota
            if (!jugador.estaVivo()) {
                finalizarBatalla(false);
                break;
            }
            
            if (todosEnemigosEliminados()) {
                finalizarBatalla(true);
                break;
            }
            
            // Turno del jugador
            if (!turnoJugador()) {
                // El jugador eligió salir
                vista.mostrarMensaje("\nHas huido de la batalla.");
                break;
            }
            
            // Verificar si ganó después del turno del jugador
            if (todosEnemigosEliminados()) {
                finalizarBatalla(true);
                break;
            }
            
            // Turnos de los enemigos
            turnosEnemigos();
        }
    }
    
    /**
     * Maneja el turno del jugador
     * @return false si el jugador elige salir
     */
    private boolean turnoJugador() {
        vista.mostrarMensaje("\n>>> TURNO DEL JUGADOR <<<");
        vista.mostrarMenuCombate();
        
        int opcion = leerOpcion(1, 4);
        
        switch (opcion) {
            case 1: // Atacar
                accionAtacarJugador();
                break;
            case 2: // Usar ítem
                accionUsarItem();
                break;
            case 3: // Pasar turno
                agregarAccion(jugador.getNombre() + " pasa su turno.");
                break;
            case 4: // Salir
                return false;
        }
        
        return true;
    }
    
    /**
     * Maneja la acción de atacar del jugador
     */
    private void accionAtacarJugador() {
        List<Enemigo> enemigosVivos = obtenerEnemigosVivos();
        
        if (enemigosVivos.isEmpty()) {
            return;
        }
        
        vista.mostrarMenuEnemigos(enemigos);
        int indice = leerOpcion(1, enemigos.size()) - 1;
        
        if (enemigos.get(indice).estaVivo()) {
            String accion = jugador.atacar(enemigos.get(indice));
            agregarAccion(accion);
            
            // Verificar si el enemigo murió
            if (!enemigos.get(indice).estaVivo()) {
                agregarAccion(enemigos.get(indice).getMensajeMuerte());
            }
        } else {
            vista.mostrarMensaje("¡Ese enemigo ya fue derrotado!");
        }
    }
    
    /**
     * Maneja la acción de usar ítem
     */
    private void accionUsarItem() {
        if (jugador.getItems().isEmpty()) {
            vista.mostrarMensaje("¡No tienes ítems disponibles!");
            return;
        }
        
        vista.mostrarMenuItems(jugador.getItems());
        int opcion = leerOpcion(0, jugador.getItems().size());
        
        if (opcion == 0) {
            return; // Cancelar
        }
        
        Item item = jugador.getItems().get(opcion - 1);
        
        // Determinar objetivos según el tipo de ítem
        List<Combatiente> objetivos = new ArrayList<>();
        
        if (item instanceof Pocion) {
            // Las pociones pueden usarse en el jugador
            objetivos.add(jugador);
        } else if (item instanceof Elixir) {
            // Los elixires se usan en el jugador
            objetivos.add(jugador);
        }
        
        String accion = jugador.usarItem(item, objetivos);
        agregarAccion(accion);
    }
    
    /**
     * Maneja los turnos de todos los enemigos vivos
     */
    private void turnosEnemigos() {
        for (Enemigo enemigo : enemigos) {
            if (!enemigo.estaVivo()) {
                continue;
            }
            
            vista.mostrarMenuAccionEnemigo(enemigo);
            int opcion = leerOpcionEnemigo(enemigo);
            
            ejecutarAccionEnemigo(enemigo, opcion);
            
            // Verificar si el jugador murió
            if (!jugador.estaVivo()) {
                break;
            }
        }
    }
    
    /**
     * Lee la opción de acción del enemigo controlado por el usuario
     * @param enemigo Enemigo actual
     * @return Opción seleccionada
     */
    private int leerOpcionEnemigo(Enemigo enemigo) {
        int maxOpcion = 2;
        if (enemigo instanceof GoblinJefe || enemigo instanceof EsqueletoJefe) {
            maxOpcion = 3;
        }
        return leerOpcion(0, maxOpcion);
    }
    
    /**
     * Ejecuta la acción seleccionada para un enemigo
     * @param enemigo Enemigo que realiza la acción
     * @param opcion Opción seleccionada
     */
    private void ejecutarAccionEnemigo(Enemigo enemigo, int opcion) {
        String accion;
        
        switch (opcion) {
            case 0: // Pasar turno
                agregarAccion(enemigo.getNombre() + " pasa su turno.");
                break;
            case 1: // Atacar
                accion = enemigo.atacar(jugador);
                agregarAccion(accion);
                if (!jugador.estaVivo()) {
                    agregarAccion(jugador.getMensajeMuerte());
                }
                break;
            case 2: // Habilidad especial
                accion = enemigo.habilidadEspecial(jugador);
                agregarAccion(accion);
                break;
            case 3: // Habilidad de jefe
                if (enemigo instanceof GoblinJefe) {
                    accion = ((GoblinJefe) enemigo).furiaGoblin();
                    agregarAccion(accion);
                } else if (enemigo instanceof EsqueletoJefe) {
                    accion = ((EsqueletoJefe) enemigo).maldicion(jugador);
                    agregarAccion(accion);
                }
                break;
        }
    }
    
    /**
     * Agrega una acción al registro, manteniendo solo las últimas 3
     * @param accion Descripción de la acción
     */
    private void agregarAccion(String accion) {
        registroAcciones.add(accion);
        if (registroAcciones.size() > 3) {
            registroAcciones.remove(0);
        }
    }
    
    /**
     * Obtiene lista de enemigos vivos
     * @return Lista de enemigos vivos
     */
    private List<Enemigo> obtenerEnemigosVivos() {
        List<Enemigo> vivos = new ArrayList<>();
        for (Enemigo enemigo : enemigos) {
            if (enemigo.estaVivo()) {
                vivos.add(enemigo);
            }
        }
        return vivos;
    }
    
    /**
     * Verifica si todos los enemigos fueron eliminados
     * @return true si no quedan enemigos vivos
     */
    private boolean todosEnemigosEliminados() {
        for (Enemigo enemigo : enemigos) {
            if (enemigo.estaVivo()) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Finaliza la batalla mostrando el resultado
     * @param victoria true si el jugador ganó
     */
    private void finalizarBatalla(boolean victoria) {
        vista.mostrarEstadoBatalla(jugador, enemigos);
        vista.mostrarRegistroAcciones(registroAcciones);
        
        if (victoria) {
            vista.mostrarVictoria();
        } else {
            vista.mostrarDerrota();
        }
        
        batallaActiva = false;
    }
    
    /**
     * Lee una opción numérica del usuario dentro de un rango
     * @param min Valor mínimo
     * @param max Valor máximo
     * @return Opción válida seleccionada
     */
    private int leerOpcion(int min, int max) {
        int opcion = -1;
        boolean valido = false;
        
        while (!valido) {
            try {
                if (scanner.hasNextInt()) {
                    opcion = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                    
                    if (opcion >= min && opcion <= max) {
                        valido = true;
                    } else {
                        vista.mostrarMensaje("Opción inválida. Ingresa un número entre " + 
                                           min + " y " + max + ":");
                    }
                } else {
                    scanner.nextLine(); // Limpiar entrada inválida
                    vista.mostrarMensaje("Por favor ingresa un número válido:");
                }
            } catch (Exception e) {
                scanner.nextLine();
                vista.mostrarMensaje("Error al leer la opción. Intenta nuevamente:");
            }
        }
        
        return opcion;
    }
    
    /**
     * Lee un texto del usuario
     * @param mensaje Mensaje a mostrar
     * @return Texto ingresado
     */
    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }
}