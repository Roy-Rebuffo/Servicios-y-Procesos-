package Clases;

import java.util.Iterator;
import java.util.Random;

public class Cajera extends Thread{
	private boolean libre = false;
	private int ocupadas = 0;
	private int cajeras = 3; //cantidad de cajeras
	private Random random;
	private int clientesTotales;
	int clientesRestantes;
	private Clientes cliente;

	public Cajera(int clientesTotales) {
		super();
		this.random = new Random();
		this.clientesTotales = clientesTotales;
		this.clientesRestantes = clientesTotales;
	}

	public void tiempoEnProcesarProductos() throws InterruptedException {
		int tiempo = 2000 + random.nextInt(1000);//Entre 2 y 3 segundos
		Thread.sleep(tiempo);
	}
	
	public synchronized void intentarCobrar() throws InterruptedException {
		while(ocupadas == cajeras) {//No hay cajeras disponibles
			wait();
		}
		ocupadas ++;
		System.out.println("\n" + Thread.currentThread().getName() + " Entra en la caja.\n"
				+ "Cajas Ocupadas->" + ocupadas);
	}
	public synchronized void terminarCobrar(String name) {
		ocupadas--;//aumentan los espacios cuando una cajera se queda libre
		System.out.println("\n" + Thread.currentThread().getName() + " Sale de la caja.\n"
				+ "Cajas Libres-> " + (cajeras - ocupadas));
		notifyAll(); //notifica a los otros para que entren
	}
	
	@Override
	public void run() {
		try {
			while(clientesRestantes > 0) {//Mientras haya clientes
				System.out.println("Comienza a cobrar");
				libre = true;//puede cobrar
				tiempoEnProcesarProductos();
				notifyAll();
				synchronized (this) {
					System.out.println("Esta cobrando...");
					libre = false;
                } 
			}
			System.out.println("Fin de la compra...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
