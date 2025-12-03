package Main;

import Model.Coche;
import Model.Parking;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		final int N_COCHES = 10;
		Parking parking = new Parking();
		Coche[] coche = new Coche[N_COCHES]; //Hilos
		
		for (int i = 0; i < N_COCHES; i++) {
			coche[i] = new Coche(parking, " Coche-> " + i);
			coche[i].start();
		}
		
		for (Coche c : coche) {
			c.join();
		}
	}
}
