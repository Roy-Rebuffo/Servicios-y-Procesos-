package Principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

public class Cliente {
	public void verificarCadenas(BufferedReader bfr,PrintWriter pw) throws IOException	{
		//Numero de lineas que queremos pasar
		pw.println(2);
		//Datos que le pasamos al hilo conexion
		pw.println("1000;10;3");
		pw.println("5000;5;4");
		//Los enviamos al hilo
		pw.flush();
		
		//Obtenemos los datos calculados en hiloConexion y los leemos
		String suma1=bfr.readLine();//Como solo le pasamos 2, solo necesitamos 2 "suma", si fueran mas tambien hay que añadir mas
		String suma2=bfr.readLine();
		
		//Mostramos los datos en consola
		System.out.println(suma1);
		System.out.println(suma2);
	}
	public static void main(String[] args) {
		Cliente cliente=new Cliente();
		InetSocketAddress direccion=new InetSocketAddress("localhost", 9876);
		Socket conexion=new Socket();
		try {
			conexion.connect(direccion);
			BufferedReader bfr=Utilidades.getFlujoLectura(conexion);
			PrintWriter pw=Utilidades.getFlujoEscritura(conexion);
			cliente.verificarCadenas(bfr, pw);
			pw.close();
			bfr.close();
			conexion.close();			
		} catch (IOException e) {
			//Quiza el servidor no está encendido
			//Quizá lo esté pero su cortafuegos
			//impide conexiones
			//...
		}
		
		

	}

}
