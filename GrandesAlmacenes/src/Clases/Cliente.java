package Clases;

import java.util.Random;

public class Cliente extends Thread{
	Puerta puerta;
	Almacen almacen;
	String nombre;
	Random generador;
	final int N_INTENTOS = 10;
	
	public Cliente(Puerta puerta, Almacen almacen, String nombre) {
		super();
		this.puerta = puerta;
		this.almacen = almacen;
		this.nombre = nombre;
		generador = new Random(); //En que momento lo decide esto_?¿?¿?¿ Porque no esta en el constructor?¿
	}
	
	public void esperar() {
		try {
			Thread.sleep(generador.nextInt(100));
		} catch (InterruptedException ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public void run() {
		super.run();
		for (int i = 0; i < N_INTENTOS; i++) {
			if(!puerta.estaOcupada()) {
				if(puerta.intentarEntrar()) {
					esperar();
					puerta.liberarPuerta();
					if(almacen.cogerProductos()) {
						System.out.println(nombre + " >>> cogí un producto");
						return;
					}else {
						System.out.println(nombre + " >>> no cogí nada");
					}
					return;
				}
			}else {
				esperar();
			}
		}
		System.out.println(nombre + " Lo intenté >>>" + N_INTENTOS + " veces y no pude" );
	}
}