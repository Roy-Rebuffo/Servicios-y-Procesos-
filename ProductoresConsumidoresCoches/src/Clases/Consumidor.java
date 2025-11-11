package Clases;

public class Consumidor extends Thread{
	private Buffer buffer;
	
	public Consumidor(Buffer buffer) {
		super();
		this.buffer = buffer;
	}

	@Override
	public void run() {
		super.run();
		try {
			for (int i = 0; i < 10; i++) {
				buffer.consumir();
				sleep((int)(Math.random()*1000));
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
