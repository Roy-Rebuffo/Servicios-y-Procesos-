package Principal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloConexion implements Runnable {

	BufferedReader bfr;
	PrintWriter pw;
	Socket socket;

	public HiloConexion(Socket socket) {
		this.socket = socket;
	}
	
	public void procesarChat() throws IOException {
		// Lee el nombre del usuario 
		String nombreUsuario = bfr.readLine();
		pw.println("Bienvenido " + nombreUsuario + ", el servidor está listo");
		pw.flush();
		
		String mensajeCliente;
		boolean continuar = true;
		
		while (continuar) {
			mensajeCliente = bfr.readLine(); // Recibimos el mensaje del cliente
			
			// Si el cliente dice SALIR, cerramos 
			if (mensajeCliente.equalsIgnoreCase("SALIR")) {
				continuar = false;
			} else { 
				String respuesta = Calculadora.procesarMensaje(mensajeCliente);
				
				// Enviar respuesta al cliente
				pw.println(respuesta);
				pw.flush();
			}
		}
	}
	
	@Override
	public void run() {
		try {
			bfr = Utilidades.getFlujoLectura(this.socket);
			pw = Utilidades.getFlujoEscritura(this.socket);
			
			procesarChat();
			
		} catch (IOException e) {
			System.out.println("Hubo una interrupción");
		}
	}
}