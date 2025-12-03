package Main;

import Clases.LineaDeTrabajo;
import Clases.Operario;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("INICIANDO LÍNEA DE PRODUCCIÓN...");
		LineaDeTrabajo lt = new LineaDeTrabajo();
		Operario[] op = new Operario[3];
		
		for (int i = 0; i < op.length; i++) {
			op[i] = new Operario(i,lt); // Asignamos Operario 0, 1, y 2
			op[i].start();
		}
		
	}
}