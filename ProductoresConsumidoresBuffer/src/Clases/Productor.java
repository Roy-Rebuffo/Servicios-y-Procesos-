package Clases;

public class Productor extends Thread{
	private Buffer buffer;

	public Productor(Buffer buffer) {
		super();
		this.buffer = buffer;
	}
	
	private int alea(int li, int ls) {
		return (int) (Math.random()*(ls - li))+li;
	}
	
	@Override
	public void run() {
		super.run();
		try {
			for (int i = 0; i < 10; i++) {
				buffer.producir(alea(500,5000));
				sleep((int)(Math.random()*1000));
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
