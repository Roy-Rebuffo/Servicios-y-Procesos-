package PrimerosHilos;

import java.util.Date;

public class MayorVector {

	public static int alea(int li, int ls) {// función (método)
		return (int) ((Math.round(Math.random() * (ls - li)) + li));
	}

	public static void main(String[] args) {
		// Creamos el vector
		int[] v = new int[500_000_000];
		/****************************************************************/
		/*
		 * //Cargamos los aleatorios en el vector for (int i = 0; i < v.length; i++) {
		 * v[i] = alea(1,2000000000); }
		 */
		/****************************************************************/

		// Cargamos los aleatorios en el vector
		for (int f = 0; f < v.length; f++) {
			v[f] = (int) (Math.random() * 2_000_000_000);
		}

		System.out.println("Fin de la carga del vector.");
		Date d1 = new Date();

		HiloMayor hilo1 = new HiloMayor();
		hilo1.fijarRango(0, v.length / 2, v);

		HiloMayor hilo2 = new HiloMayor();
		hilo2.fijarRango(v.length / 2 + 1, v.length - 1, v);

		hilo1.start();
		hilo2.start();

		while (hilo1.isAlive() || hilo2.isAlive());

		System.out.print("Mayor elemento del vector: ");

		if (hilo1.may > hilo2.may)
			System.out.println(hilo1.may);
		else
			System.out.println(hilo2.may);

		Date d2 = new Date();
		long milisegundos = (d2.getTime() - d1.getTime());

		System.out.println("Milisegundos requeridos con 2 hilos: " + milisegundos);

		/****************************************************************/
		d1 = new Date();
		int may = v[0];
		
		for (int f = 1; f < v.length; f++) {
			if(v[f] > may) may = v [f];
		}
		System.out.println("Mayor elemento del vector: " + may);
		d2 = new Date();
		milisegundos = (d2.getTime() - d1.getTime());
		System.out.println("Milisegundos requeridos sin hilos: " + milisegundos);
	}
}

class HiloMayor extends Thread {
	int[] v;
	int ini, fin;
	int may;

	void fijarRango(int i, int f, int[] v) {
		this.ini = i;
		this.fin = f;
		this.v = v;
	}

	public void run() {
		may = v[ini];
		for (int f = ini + 1; f < fin; f++) {
			if (v[ini] > may)
				may = v[f];
		}
	}
}
