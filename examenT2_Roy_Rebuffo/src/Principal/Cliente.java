package Principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	
	public void iniciarChat(BufferedReader bfr, PrintWriter pw) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Introduce tu nombre de usuario: ");
		String nombre = sc.nextLine();
		
		pw.println(nombre);
		pw.flush();
		
		String saludo = bfr.readLine();
		System.out.println("SERVIDOR >> " + saludo);
		
		String mensaje;
		while (true) {
			System.out.print("Tú >> ");
			mensaje = sc.nextLine();
			
			pw.println(mensaje);
			pw.flush();
			
			if (mensaje.equalsIgnoreCase("SALIR")) {
				System.out.println("Cerrando conexión...");
				break;
			}
			
			// Recibimos la respuesta del servidor
			String respuesta = bfr.readLine();
			System.out.println("SERVIDOR >> " + respuesta);
		}
		sc.close();
	}

	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		InetSocketAddress direccion = new InetSocketAddress("localhost", 5000);
		Socket conexion = new Socket();
		
		try {
			conexion.connect(direccion);
			
			BufferedReader bfr = Utilidades.getFlujoLectura(conexion);
			PrintWriter pw = Utilidades.getFlujoEscritura(conexion);
			
			cliente.iniciarChat(bfr, pw);
			
			pw.close();
			bfr.close();
			conexion.close();
			
		} catch (IOException e) {
			System.out.println("No se pudo conectar con el servidor. ¿Está encendido?");
		}
	}
}