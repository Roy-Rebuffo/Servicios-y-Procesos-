package Principal;

import Clases.Cajera;
import Clases.Clientes;

public class Main {

	public static void main(String[] args) {
		final int N_CLIENTES = 3;
		Cajera cajera = new Cajera(N_CLIENTES);
		
		for (int i = 0; i < N_CLIENTES; i++) {
			new Clientes(cajera).start();
		}
		cajera.start();
	
		
	}

}
