package Clases;

public class JugadorClasico extends Jugador {
	int numeroElegido;
	
	public JugadorClasico(long saldo, Banca banca) {
		super(saldo, banca);
	}

	@Override
	public void comunicarNumero(int numero) {
		if (numero == this.numeroElegido) {
			System.out.println(this.nombreHilo + " Gana 36 veces lo jugado. 360€");
			banca.restarSaldo(360); // Como gana le resta a la banca
			sumarSaldo(360); // Y se lo suma al propio jugador
		}
		System.out.println(this.nombreHilo + " tiene un saldo de " + this.saldo);
		this.apuestaRealizada  = false;
	}

	@Override
	public void hacerApuesta() {
		if(!banca.aceptarApuestas()) return;
		if(apuestaRealizada) return;
		/*Elegimos un numero al azar entre 1 y 36*/
		this.numeroElegido = 1 + generador.nextInt(36); // El 0 no puede ser porque si cae ahi gana la banca
		banca.sumarSaldo(10);
		restarSaldo(10);
		apuestaRealizada = true;
		banca.aceptarApuesta(this);
	}

}
