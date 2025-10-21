package PrimerosHilos;

//creamos una variable estatica
//Creamos la clase RunnableImpl que implementa Runnable
//creamos el metodo run
//sumamos por 100 cada vez que run se ejecuta con start()
//vamos al main
//creamos los hilos
//les ponemos nombre
//lo corremos con start()


public class ejer01 {
	private static short num = 100;
	public static void main(String[] args) {
		Thread h2 = new Thread (new ejer01().new RunnableImpl());
		h2.setName("Hilo 2");
		Thread h1 = new Thread (new ejer01().new RunnableImpl());
		h1.setName("Hilo 1 ");
		
		h1.start();
		h2.start();
		System.out.println("Termino el hilo principal");
	}
	
	private class RunnableImpl implements Runnable{
		@Override
		public void run() {
			num +=100;
			System.out.println(Thread.currentThread().getName() +
					" ejecutandose, num: " + num);
		}
	}
}
