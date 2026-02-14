package Principal;

public class ContadorLetras {

	public static int contadorRepetidas(String cad) {
	    int contador = 0;

	    for (int i = 0; i < cad.length(); i++) {
	        char letra = cad.charAt(i);

	        if (cad.indexOf(letra) != cad.lastIndexOf(letra)) {

	            boolean yaContada = false;
	            for (int j = 0; j < i; j++) {
	                if (cad.charAt(j) == letra) {
	                    yaContada = true;
	                    break;
	                }
	            }

	            if (!yaContada) contador++;
	        }
	    }
	    return contador;
	}

}
