package Clases;

public class JugadorParImpar extends Jugador {
	protected boolean jugamosApares;
	
	public JugadorParImpar(long saldo, Banca banca) {
		super(saldo, banca);
	}
	
	public boolean esGanador(int num) {
		if (num == 0) return false;
		if(num%2==0 && jugamosApares) return true;
		if(num%2!=0 && !jugamosApares) return true;
		return false;
	}

	@Override
	public void comunicarNumero(int numero) {
		if(esGanador(numero)) {
			System.out.println(this.nombreHilo + " Gana 20 euros por acertar.");
			banca.restarSaldo(20);
			this.sumarSaldo(20);
		}
		System.out.println(this.nombreHilo + " se queda con un saldo de " + saldo);
		apuestaRealizada = false;
	}

	@Override
	public void hacerApuesta() {
		if(!banca.aceptarApuestas()) return;
		if(apuestaRealizada) return;
		if(generador.nextBoolean()) {
			jugamosApares = true;
		}else {
			jugamosApares = false;
		}
		banca.sumarSaldo(10);
		restarSaldo(10);
		apuestaRealizada = true;
		banca.aceptarApuesta(this);
	}

}
