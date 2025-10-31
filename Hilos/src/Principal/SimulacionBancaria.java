//Si atacamos con varios hilos el mismo objeto tenemos que usar el sincronizado, porque
//puede estar contaminado con otros hilos a lo largo del proceso.

package Principal;

public class SimulacionBancaria {

	public static void main(String[] args) throws InterruptedException {
		Cuenta cuenta = new Cuenta(100); // Inicializamos la cuenta con 100 euros
		final int C100 = 40;
		final int C50 = 20;
		final int C20 = 60;
		
		
		Thread[] hI100 = new Thread[C100]; // Hilos que ingresan 100
		Thread[] hR100 = new Thread[C100]; // Hilos que retiran 100
		
		Thread[] hI50 = new Thread[C50]; // Hilos que ingresan 50
		Thread[] hR50 = new Thread[C50]; // Hilos que retiran 50
		
		Thread[] hI20 = new Thread[C20]; // Hilos que ingresan 20
		Thread[] hR20 = new Thread[C20]; // Hilos que retiran 20
		
		for (int i = 0; i < C100; i++) {
			hI100[i] = new Thread(new HiloCliente(cuenta,100));
			hR100[i] = new Thread(new HiloCliente(cuenta,-100));
			hI100[i].start();
			hR100[i].start();
		}
		for (int i = 0; i < C50; i++) {
			hI50[i] = new Thread(new HiloCliente(cuenta,50));
			hR50[i] = new Thread(new HiloCliente(cuenta,-50));
			hI50[i].start();
			hR50[i].start();
		}
		for (int i = 0; i < C20; i++) {
			hI20[i] = new Thread(new HiloCliente(cuenta,20));
			hR20[i] = new Thread(new HiloCliente(cuenta,-20));
			hI20[i].start();
			hR20[i].start();
		}
		/**************************************************************************************/
		for (int i = 0; i < C100; i++) {
			hI100[i].join();
			hR100[i].join();
		}
		for (int i = 0; i < C50; i++) {
			hI50[i].join();
			hR50[i].join();
		}
		for (int i = 0; i < C20; i++) {
			hI20[i].join();
			hR20[i].join();
		}
		/**************************************************************************************/
		//Comprueba que el saldo vuelve a ser 100 después de todo esto
		if(cuenta.esSimulacionCorrecta()) {
			System.out.println("Simulación correcta");
		}else {
			System.err.println("Simulación incorrecta\n");
			System.out.println("La cuenta tiene un saldo de: " + cuenta.getSaldo());
		}
	}
}
/**************************************************************************************/
//TODO: Crear otra clase en otro módulo
//Paso 1
class Cuenta{
	private int saldo;
	private int saldoInicial;
	
	public Cuenta(int saldo) {
		super();
		this.saldo = saldo;
		this.saldoInicial = saldo;
	}

	public int getSaldo() { return saldo;}
		
	public synchronized void hacerMovimiento(int cantidad) {
		this.saldo += cantidad; // lo atacaremos con varios hilos
	}
	
	public synchronized boolean esSimulacionCorrecta() { //es lo que nos dice que la cuenta se ha quedado igual que estaba
		if(this.saldo == this.saldoInicial) return true;
		return false;
	}
}
/**************************************************************************************/
//TODO: Crear otra clase en otro módulo
//Paso 2
class HiloCliente implements Runnable {
	Cuenta cuenta;
	int cantidad;
	
	public HiloCliente(Cuenta cuenta, int cantidad) {
		super();
		this.cuenta = cuenta;
		this.cantidad = cantidad;
	}

	@Override
	public void run() {
		for(int i = 0 ; i<100;i++) {
			cuenta.hacerMovimiento(cantidad);
		}
	}
}