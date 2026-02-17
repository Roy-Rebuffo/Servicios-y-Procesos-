package Principal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	public void servir() {
		ServerSocket serverSocket;
		final int PUERTO = 5000;
		
		System.out.println("Servidor iniciado en puerto " + PUERTO + ". Esperando clientes...");
		
		try {
			serverSocket = new ServerSocket(PUERTO);
			while (true) {
				Socket conexion;
				conexion = serverSocket.accept();
				
				HiloConexion hiloConexion = new HiloConexion(conexion);
				Thread hilo = new Thread(hiloConexion);
				hilo.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Servidor servidor;
		servidor = new Servidor();
		servidor.servir();
	}
}