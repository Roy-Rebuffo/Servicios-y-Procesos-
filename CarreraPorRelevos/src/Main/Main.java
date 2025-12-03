package Main;

import java.util.Iterator;

import Model.Atleta;
import Model.Testigo;

public class Main {

	public static void main(String[] args) {
		final int N_ATL = 4;
		
		Testigo testigo = new Testigo();
		Atleta[] atl = new Atleta[N_ATL];
		
		for (int i = 0; i < N_ATL; i++) {
			// **CORRECCIÓN:** Pasamos el índice (0, 1, 2, 3) como identidad
			atl[i] = new Atleta(testigo, i); 
			atl[i].start();
		}
	}
}
