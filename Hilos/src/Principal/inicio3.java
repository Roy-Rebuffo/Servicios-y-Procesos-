package Principal;
/*
Resumen paso a paso:
1) main crea un objeto `Contador` y varios hilos (`NUM_HILOS`), cada uno con parte del trabajo.
2) Cada hilo ejecuta `run()`, incrementando el contador compartido de forma sincronizada.
3) Los métodos `synchronized` en `Contador` evitan condiciones de carrera.
4) main espera con `join()` a que todos los hilos terminen.
5) Finalmente imprime la cuenta global acumulada correctamente.
*/

import java.util.Iterator;

public class inicio3 {
	public static final int NUM_HILOS = 10;
	public static final int CUENTA_TOTAL = 1_000_000_000;
	
	public static void main(String[] args) {
		Contador2 c = new Contador2();
		Tarea2 [] hilos = new Tarea2 [NUM_HILOS];
		
		for (int i = 0; i < NUM_HILOS; i++) {
			
			hilos[i] = new Tarea2(i,CUENTA_TOTAL/NUM_HILOS,c);
			hilos[i].start();
		}
		for(Thread h : hilos) { //nos recorremos el array de hilos
			try {
				h.join();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		System.out.printf("Cuenta global: %d\s", c.getCuenta());
	}

}
/***************************************************************/
class Contador2 {
	private int cuenta = 0;
	
	synchronized public int getCuenta() {
		return cuenta;
	}
	
	synchronized public int incrementa() {
		return cuenta ++;
	}
}

/***************************************************************/
class Tarea2 extends Thread{
	int numHilo, miParte,miCuenta = 0;
	private final Contador2 cont;
	
	//Constructor
	public Tarea2(int numHilo, int miParte, Contador2 cont) {
		super();
		this.numHilo = numHilo;
		this.miParte = miParte;
		this.cont = cont;
	}

	public int getMiCuenta() {
		return miCuenta;
	}
	@Override
	public void run() {
		for (int i = 0; i < miParte; i++) {
			this.cont.incrementa();
			miCuenta++;
		}
		System.out.printf("Hilo  %d terminado, cuenta: %d\n",numHilo,getMiCuenta());
	}
}
/***************************************************************/
