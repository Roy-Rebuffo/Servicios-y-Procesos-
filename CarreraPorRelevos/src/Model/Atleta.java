package Model;

import java.util.Random;

public class Atleta extends Thread {
	private Random random;
	private Testigo testigo;
	
	public Atleta(Testigo testigo) {
		super();
		this.random = new Random();
		this.testigo = testigo;
	}
	
	public void corriendo() throws InterruptedException {
		int tiempo = 9000 + random.nextInt(2000);
	    Thread.sleep(tiempo);
	}
	
	@Override
	public void run() {
		testigo.cogerTestigo();
		
		System.out.println(Thread.currentThread().getName() + " Comienza a correr!");
		try {
			corriendo();
		} catch (Exception e) {
			System.err.println("OHH OHH, ALGO A PASADO" + e.getMessage());
		}
		System.out.println(Thread.currentThread().getName() + " Ha terminado de correr\n"
				+ "y ha tardado " + System.currentTimeMillis());
		testigo.soltarTestigo();//Suelta el testigo
	}
}
