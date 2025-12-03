package Clases;

import java.util.Random;

public class Operario extends Thread{
	private final int num;
	private final Fabrica fabrica;
	private String miFase;
	private final Random random = new Random();
	
	public Operario(int num, Fabrica fabrica) {
		super("Operario-" + num); // Nombre del hilo
		this.num = num;
		this.fabrica = fabrica;
		
	}
	// Métodos para simular el tiempo de trabajo
	public void curra() throws InterruptedException {
		int tiempo = 2000 + random.nextInt(2000);
		Thread.sleep(tiempo); 
	}
	// Auxiliar para ejecutar la tarea correcta
	private void ejecutarMiFase() throws InterruptedException {
		   if (miFase.equals("Cortar")) {
		    curra();
		   } else if (miFase.equals("Armar")) {
		    curra();
		   } else if (miFase.equals("Empaquetar")) {
		    curra();
		   }
	}
	
	@Override
	public void run() {
	    this.miFase = fabrica.getFases()[this.num]; // Asigna la fase basada en su índice (0, 1, 2)
	    long tiempoInicio;
	    
	    try {
	    	// 1. Espera a que sea su turno
	    	fabrica.esperarTunrno(this.num);
	        
	        // 2. Inicia el trabajo
	        System.out.println("Operario " + num +  " inicia la fase de " + miFase);
	        tiempoInicio = System.currentTimeMillis();
	        
	        ejecutarMiFase(); // Ejecuta SOLO su trabajo
	        
	        // 3. Finaliza el trabajo e imprime el tiempo
	        long duracion = System.currentTimeMillis() - tiempoInicio;
	        System.out.println("Operario " + num + " (Fase " + miFase + ") tarda " + duracion + "ms.");
	        
	        // 4. Pasa el testigo al siguiente
	        fabrica.pasarTurno(this.num);
	        
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	        System.out.println("Operario " + num + " fue interrumpido.");
	    }
	}
}
