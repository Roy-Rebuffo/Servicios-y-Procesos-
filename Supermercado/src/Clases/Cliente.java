package Clases;

import java.util.List;

public class Cliente{
	private String nombre;
	private List<Integer>tiemposProductoMs;
	
	public Cliente(String nombre, List<Integer> tiemposProductoMs) {
		super();
		this.nombre = nombre;
		this.tiemposProductoMs = tiemposProductoMs;
	}

	public String getNombre() {
		return nombre;
	}

	public List<Integer> getTiemposProductoMs() {
		return tiemposProductoMs;
	}
	
}
