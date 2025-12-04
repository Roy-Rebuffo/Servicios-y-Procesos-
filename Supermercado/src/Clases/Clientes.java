package Clases;

import java.util.ArrayList;
import java.util.Random;

public class Clientes extends Thread{
	private String[] lista = {"Jamon","Queso","Leche"};
	private Random random;
	private long tiempoInicio;
	private ArrayList<Long> resultados = new ArrayList<Long>();
	private Cajera cajera;
	
	public Clientes(Cajera cajera) {
		super();
		this.random = new Random();
		this.cajera = cajera;
	}

	public synchronized void registrarCompra(int numCliente, int tiempoLlegada) {
		long total = (tiempoLlegada - tiempoInicio)/1000;
		resultados.add(total);
		System.out.println("El Cliente " + numCliente + " tarda " + total + " segundos");
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				cajera.intentarCobrar();
				
				long inicio = System.currentTimeMillis();
                System.out.println("🚗 " + getName() + " COBRANDO. Tiempo de inicio: " + inicio);
                
                cajera.tiempoEnProcesarProductos();
                
                cajera.terminarCobrar(getName());
			}
		} catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
	}
}
