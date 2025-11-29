package Model;

public class Testigo {
	private boolean libre;

	public Testigo() {
		super();
		this.libre = true;
	}
	
	public boolean testigoLibre() {return libre;}
	
	public synchronized void cogerTestigo() {
		while(!libre) {//Mientras el testigo no este libre, esperan los corredores
			try {
				wait(); 
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		libre = false; // el corredor lo ha cogido
	}
	public synchronized void soltarTestigo() {
		libre = true;//el corredor termina y suelta el testigo
		notify(); // notifica al siguiente que lo ha soltado
	}
}
