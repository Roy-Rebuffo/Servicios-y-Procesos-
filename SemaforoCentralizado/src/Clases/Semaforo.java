package Clases;

import java.util.ArrayList;

public class Semaforo {
	private boolean salida = false;
	private long tiempoSalida;
	private ArrayList<Long> resultados = new ArrayList<Long>();
	
	public synchronized void esperarVerde() throws InterruptedException {
		while(!salida) {
			wait();
		}
	}
	
	public synchronized void cruzar() throws InterruptedException {
		System.out.println("Semaforo en rojo");
		Thread.sleep(2000); // Espera 2 segundos
		System.out.println("Semaforo en ambar");
		Thread.sleep(1000);
		System.out.println("Semaforo verde! Adelante>>");
		salida = true;
		tiempoSalida = System.currentTimeMillis();
		notifyAll();
	}
	
	public synchronized void registrarLlegada(int coche, long tiempoLlegada) {
		long total = (tiempoLlegada - tiempoSalida) / 1000; //Simular segundos
		resultados.add(total);
		System.out.println("El coche " + coche + " ha tardado " + total + " segundos");
	}
	
}
