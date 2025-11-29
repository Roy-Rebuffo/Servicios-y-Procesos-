package Model;

import java.util.ArrayList;


public class Carrera {
	private boolean salida = false;
	private long tiempoSalida;
	private ArrayList<Long> resultados = new ArrayList<Long>();
	
	public void esperar() throws InterruptedException {
		Thread.sleep(2000);
	}
	
	public synchronized void esperarSalida() throws InterruptedException {
		while(!salida) {//mientras no salgan esperamos
			wait();
		}
	}
	
	 public synchronized void hacerSalida() throws InterruptedException {
	        System.out.println("Preparados...");
	        esperar();

	        System.out.println("Listos...");
	        esperar();

	        System.out.println("YA!");
	        salida = true;
	        tiempoSalida = System.currentTimeMillis();
	        notifyAll(); // Despertamos a todos los atletas
	    }
	
	public synchronized void registrarLlegada(int dorsal, long tiempoLlegada) {
		long total = tiempoLlegada - tiempoSalida;
		resultados.add(total);
		System.out.println("El atleta con el dorsal " + dorsal + " tarda " + total);
	}
}
