package Clases;

public class Panaderia{
	private String pan;
	private boolean disponible;
	
	public synchronized void Hornear(String masa) {
		while(disponible) {
			try {
				wait();//deja esperando a la panaderia mientras un pan este disponible
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		this.pan = masa;
		System.out.println("Panadero hornea " + this.pan);
		this.disponible = true;
		notify();//Notifica que la barra ya esta disponible al metodo Consumir. se espera a ser consumida
	}
	public synchronized String Consumir() {
		while(!disponible) {
			try {
				wait();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println("El consumidor consume  " + this.pan);
		this.disponible = false;
		notify();//el pan es consumido y notifica que puede volver a hornear
		return this.pan;
	}
}
