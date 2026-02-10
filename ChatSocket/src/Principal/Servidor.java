package Principal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor{
	Scanner sc = new Scanner(System.in);
	private ServerSocket serverSocket;
	private Socket socket;
	private DataInputStream bufferdeentrada=null;
	private DataOutputStream bufferdesalida=null;
	final String COMANDO_TERMINACION = "salir()";
	
	public void levantarConexion(int puerto) {
		try {
			serverSocket = new ServerSocket(puerto);
			System.out.println("Esperando conexion entrante en el puerto " +String.valueOf(puerto));
			socket = serverSocket.accept();
		} catch (Exception e) {
			System.out.println("Error en la conexión " + e.getMessage());
			System.exit(0);
		}
	}
	
	public void flujos() {
		try {
			bufferdeentrada = new DataInputStream(socket.getInputStream());
			bufferdesalida = new DataOutputStream(socket.getOutputStream());
			bufferdesalida.flush();
		} catch (IOException e) {
			System.out.println("Error en la apertura de flujos. ");
		}
	}
	
	public void recibirDatos() {
		String cadena = "";
		try {
			do {
				cadena=(String)bufferdeentrada.readUTF();
				System.out.println("\n[Cliente] => " + cadena);
				System.out.println("\n[Usted] => " );
			} while (!cadena.equals(COMANDO_TERMINACION));
		} catch (IOException e) {
			cerrarConexion();
		}
	}
	
	public void escribirDatos() {
		while (true) {
			System.out.println("[Usted] =>");
			enviar(sc.nextLine());
		}
	}
	
	public void enviar(String cadena) {
		try {
			bufferdesalida.writeUTF(cadena);
		} catch (IOException e) {
			System.out.println("Error al enviar los datos: " + e.getMessage());
		}
	}
	
	public void ejecutarConexion(int puerto) {
		Thread hilo = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					try {
						levantarConexion(puerto);
						flujos();
						recibirDatos();
					}finally {
						cerrarConexion();
					}
					
				}
				
			}
			
		});
		hilo.start();
	}
	
	public void cerrarConexion() {
		try {
			bufferdeentrada.close();
			bufferdesalida.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("Error al cerrar conexiones ");
		} finally {
			System.out.println("Conversación finalizada");
			System.exit(0);
		}
	}
	

	public static void main(String[] args) {
		Servidor s = new Servidor();
		System.out.print("Indique el puerto a conectar[5500]: ");
		String puerto = s.sc.nextLine();
		if(puerto.length()<=0) puerto = "5500";
		s.levantarConexion(Integer.valueOf(puerto));
		s.escribirDatos();
		
	}

	

}
