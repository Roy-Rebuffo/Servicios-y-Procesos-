package Clases;

import java.util.ArrayList;

public class Fabrica {
	private int turnoActual = 0;
	private String[] fases = {"Cortar", "Armar", "Empaquetar"};
	
	public String[] getFases() {
		return fases;
	}
	
	public synchronized void esperarTunrno(int numOperario) throws InterruptedException {
		while(numOperario != turnoActual) {
			wait();
		}
	}
	
	public synchronized void pasarTurno(int numOperario) {
		if(numOperario < fases.length - 1) {
			turnoActual ++;
			System.out.println("\n Operario " + numOperario + " pasa turno a Operario " + turnoActual + "---");
			notifyAll();
		}else {
			System.out.println("Trabajo completado");
		}
	}
	
}
