package Clases;

import java.util.ArrayList;

public class Fabrica {
	private int turnoActual = 0;
	private String[] fases = {"Cortar", "Armar", "Empaquetar"};
	
	public String[] getFases() {
		return fases;
	}
	
	// Fabrica.java
	public synchronized void esperarTunrno(int numOperario) throws InterruptedException {
	    while(numOperario != turnoActual) { // Si no es mi turno, me duermo
	        wait();
	    }
	}

	public synchronized void pasarTurno(int numOperario) {
	    if(numOperario < fases.length - 1) {
	        turnoActual ++; // Pasa el testigo
	        notifyAll();    // Despierta a todos (solo el que tenga 'turnoActual' podrá pasar)
	    } else {
	        System.out.println("Trabajo completado");
	    }
	}
	
}
