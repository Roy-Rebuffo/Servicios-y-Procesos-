package Clases;

public class SalaServidores {
	private int numeroDeTecnicosDentro = 3;
	private int ocupadas = 0;
	private boolean libre= false;

	public boolean estaLibre() {
		return numeroDeTecnicosDentro > ocupadas;
	}
	
	public synchronized void entrar() throws InterruptedException {
		while(numeroDeTecnicosDentro == ocupadas) {
			wait();
		}
		ocupadas ++;//Sale del bucle porque hay hueco para entrar
		System.out.println("El tecnico " + Thread.currentThread().getName() + " entra en la sala>>"
				+ "\nNumero de plazas ocupadas " + ocupadas);
	}
	public synchronized void salir() {
		ocupadas--;//aumentan los espacios cuando un coche sale
		System.out.println("\n" + Thread.currentThread().getName() + " Sale de la sala.\n"
				+ "Plazas Libres-> " + (numeroDeTecnicosDentro - ocupadas));
		notifyAll(); //notifica a los otros para que entren
	}
}
