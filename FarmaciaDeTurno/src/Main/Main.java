package Main;

import Clases.Cliente;
import Clases.Ventanilla;

public class Main {

	public static void main(String[] args) {
		final int n_clientes = 12;
		Cliente[] c = new Cliente[n_clientes];
		Ventanilla v = new Ventanilla();
		
		for (int i = 0; i < n_clientes; i++) {
			c[i] = new Cliente(v, "Cliente-> " + i);
			c[i].start();
		}
	}
}
