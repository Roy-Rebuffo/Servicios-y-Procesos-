package Clases;

import java.util.Random;

public class Coche extends Thread {
    private final Cruce cruce;
    private final Random random;

    public Coche(Cruce cruce, int id) {
        super("Coche-" + id);
        this.cruce = cruce;
        this.random = new Random();
    }

    private void cruzar() throws InterruptedException {
        // 500 ms + un aleatorio entre 0 y 700 ms = 500ms a 1200ms
        int tiempo = 500 + random.nextInt(701); 
        Thread.sleep(tiempo);
    }

    @Override
    public void run() {
        try {
            // El bucle permite que el coche intente cruzar hasta que el programa termine
            while (true) {
                // Bloquea: Espera a que sea Verde Y que nadie esté cruzando.
                cruce.intentarCruzar(); 
                
                // Verificación final para salir si ya todos cruzaron
                if (cruce.cochesRestantes <= 0) break;
                
                // El coche tiene el candado (isCrossing = true)
                long inicio = System.currentTimeMillis();
                System.out.println("🚗 " + getName() + " CRUZANDO. Tiempo de inicio: " + inicio);
                
                cruzar(); // Simulación de cruce (500ms a 1200ms)
                
                // Libera el cruce y notifica al siguiente
                cruce.terminarCruce(getName()); 
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}