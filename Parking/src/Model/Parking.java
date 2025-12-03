package Model;

public class Parking {
	private int espacios = 3;
	private int ocupadas = 0;
	private boolean libre = false;
	
	public boolean estaLibre() {
		return ocupadas < espacios;
	}
	
	public synchronized void entrar() throws InterruptedException {
		while(ocupadas==espacios) {//no hay sitio
			wait();
		}
		ocupadas ++;//Sale del while porque si hay sitio y ocupa las plazas
		System.out.println("\n" + Thread.currentThread().getName() + " Entra en el parking.\n"
				+ "Plazas Ocupadas->" + ocupadas);
		
	}
	public synchronized void salir() {
		ocupadas--;//aumentan los espacios cuando un coche sale
		System.out.println("\n" + Thread.currentThread().getName() + " Sale del parking.\n"
				+ "Plazas Libres-> " + (espacios - ocupadas));
		notifyAll(); //notifica a los otros para que entren
	}
}
