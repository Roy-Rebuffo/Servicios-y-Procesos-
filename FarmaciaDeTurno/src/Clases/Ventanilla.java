package Clases;

public class Ventanilla {
	private int capacidad = 2;
	private int ocupadas = 0;
	private boolean libre =  false;
	
	public boolean ventanillaLibre() {
		return capacidad > ocupadas;
	}
	
	public synchronized void entrar() throws InterruptedException {
		while(capacidad == ocupadas) {//no te pueden atender porque estan las 2 vent ocupadas
			wait();
		}
		ocupadas ++;//Sale del while porque si hay sitio y ocupa las ventanillas
		System.out.println("\n" + Thread.currentThread().getName() + " Esta siendo atendido.\n"
				+ "Plazas Ocupadas->" + ocupadas);
	}
	public synchronized void salir() {
		ocupadas --;
		System.out.println("\n" + Thread.currentThread().getName() + " Sale de la farmacia.\n"
				+ "Plazas Libres-> " + (capacidad - ocupadas));
		notifyAll(); //notifica a los otros para que entren
	}
}
