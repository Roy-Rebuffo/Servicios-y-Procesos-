package Clases;

public class Puerta {
	private boolean ocupada;

	public Puerta() {
		this.ocupada = ocupada; //En que momento lo decide esto_?¿?¿?¿ Porque no esta en el constructor?¿
	}
	
	public boolean estaOcupada() {return ocupada;}
	
	public synchronized void liberarPuerta() {ocupada = false;}
	
	public synchronized boolean intentarEntrar() {
		if(ocupada) return false;
		ocupada =!ocupada;
		return ocupada;
	}
}
