package Model;

public class Testigo {
    private boolean libre = true;
    // 0: Corredor 0, 1: Corredor 1, etc.
    private int turnoActual = 0; 
    private final int N_ATL = 4; // Total de corredores

    public synchronized void cogerTestigo(int miNumero) {
        // La condición de bloqueo es doble:
        // 1. Que el testigo NO esté libre (Exclusión Mutua)
        // 2. O que NO sea mi turno (Coordinación Secuencial)
        while(!libre || miNumero != turnoActual) { 
            try {
                wait(); 
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        // Si sale del while, el testigo es libre Y es mi turno.
        libre = false;
    }
    
    public synchronized void soltarTestigo(int miNumero) {
        libre = true; // El corredor suelta el testigo
        
        if (miNumero < N_ATL - 1) { // Mientras no sea el último
            turnoActual++;          // Se pasa el turno al siguiente
            System.out.println("--- " + Thread.currentThread().getName() + " PASA el testigo al corredor " + turnoActual + " ---");
            notifyAll(); // Despierta a todos para que reevalúen la condición
        } else {
            System.out.println("\n*** CARRERA COMPLETADA por ! ***");
        }
    }
}