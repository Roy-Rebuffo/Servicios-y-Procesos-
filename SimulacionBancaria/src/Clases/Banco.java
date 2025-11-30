package Clases;

public class Banco {
	private int saldo = 100;

	public Banco() {
		super();
		this.saldo = saldo;
	}
	
	public synchronized void ingresar(int cantidad) {
		
		saldo +=cantidad;
	}
	public synchronized void retirar(int cantidad) {
		saldo-=cantidad;
	}

	public int getSaldo() {
		return saldo;
	}
}
