package PrimerosHilos;

import java.util.Date;
import java.util.Iterator;

public class MayorVector {
	public static void main(String[] args) {
		/***************Con Hilos*******************************************/
		int[] v = new int[500_000_000];
		System.out.println("Inicio de la carga del vector");
		Date d1 = new Date();
		for(int f = 0; f< v.length ; f ++) {
			v[f] = (int)(Math.random() * 2_000_000_000);
		}
		System.out.println("Fin de la carga del vector");
		Date d2 = new Date();
		long milisegundos = (d2.getTime() - d1.getTime());
		System.out.println("Carga del vector en: " + milisegundos + " milisegundos");
		
		d1 = new Date();
		HiloMayor h1 = new HiloMayor();
		h1.fijarRango(0, v.length / 2, v);
		
		HiloMayor h2 = new HiloMayor();
		h2.fijarRango(v.length / 2 + 1, v.length - 1, v);
		
		while(h1.isAlive() || h2.isAlive()) 
			System.out.println("Mayor elemento del vector");
		
		if(h1.may > h2.may)
			System.out.println(h1.may);
		else
			System.out.println(h2.may);
		
		d2 = new Date();
		milisegundos = (d2.getTime() - d1.getTime());
		System.out.println("Milisegundos requeridos con 2 hilos: " + milisegundos);
		/**************Sin Hilos********************************************/
		d1 = new Date();
		int may = v[0];
		for (int f = 1; f < v.length; f++) {
			if(v[f] > may) may = v[f];
		}
		System.out.println("Mayor elemento del vector: " + may);
		d2 = new Date();
		milisegundos = (d2.getTime() - d1.getTime());
		System.out.println("Milisegundos requeridos sin hilos: " + milisegundos);
	}
}

class HiloMayor extends Thread{
	int [] v;
	int ini,fin;
	int may;
	
	void fijarRango(int i, int f, int []v) {
		this.ini = i;
		this.fin = f;
		this.v = v;
	}
	
	public void run() {
		may = v[ini];
		for (int f = ini + 1; f < fin; f++) {
			if(v[f] > may) may = v[f];
		}
	}
}