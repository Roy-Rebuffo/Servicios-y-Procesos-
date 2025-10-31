package Clases;

import java.util.Random;

public abstract class Jugador implements Runnable{
	protected long saldo;
	protected boolean enBancarrota;
	protected long cantidadApostada;
	protected boolean apuestaRealizada;
	protected Banca banca;
	protected String nombreHilo;
	protected Random generador;
	
	
	public Jugador(long saldo, Banca banca) {
		super();
		this.saldo = saldo;
		this.banca = banca;
		this.apuestaRealizada = false;
		this.generador = new Random();
	}

	public void sumarSaldo(long cantidad) {
		this.saldo += cantidad;
	}
	public void restarSaldo(long cantidad) {
		if(this.saldo - cantidad <= 0) {
			saldo = 0;
			this.enBancarrota = true;
			return;
		}
		this.saldo -=cantidad;
	}
	
	public boolean enBancarrota() {
		return this.enBancarrota;
	}
	/*Lo usa la banca para comunicarnos el nº ganador*/
	public abstract void comunicarNumero(int numero);
	
	public abstract void hacerApuesta();
	
	/*Todos los jugadores hacen lo mismo:
	 * Mientras que no esten en bancarrota y la banca tampoco lo este,
	 * hacemos apuestas. La banca nos dira el nº que haya salido y
	 * en ese momento (y si procede) incrementaremos el saldo.
	 */
	@Override
	public void run() {
		this.nombreHilo = Thread.currentThread().getName();
		while(!this.enBancarrota && !banca.enBancarrota()) {
			int msAzar;
			/*
			 * Mientras la ruleta no acepta apuestas,dormimos un periodo al
			 * azar.
			 * */
			while(!banca.aceptarApuestas()) {
				msAzar = generador.nextInt(500);
				
				try {
					Thread.sleep(msAzar);
				} catch (InterruptedException e) {
					return;
				}
			}
			hacerApuesta();
		}
		if(this.enBancarrota) {
			System.out.println(this.nombreHilo + " Me arruiné 😒");
			return;
		}
		if(banca.enBancarrota()) {
			System.out.println(this.nombreHilo + " hizo saltar la banca.");
		}
	}
	
}
