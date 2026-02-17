package Principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloConexion implements Runnable {

	BufferedReader bfr;
	PrintWriter pw;
	Socket socket;

	public HiloConexion(Socket socket) {
		this.socket = socket;
	}
	
	public void procesarLineas() throws IOException{
		//Recoje el numero de lineas que tiene que envia el cliente (2). La recibimos en string con el readLine()
		String lineaNumero = bfr.readLine();
		Integer nLineas = Integer.parseInt(lineaNumero);//La convertimos a numero para luego hacer el for
		
		String[] partes;//Separador
	
		double[] resultados; // Guardamos los resultados de haber calculado la mensualidad
		resultados = new double[nLineas]; // Seteamos el mismo tamaño que del nº de lineas que envia el cliente
		
		for (int i = 0; i < nLineas; i++) {
			String linea = bfr.readLine();//Leemos cada linea
			partes = linea.split(";"); // Separamos los datos por su delimitador
			
			//Obtenemos los datos
			double capital = Double.parseDouble(partes[0]);
			double años = Double.parseDouble(partes[1]);
			double interes = Double.parseDouble(partes[2]);
			
			//Calculamos
			double resultado = Calculadora.mensualidad(capital, años, interes);
			//Metemos la mensualidad calculada en el array
			resultados[i] = resultado;
		}
		
		for (int i = 0; i < nLineas; i++) {
			//Enviamos los datos 1 a 1 al cliente
			pw.println(resultados[i]);
			pw.flush();
		}
		
	}
	public void run() {
		try {

			bfr = Utilidades.getFlujoLectura(this.socket);
			pw = Utilidades.getFlujoEscritura(this.socket);
			procesarLineas();

		} catch (IOException e) {
			System.out.println("Hubo una interrupción");
		}

	}

}
