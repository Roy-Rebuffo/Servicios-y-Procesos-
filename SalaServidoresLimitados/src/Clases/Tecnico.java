package Clases;

import java.util.Random;

public class Tecnico extends Thread{
	private Random random;
	private SalaServidores sala;
	
	public Tecnico(SalaServidores sala, String nombre) {
		super();
		this.sala = sala;
		this.random = new Random();
	}
	
	public void curra() throws InterruptedException {
		int tiempo = 3000 + random.nextInt(2000);//aparca entre 3 y 5s
	    Thread.sleep(tiempo);
	}
	
	@Override
	public void run() {
		try {
			sala.entrar();
			curra();
			sala.salir();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
