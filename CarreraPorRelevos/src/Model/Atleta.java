package Model;

import java.util.Random;

public class Atleta extends Thread {
	private final Random random; // ¡Ahora es final y se inicializa!
	private Testigo testigo;
	private final int num;
	
	public Atleta(Testigo testigo, int num) { 
        super("Corredor-" + num);
        this.num = num;
        this.testigo = testigo;
        this.random = new Random(); // <--- CORRECCIÓN 1: Inicializar Random
    }
	
	public void corriendo() throws InterruptedException {
		// Simula el tiempo de carrera (9s a 11s)
		int tiempo = 9000 + random.nextInt(2001); 
	    Thread.sleep(tiempo);
	}
    
    @Override
    public void run() {
        try {
            // 1. Pide el testigo (se bloquea si no es su turno)
            testigo.cogerTestigo(num); 
            
            System.out.println(Thread.currentThread().getName() + " Comienza a correr!");
            long tiempoInicio = System.currentTimeMillis();

            // 2. CORRE y se detiene (Thread.sleep())
            corriendo(); // <--- CORRECCIÓN 2: Llamar al método de carrera
            
            long duracion = System.currentTimeMillis() - tiempoInicio;

            System.out.println(Thread.currentThread().getName() + " Ha terminado de correr\n"
            		+ "y ha tardado " + duracion + " ms");
            
            // 3. Suelta el testigo y pasa el turno
            testigo.soltarTestigo(num); 
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + " fue interrumpido.");
        }
        // Nota: Si solo quieres el tiempo total de la carrera, usa System.currentTimeMillis() antes de Main.
    }
}