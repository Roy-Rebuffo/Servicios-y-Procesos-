package Main;

import Clases.SalaServidores;
import Clases.Tecnico;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		final int n = 9;
		SalaServidores sala = new SalaServidores();
		Tecnico[] t = new Tecnico[n];
		
		for (int i = 0; i < n; i++) {
			t[i] = new Tecnico(sala,"Tecnico-> " + i);
			t[i].start();
		}
		
		for (Tecnico tecnico : t) {
			tecnico.join();
		}
	}

}
