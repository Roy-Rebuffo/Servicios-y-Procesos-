package PrimerosHilos;

public class ejer01 {
	
	private static short num = 100;
	
	public static void main(String[] args) {
		
		Thread h2 = new Thread(new ejer01().new RunnableImpl());
		h2.setName("Hilo 2");
		Thread h1 = new Thread(new ejer01().new RunnableImpl());
		h1.setName("Hilo 1");
		
		h1.start();
		h2.start();
		//Termina antes el programa principal que el Hilo
		System.out.println("Termino el hilo principal");
	}
	
	private class RunnableImpl implements Runnable {
		//Runnable es un ainterfaz que nos permite reescribiendo
		//run sobre el hilo modificar su comportamiento
		
		@Override
		public void run() {
			num += 100;
			System.out.println(Thread.currentThread().getName() +
					" ejecutándose, num: " +num);
			//Thread.currentThread().getName() muestra el nombre del hilo en ejecución	
			}
	}

}
