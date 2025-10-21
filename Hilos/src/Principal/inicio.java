package Principal;

public class inicio {

	public static void main(String[] args) {
		Thread hilo1 = new Thread(new Tarea(" Tarea 1"));
		Thread hilo2 = new Thread(new Tarea(" Tarea 2"));
		
		hilo1.start();
		try {
			//hasta que no termina el hilo 1 no continua al hilo 2
			hilo1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		hilo2.start();
		try {
			hilo2.join();
		} catch (InterruptedException e) {
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
		this.activado = true;
	}
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Soy el hilo " + i + nombre);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
