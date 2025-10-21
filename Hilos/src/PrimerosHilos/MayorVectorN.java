package PrimerosHilos;

import java.util.Date;
import java.util.Scanner;

//Poner una variable numero de hilos (darle contenido con scanner)
//si el usuario pone 4. pues int[] v = new int[500_000_000]; / 4
//hacer tantos hilos como la variable indique
//comparar con el proceso de busqueda con hilos
//comparar con el proceso de busqueda sin hilos

public class MayorVectorN {

	public static Scanner sc = new Scanner(System.in);

	// public static int[] h = new int[1];

	public static void main(String[] args) {
		System.out.println("Cantidad de núcleos del procesador: " + Runtime.getRuntime().availableProcessors());
		System.out.print("Indicar el nunmero de hilos a estudiar: ");
		int res = Integer.valueOf(sc.nextLine());
		int tamaño = 500_000_000;
		int tramo = res;
		int divi = tamaño / tramo;
		
		HiloMayor2 hilos[] = new HiloMayor2[tramo];
		
		// Creamos el vector
		int[] v = new int[tamaño];
		System.out.println("Inicio de la carga del vector.");
		Date d1 = new Date();

		// Cargamos los aleatorios en el vector
		for (int f = 0; f < v.length; f++) {
			v[f] = (int) (Math.random() * 2_000_000_000);
		}

		System.out.println("Fin de la carga del vector.");
		Date d2 = new Date();
		System.out.println("Tiempo de la carga de matriz: " + (d2.getTime() - d1.getTime()));
		
		System.out.println("Fin de la carga del vector.");
		d1 = new Date();
		
		int z = 0;
		for (int i = 0; i < tramo; i++) {
			if(i!=0) z=1;
			System.out.printf("%d %d \n", (i*divi)+z, (i+1)*divi);
			hilos[i] = new HiloMayor2((i*divi)+z, (i+1)*divi,v);
			//hilos[i].start();
		}
		d1 = new Date();
		for (int i = 0; i < tramo; i++) hilos[i].start();

       
       boolean sigo = true;
       while(sigo) {
    	   sigo = false;
    	   for (int i = 0; i < tramo; i++) {
			if(hilos[i].isAlive()) {
				sigo = true;
				break;
			}
    	   }
       }
       int may = hilos[0].may;
       for (int i = 0; i < tramo; i++) {
		if (hilos[i].may > may) may = hilos[i].may;
	}
       d2 = new Date();
       System.out.println("Mayor elemento del vector: " + may);
       long milisegundos = (d2.getTime() - d1.getTime());
       
       System.out.println("Milisegundos requeridos con " + tramo + " hilos "+ milisegundos);
       
       /***************************SIN HILOS*************************************************/
       d1 = new Date();
       may = v[0];
       for (int f = 0; f < v.length; f++) {
		if(v[f] > may) may = v[f];
       }
       System.out.println("Mayor elemento del vector: " + may);
       d2 = new Date();
       milisegundos = (d2.getTime() - d1.getTime());
       System.out.println("Milisegundos requeridos sin hilos: " + milisegundos);
	}
}
class HiloMayor2 extends Thread {
	int[] v;
	int ini, fin;
	int may;

	public HiloMayor2(int i, int f, int[] v) {
		this.ini = i;
		this.fin = f;
		this.v = v;
	}

	//Lo que va a ejecutar cuando hagar start();
	public void run() {
		may = v[ini];
		for (int f = ini + 1; f < fin; f++) {
			if (v[ini] > may)
				may = v[f];
		}
	}
}