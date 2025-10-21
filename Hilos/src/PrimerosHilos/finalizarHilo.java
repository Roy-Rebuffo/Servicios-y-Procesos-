package PrimerosHilos;
/*
Resumen paso a paso:
4) Flujo temporal del programa (resumen)
   - main crea y arranca `workerThread`.
   - `workerThread` imprime "Empezando a dormir..." y entra en `sleep(5000)`.
   - main duerme 2s, luego imprime y llama a `workerThread.interrupt()`.
   - la interrupción hace que el `sleep(5000)` del `workerThread` lance `InterruptedException`.
   - `workerThread` entra en el `catch`, imprime "Fue interrumpido", luego ejecuta `finally`
     y termina.
   - El hilo principal continúa (termina el `main` tras la llamada a `interrupt()`).
*/

public class finalizarHilo {

	public static void main(String[] args) throws InterruptedException {
		//Hilo que queremos interrumpir
		Thread workerThread = new Thread (new WorkerTask(), "Trabajo");
		workerThread.start();
		//Esperamos un poco y luego interrumpimos el hilo
		Thread.sleep(2000); // Espera 2 segundos
		System.out.println("Hilo principal: Interrumpiendo el hilo de trabajo");
		workerThread.interrupt();
	}
}

class WorkerTask implements Runnable{
	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName() + ": Empezando a dormir...");
			Thread.sleep(5000);//Hilo entra en estado de sleep por 5 segundos
			System.out.println(Thread.currentThread().getName() + ": Desperté normalmente...");
		} catch (InterruptedException e) {
			//Si se reciba la interrupción, se lanza la excepción
			System.err.println(Thread.currentThread().getName() + ": Fue interrumpido ");
			//Limpiar o hacer otras acciones necesarias
		}finally {
			System.out.println(Thread.currentThread().getName() + ": Finalizando.");
		}
	}
}
