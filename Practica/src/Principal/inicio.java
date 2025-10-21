package Principal;

public class inicio {

	public static void main(String[] args) {
		Thread h1 = new Thread (new Tarea(" Tarea 1"));
		Thread h2 = new Thread (new Tarea(" Tarea 2"));
		
		h1.start();
		try {
			h1.join();
		} catch (InterruptedException e ) {
			e.printStackTrace();
		}
		h2.start();
		try {
			h2.join();
		} catch (InterruptedException e ) {
			e.printStackTrace();
		}
	}

}

class Tarea implements Runnable{
	private String nombre;
	private boolean activado;
	
	
	
	public Tarea(String nombre) {
		super();
		this.nombre = nombre;
		this.activado = activado;
	}

	public void desactivar() {
		this.activado = activado;
	}


	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Soy el hilo " + i + " de la" +  nombre);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
