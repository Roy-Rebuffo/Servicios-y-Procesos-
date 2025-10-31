package Clases;

public class JugadorMartinGala extends Jugador {
	private int cantidadAApostar;
	private int numeroElegido;

	public JugadorMartinGala(long saldo, Banca banca) {
		super(saldo, banca);
		cantidadAApostar = 1;
	}

	@Override
	public void comunicarNumero(int numero) {
		if (numero == 0 || numeroElegido!=numero) {
			System.out.println(nombreHilo + " pierde " + cantidadAApostar);
			cantidadAApostar *= 2;
		}else if(numeroElegido == numero){
			banca.restarSaldo(cantidadAApostar * 36);
			sumarSaldo(cantidadAApostar * 36);
			cantidadAApostar = 1;
		}
		System.out.println(this.nombreHilo + " queda con un saldo de: " + saldo);
		apuestaRealizada = false;
	}

	@Override
	public void hacerApuesta() {
		if(!banca.aceptarApuestas()) return;
		if(apuestaRealizada) return;
		
		numeroElegido = 1+generador.nextInt(36);
		banca.sumarSaldo(cantidadAApostar);
		restarSaldo(cantidadAApostar);
		apuestaRealizada = true;
		banca.aceptarApuesta(this);
	}

}
