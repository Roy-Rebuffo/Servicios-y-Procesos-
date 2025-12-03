package Model;

import java.util.Random;

public class Coche extends Thread{ 
	private Random random;
	private Parking parking;
	
	public Coche(Parking parking, String nombre) {
		super();
		this.parking = parking;
		this.random = new Random();
	}

	public void aparca() throws InterruptedException {
		int tiempo = 3000 + random.nextInt(2000);//aparca entre 3 y 5s
	    Thread.sleep(tiempo);
	}
	
	@Override
	public void run() {
		try {
			parking.entrar();
			aparca();
			parking.salir();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
