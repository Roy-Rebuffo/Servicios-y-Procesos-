package Clases;

public class Buffer {
	private int[] buffer;
	private int tamaño;
	private int contador = 0;
	
	public Buffer(int tamaño) {
		super();
		this.tamaño = tamaño;
		buffer = new int[tamaño];
	}
	/********************************************************/
	public synchronized void producir(int valor) throws InterruptedException {
		while(contador == tamaño) {
			System.out.println("Buffer lleno, el productor está esperando...");
			wait();
		}
		buffer[contador] = valor;
		contador++;
		System.out.println("El productor agregó " + valor);
		notify();
	}
	/********************************************************/
	public synchronized int consumir() throws InterruptedException {
		while(contador == 0) {
			System.out.println("El consumidor está esperando. El buffer está vacío");
			wait(); //Espera si el buffer está vacío
		}
		int valor = buffer[--contador];
		System.out.println("El consumidor consumió " + valor);
		notify();//Notifica al productor que hay valores por introducir
		return valor;
	}
}
