package Clases;

public class Productor extends Thread{
	private Buffer buffer;

	public Productor(Buffer buffer) {
		super();
		this.buffer = buffer;
	}
	String matriculas [] = {"1111", "2222", "3333", "4444"};
	String marcas[] = {"Renault","BMW","Mercedes", "Seat"};
	String modelos [] = {"Clio", "Serie 1", "Clase A", "Leon"};
	
	@Override
	public void run() {
		super.run();
		try {
			for (int i = 0; i < 4; i++) {
				Coche c = new Coche(matriculas[i % matriculas.length],marcas[i % marcas.length], modelos[i % modelos.length]);
                buffer.producir(c);
                Thread.sleep(500); // simula tiempo de producción
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
