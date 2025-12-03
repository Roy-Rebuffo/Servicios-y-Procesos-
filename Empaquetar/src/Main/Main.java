package Main;

import Clases.Fabrica;
import Clases.Operario;

public class Main {

	public static void main(String[] args) {
		final int operarios = 3;
		Fabrica fabrica = new Fabrica();
		Operario[]op = new Operario[operarios];
		
		
		System.out.println("INICIANDO LÍNEA DE PRODUCCIÓN...");
		for (int i = 0; i < operarios; i++) {
			op[i] = new Operario(i,fabrica);
			op[i].start();
		}		
	}
}
