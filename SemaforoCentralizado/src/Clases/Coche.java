package Clases;

import java.util.Random;

public class Coche extends Thread{
	private int idCoche;
	private Random random;
	private Semaforo semaforo;
	
	public Coche(int idCoche, Semaforo semaforo) {
		super();
		this.idCoche = idCoche;
		this.random = new Random();
		this.semaforo = semaforo;
	}
	
	public void cruzar() throws InterruptedException {
		int tiempo = 800 + random.nextInt(7000); //entre 0.8s y 1.5s
		Thread.sleep(tiempo);
	}
	
	@Override
	public void run() {
		try {
			semaforo.esperarVerde();
			cruzar();
			long llegada = System.currentTimeMillis();
			semaforo.registrarLlegada(idCoche, llegada);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
