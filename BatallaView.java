import java.util.List;

/**
 * Clase Vista del patrón MVC para la batalla.
 * Se encarga de mostrar información al usuario.
 * ÚNICA clase con System.out.println según las instrucciones.
 */
public class BatallaView {
    
    /**
     * Muestra el estado actual de todos los combatientes
     * @param jugador El jugador
     * @param enemigos Lista de enemigos
     */
    public void mostrarEstadoBatalla(Jugador jugador, List<Enemigo> enemigos) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ESTADO DE LA BATALLA");
        System.out.println("=".repeat(60));
        
        System.out.println("\n[JUGADOR]");
        System.out.println(jugador.toString());
        
        System.out.println("\n[ENEMIGOS]");
        for (int i = 0; i < enemigos.size(); i++) {
            Enemigo enemigo = enemigos.get(i);
            String estado = enemigo.estaVivo() ? "" : " [DERROTADO]";
            System.out.println((i + 1) + ". " + enemigo.toString() + estado);
        }
        System.out.println("=".repeat(60));
    }
    
    /**
     * Muestra las últimas acciones realizadas
     * @param acciones Lista con las últimas 3 acciones
     */
    public void mostrarRegistroAcciones(List<String> acciones) {
        System.out.println("\n--- ÚLTIMAS ACCIONES ---");
        if (acciones.isEmpty()) {
            System.out.println("(Sin acciones aún)");
        } else {
            for (int i = 0; i < acciones.size(); i++) {
                System.out.println((i + 1) + ". " + acciones.get(i));
            }
        }
        System.out.println();
    }
    
    /**
     * Muestra el menú principal de combate
     */
    public void mostrarMenuCombate() {
        System.out.println("\n¿Qué acción deseas realizar?");
        System.out.println("1. Atacar");
        System.out.println("2. Usar ítem");
        System.out.println("3. Pasar turno");
        System.out.println("4. Salir de la batalla");
        System.out.print("Opción: ");
    }
    
    /**
     * Muestra el menú de selección de enemigos
     * @param enemigos Lista de enemigos disponibles
     */
    public void mostrarMenuEnemigos(List<Enemigo> enemigos) {
        System.out.println("\n¿A quién deseas atacar?");
        for (int i = 0; i < enemigos.size(); i++) {
            if (enemigos.get(i).estaVivo()) {
                System.out.println((i + 1) + ". " + enemigos.get(i).getNombre() + 
                                 " [HP: " + enemigos.get(i).getPuntosVida() + "/" + 
                                 enemigos.get(i).getPuntosVidaMax() + "]");
            }
        }
        System.out.print("Opción: ");
    }
    
    /**
     * Muestra el menú de ítems disponibles
     * @param items Lista de ítems del jugador
     */
    public void mostrarMenuItems(List<Item> items) {
        System.out.println("\n¿Qué ítem deseas usar?");
        if (items.isEmpty()) {
            System.out.println("No tienes ítems disponibles!");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).toString());
        }
        System.out.println("0. Cancelar");
        System.out.print("Opción: ");
    }
    
    /**
     * Muestra el menú de acciones de enemigo
     * @param enemigo El enemigo que toma el turno
     */
    public void mostrarMenuAccionEnemigo(Enemigo enemigo) {
        System.out.println("\n--- Turno de " + enemigo.getNombre() + " ---");
        System.out.println("¿Qué hará " + enemigo.getNombre() + "?");
        System.out.println("1. Atacar");
        System.out.println("2. Usar habilidad especial");
        
        // Verificar si es un jefe con habilidad adicional
        if (enemigo instanceof GoblinJefe) {
            System.out.println("3. Furia Goblin (habilidad de jefe)");
        } else if (enemigo instanceof EsqueletoJefe) {
            System.out.println("3. Maldición (habilidad de jefe)");
        }
        
        System.out.println("0. Pasar turno");
        System.out.print("Opción: ");
    }
    
    /**
     * Muestra un mensaje genérico
     * @param mensaje Mensaje a mostrar
     */
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    
    /**
     * Muestra mensaje de victoria
     */
    public void mostrarVictoria() {
        System.out.println("\n" + "★".repeat(60));
        System.out.println("¡¡¡VICTORIA!!!");
        System.out.println("Has derrotado a todos los enemigos!");
        System.out.println("★".repeat(60));
    }
    
    /**
     * Muestra mensaje de derrota
     */
    public void mostrarDerrota() {
        System.out.println("\n" + "☠".repeat(60));
        System.out.println("DERROTA...");
        System.out.println("Has sido vencido en combate.");
        System.out.println("☠".repeat(60));
    }
    
    /**
     * Muestra el menú de selección de rol
     */
    public void mostrarMenuRol() {
        System.out.println("\n=== SELECCIÓN DE ROL ===");
        System.out.println("1. Guerrero - [HP: 150 | ATK: 25 | Items: 3]");
        System.out.println("   Bastante vida, bastante ataque, poca capacidad de ítems");
        System.out.println("2. Explorador - [HP: 100 | ATK: 15 | Items: 7]");
        System.out.println("   Vida y ataque normales, amplia variedad de ítems");
        System.out.print("Elige tu rol (1 o 2): ");
    }
    
    /**
     * Muestra banner de inicio
     */
    public void mostrarBanner() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println(" ".repeat(15) + "SIMULADOR DE BATALLA");
        System.out.println(" ".repeat(10) + "Juego de Rol por Turnos");
        System.out.println("=".repeat(60) + "\n");
    }
}