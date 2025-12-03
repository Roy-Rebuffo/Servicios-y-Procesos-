package Clases;

import java.util.Random;

public class Operario extends Thread{
	private final int num;
	private final LineaDeTrabajo linea;
	private String miFase;
	private final Random random = new Random(); // Inicializa el Random
	
	public Operario(int num, LineaDeTrabajo linea) {
		super("Operario-" + num); // Nombre del hilo
		this.num = num;
		this.linea = linea;
	}
	
	// Métodos para simular el tiempo de trabajo
	public void ensambla() throws InterruptedException {
		Thread.sleep(2000 + random.nextInt(2001)); 
	}
	public void pinta() throws InterruptedException {
		Thread.sleep(1000 + random.nextInt(2001)); 
	}
	public void controlCalidad() throws InterruptedException {
		Thread.sleep(500 + random.nextInt(1501)); 
	}
	
	// Auxiliar para ejecutar la tarea correcta
	private void ejecutarMiFase() throws InterruptedException {
	    if (miFase.equals("Ensamblaje")) {
	        ensambla();
	    } else if (miFase.equals("Pintura")) {
	        pinta();
	    } else if (miFase.equals("Control de Calidad")) {
	        controlCalidad();
	    }
	}
	
	@Override
	public void run() {
	    this.miFase = linea.getFases()[this.num]; // Asigna la fase basada en su índice (0, 1, 2)
	    long tiempoInicio;
	    
	    try {
	    	// 1. Espera a que sea su turno
	        linea.esperarMiTurno(this.num); 
	        
	        // 2. Inicia el trabajo
	        System.out.println("Operario " + num +  " inicia la fase de " + miFase);
	        tiempoInicio = System.currentTimeMillis();
	        
	        ejecutarMiFase(); // Ejecuta SOLO su trabajo
	        
	        // 3. Finaliza el trabajo e imprime el tiempo
	        long duracion = System.currentTimeMillis() - tiempoInicio;
	        System.out.println("Operario " + num + " (Fase " + miFase + ") tarda " + duracion + "ms.");
	        
	        // 4. Pasa el testigo al siguiente
	        linea.pasarTestigo(this.num);
	        
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	        System.out.println("Operario " + num + " fue interrumpido.");
	    }
	}
}