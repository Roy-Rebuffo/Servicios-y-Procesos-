package Clases;

import java.util.Random;

public class Cliente extends Thread{
	private Random random;
	private Ventanilla ventanilla;
	private String nombre;
	
	
	
	public Cliente(Ventanilla ventanilla, String nombre) {
		super();
		this.random = new Random();
		this.ventanilla = ventanilla;
		this.nombre = nombre;
	}

	public void compra() throws InterruptedException {
		int tiempo = 1500 + random.nextInt(2000);//entre 1.5s y 3.5s
		Thread.sleep(tiempo);
	}
	
	@Override
	public void run() {
		try {
			ventanilla.entrar();
			compra();
			ventanilla.salir();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
