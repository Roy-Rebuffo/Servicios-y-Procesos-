package Clases;

public class LineaDeTrabajo {
	
    // 0 = Ensamblaje, 1 = Pintura, 2 = Control de Calidad
    private int turnoActual = 0; 
    private final String[] fases = {"Ensamblaje", "Pintura", "Control de Calidad"};
    
    // Getter para que Operario pueda ver qué fase le toca
    public String[] getFases() {
        return fases;
    }

    // Este método bloquea al hilo si no es su turno
    public synchronized void esperarMiTurno(int numOperario) throws InterruptedException {
        // Mientras el turno actual no sea el número de operario que está llamando, espera.
        while (numOperario != turnoActual) {
            wait();
        }
        // Cuando despierta y el turno es el suyo, sale del while.
    }
    
    // Este método notifica al siguiente hilo cuando termina.
    public synchronized void pasarTestigo(int numOperario) {
        if (numOperario < fases.length - 1) { 
            turnoActual++; // Pasa el turno al siguiente operario
            System.out.println("\n--- Operario " + numOperario + " PASA TESTIGO a Operario " + turnoActual + " ---");
            notifyAll(); // Despierta a todos los hilos, pero solo el Operario 'turnoActual' podrá pasar el 'while' de arriba.
        } else {
            System.out.println("\n--- CARRERA COMPLETADA. El producto ha sido terminado. ---");
        }
    }
}