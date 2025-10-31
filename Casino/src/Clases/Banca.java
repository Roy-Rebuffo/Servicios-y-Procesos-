package Clases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Banca {
	public enum Estado { INICIO, ACEPTANDO_APUESTAS, RULETA_GIRANDO,
		PAGANDO_APUESTAS, EN_BANCARROTA};
		
	/*ATRIBUTOS*/
	protected long saldo; // saldo de la banca
	protected int numeroGanador;
	protected boolean enBancarrota;
	protected boolean sePuedenHacerApuestas;
	protected Random generador;
	private Estado estadoRuleta;
	private ArrayList<Jugador> jugadores;
	
	/*CONSTRUCTOR*/
	public Banca(long saldo) {
		super();
		this.saldo = saldo;
		enBancarrota = false;
		estadoRuleta = Estado.INICIO;
		generador = new Random();
		jugadores = new ArrayList<Jugador>();
	}
	
	public synchronized boolean enBancarrota() {
		return this.enBancarrota;
	}
	
	public synchronized void sumarSaldo(long cantidad) {
		saldo += cantidad;
	}
	
	public synchronized void restarSaldo(long cantidad) {
		if(saldo - cantidad <= 0) {
			saldo = 0;
			estadoRuleta = Estado.EN_BANCARROTA;
			return;
		}
		saldo -=cantidad;
	}
	public synchronized void aceptarApuesta(Jugador j) {
		if(estadoRuleta == Estado.ACEPTANDO_APUESTAS) jugadores.add(j);
	}
	public synchronized boolean aceptarApuestas() {
		if(estadoRuleta == Estado.ACEPTANDO_APUESTAS) return true;
		return false;
	}
	
	public void comunicarNumeroGanador(int numero) {
		for (Jugador j : jugadores) j.comunicarNumero(numero);
		/*Una vez comunicadas todas las apuestas, borramos el ArrayList*/
		jugadores.clear();
	}
	
	public void girarRuleta() throws InterruptedException {
		int segundosAzar;
		System.out.println(" ¡¡Empieza el juego!!");
		while (estadoRuleta != Estado.EN_BANCARROTA) {
			estadoRuleta = Estado.ACEPTANDO_APUESTAS;
			segundosAzar=1+generador.nextInt(3);
			/*Se eligen unos milisegindos al azar para que los jugadores apuesten*/
			System.out.println("Hagan juego. Tienen ustedes " + segundosAzar+(segundosAzar > 1 ? " Segundos" : " Segundo") +
					" segundos para hacer sus apuestas");
			Thread.sleep(1000*segundosAzar);
			System.out.println("Ya no se admiten apuestas...");
			estadoRuleta = Estado.RULETA_GIRANDO;
			Thread.sleep(3000);
			numeroGanador = generador.nextInt(37);
			System.out.println("El número ganador es: " + numeroGanador);
			estadoRuleta = Estado.PAGANDO_APUESTAS;
			this.comunicarNumeroGanador(numeroGanador);
			System.out.println("El saldo del banco es ahora: " + saldo);
		}
	}
	
	public void simular(int nJugadoresClasicos, int nJugadoresParImpar, int nJugadoresMartinGala) throws InterruptedException {
		Thread[] hJClasicos = new Thread[nJugadoresClasicos];
		for (int i = 1; i < nJugadoresClasicos; i++) {
			hJClasicos[i] =new Thread( new JugadorClasico(1000, this));
			hJClasicos[i].setName("Jugador Clásico " + i);
			hJClasicos[i].start();
		}
		/**********************************************************/
		Thread [] hJParImpar = new Thread [nJugadoresParImpar];
		for (int i = 1; i < nJugadoresParImpar; i++) {
			hJParImpar[i] = new Thread(new JugadorParImpar(1000,this)); 
			hJParImpar[i].setName("Jugador Par Impar " + i);
			hJParImpar[i].start();
		}
		/**********************************************************/
		Thread [] hJMartinGala = new Thread [nJugadoresMartinGala];
		for (int i = 1; i < nJugadoresMartinGala; i++) {
			hJMartinGala[i] = new Thread(new JugadorMartinGala(1000,this));
			hJMartinGala[i].setName("Jugador MartinGala " + i);
			hJMartinGala[i].start();
		}
		this.girarRuleta();
	}
	/**************************************************************/

	public static void main(String[] args) throws InterruptedException {
		Banca b = new Banca(50000);
		b.simular(5,5,5);
	}
	
}
