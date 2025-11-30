package Main;

import Clases.Banco;
import Clases.Cliente;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		final int INGRESA_100 = 40;
		final int INGRESA_50 = 20;
		final int INGRESA_20 = 60;
		
		final int RETIRA_100 = 40;
		final int RETIRA_50 = 20;
		final int RETIRA_20 = 60;
		
		int total_hilos = INGRESA_100 +
				INGRESA_50 +
				INGRESA_20 +
				RETIRA_100 +
				RETIRA_50 +
				RETIRA_20;
		
		Banco banco = new Banco();
		
		Cliente[] c = new Cliente[total_hilos];
		
		int index = 0;
		
		for (int i = 0; i < INGRESA_100; i++) {
			c[index++] = new Cliente(banco,100,true);
		}
		for (int i = 0; i < INGRESA_50; i++) {
			c[index++] = new Cliente(banco,50,true);
		}
		for (int i = 0; i < INGRESA_20; i++) {
			c[index++] = new Cliente(banco,20,true);
		}
		
		for (int i = 0; i < RETIRA_100; i++) {
			c[index++] = new Cliente(banco,100,false);
		}
		for (int i = 0; i < RETIRA_50; i++) {
			c[index++] = new Cliente(banco,50,false);
		}
		for (int i = 0; i < RETIRA_20; i++) {
			c[index++] = new Cliente(banco,20,false);
		}
		//Iniciamos los hilos y hacemos join para que cuando terminen, se ejecute el ultimo "hilo del main"
		for (Cliente cliente : c) {
			cliente.start();
			cliente.join();
		}
		
		System.out.println("saldo final => " + banco.getSaldo());
	}

}
