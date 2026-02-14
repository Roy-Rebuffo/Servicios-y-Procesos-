package Principal;

public class ContadorVocales {
	
	public static int contador(String cad) {
		int suma = 0;
		String vocales = "AEIOUaeiou";
		for (int i = 0; i < cad.length(); i++) {
			char letra = cad.charAt(i);

			if (vocales.indexOf(letra) != -1) suma ++;
		}
		return suma;
	}
}
