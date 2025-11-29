package Model;

import java.util.Random;

public class Atleta extends Thread{
	private int dorsal;
	private Random random;
	private Carrera carrera;

	public Atleta(int dorsal, Carrera carrera) {
		super();
		this.dorsal = dorsal;
		this.carrera = carrera;
		this.random = new Random();
	}
	
	public void correr() throws InterruptedException {
		int tiempo = 9000 + random.nextInt(2000);//Entre 9s y 11s
		Thread.sleep(tiempo);
	}
	
	@Override
	public void run() {
		try {
			carrera.esperarSalida();
			correr();
			long llegada = System.currentTimeMillis();
			carrera.registrarLlegada(dorsal, llegada);
		} catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
}