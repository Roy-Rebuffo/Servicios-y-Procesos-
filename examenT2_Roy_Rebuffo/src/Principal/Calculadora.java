package Principal;

public class Calculadora {
	
	public static String procesarMensaje(String mensaje) {
		
		String mayusculas = mensaje.toUpperCase();
		int longitud = mensaje.length();
		
		return mayusculas + " (Longitud: " + longitud + ")";
	}
}