package Main;

import Model.Atleta;
import Model.Carrera;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		final int N_ATL = 8;
		Atleta[] atl = new Atleta[N_ATL];
		Carrera c = new Carrera();
		
		for (int i = 0; i < N_ATL; i++) {
			atl[i] = new Atleta(i, c);
			atl[i].start();
		}
		c.hacerSalida();
		
	}

}
