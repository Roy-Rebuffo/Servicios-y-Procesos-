package Tipos;
//Creamos la clase Tarea que extiende a Thread (Hilos)
//creamos el metodo run
//hacemos un bucle de 10
//Creamos objetos Tarea

public class Tipo1 {

	public static void main(String[] args) {
		Tarea tarea1 = new Tarea();
		Tarea tarea2 = new Tarea();
		
		tarea1.start();
		tarea2.start();
		
		System.out.println("Soy el hilo principal y sigo haciendo mi trabajo");
		System.out.println("Soy el fin del hilo principal");
	}
}

class Tarea extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Soy un hilo y esto es lo que hago "
					+ i +" " + getName());
		}
	}
}
