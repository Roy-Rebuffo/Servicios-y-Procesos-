package Clases;

public class Cliente extends Thread{
	private Banco banco;
	private int cantidad;
	private boolean ingreso;
	
	public Cliente(Banco banco, int cantidad, boolean ingreso) {
		super();
		this.banco = banco;
		this.cantidad = cantidad;
		this.ingreso = ingreso;
	}
	
	@Override
	public void run() {
		if(ingreso) {
			banco.ingresar(cantidad);
		}else {
			banco.retirar(cantidad);
		}
	}
}
