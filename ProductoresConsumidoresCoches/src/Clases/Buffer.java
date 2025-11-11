package Clases;

import java.util.ArrayList;

public class Buffer {
	private ArrayList<Coche> buffer;
	private int capacidad;
	
	public Buffer(int capacidad) {
		super();
		this.capacidad = capacidad;
		this.buffer = new ArrayList<>();
	}
	
	/********************************************************/
	public synchronized void producir(Coche coche) throws InterruptedException {
		while(buffer.size() == capacidad) {
			System.out.println("Buffer lleno, el productor está esperando...");
			wait();
		}
		buffer.add(coche);
		System.out.println("El productor agregó " + coche);
		notify();
	}
	/********************************************************/
	public synchronized Coche consumir() throws InterruptedException {
		while(buffer.size() == 0) {
			System.out.println("El consumidor está esperando. El buffer está vacío");
			wait(); //Espera si el buffer está vacío
		}
		Coche coche = buffer.remove(0);
		System.out.println("El consumidor consumió " + coche);
		notify();//Notifica al productor que hay valores por introducir
		return coche;
	}
}
