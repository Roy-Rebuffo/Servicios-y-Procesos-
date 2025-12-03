package Clases;

public class Cruce extends Thread {
    private volatile boolean isGreen = false; // Estado de la luz
    private volatile boolean isCrossing = false; // Exclusión Mutua
    int cochesRestantes;
    private final int cochesTotales;

    public Cruce(int cochesTotales) {
        this.cochesTotales = cochesTotales;
        this.cochesRestantes = cochesTotales;
    }
    
    // Método de tiempo en ROJO (4 segundos)
    private void enRojo() throws InterruptedException { Thread.sleep(4000); }
    // Método de tiempo en VERDE (2 segundos)
    private void enVerde() throws InterruptedException { Thread.sleep(2000); }

    // --- ACCESO Y SINCRONIZACIÓN PARA COCHES ---
    
    public synchronized void intentarCruzar() throws InterruptedException {
        // La condición de espera más fuerte (Bloqueo en ROJO O Bloqueo por Exclusión)
        while (!isGreen || isCrossing) {
            wait();
        }
        // Cuando un hilo pasa el 'while', obtiene el candado de cruce.
        isCrossing = true;
    }
    
    public synchronized void terminarCruce(String name) {
        isCrossing = false; // Libera el candado de cruce
        cochesRestantes--;
        System.out.println("✅ " + name + " terminó. Coches restantes: " + cochesRestantes);
        
        // Despierta a los demás: 
        // 1) Si la luz sigue VERDE, el siguiente coche puede pasar inmediatamente.
        // 2) Si la luz está ROJA, no pasa nada, quedan en espera.
        notifyAll(); 
    }
    
    // --- CICLO DE VIDA DEL SEMÁFORO ---

    @Override
    public void run() {
        try {
            while (cochesRestantes > 0) {
                // FASE ROJO
                System.out.println("\n🚦 Semáforo: ROJO (4s) - Coches esperando...");
                isGreen = false;
                enRojo(); // Espera 4 segundos
                
                // FASE VERDE
                // Debe estar synchronized para poder usar notifyAll()
                synchronized (this) {
                    System.out.println("🟢 Semáforo: ¡VERDE! (2s) - Coches compiten...");
                    isGreen = true;
                    notifyAll(); // Despierta a todos los coches
                } // El bloqueo se libera aquí.
                
                enVerde(); // Permanece en verde 2 segundos
                
                // Fin de la ventana verde. Forzamos el estado de ROJO.
                // Obtenemos el bloqueo de nuevo para cambiar el estado.
                synchronized (this) {
                    isGreen = false;
                    // Opcional: notifyAll() aquí para asegurar que los coches que 
                    // no lograron pasar el 'while' se bloqueen de nuevo si fuera necesario.
                }

            }
            System.out.println("\n🏁 FIN: Todos los coches han cruzado.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}