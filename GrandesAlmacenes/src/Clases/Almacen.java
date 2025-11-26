package Clases;

public class Almacen {
	private int nProductos;

	public Almacen(int nProductos) {
		super();
		this.nProductos = nProductos;
	}
	
	public boolean cogerProductos() {
		if(nProductos <=0) return false;
		
		nProductos --;
		return true;
		
		/*
		if(nProductos == 0) {
			System.out.println("No queda mas stock");
		}*/
	}
}
