package Tipos;

public class Tipo1 {

	public static void main(String[] args) {
		Tarea tarea1 = new Tarea();
		Tarea tarea2 = new Tarea();
		tarea1.start();
		tarea2.start();
		
		System.out.println("Soy el hilo principal y sigo haciendo mi trabajo");
		System.out.println("Fin del hilo principal");
	}
}

class Tarea extends Thread{
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Soy un hilo y esto es lo que hago "+ i + " " + getName());
		}
	}
}
