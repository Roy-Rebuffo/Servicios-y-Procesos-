package Main;

import Clases.Coche;
import Clases.Semaforo;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		final int n = 6;
		
		Semaforo semaforo = new Semaforo();
		Coche[] c = new Coche[n];
		
		for (int i = 0; i < n; i++) {
			c[i] = new Coche(i,semaforo);
			c[i].start();
		}
		semaforo.cruzar();
	}
}
