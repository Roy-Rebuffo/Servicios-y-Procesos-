package conexiones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSuma extends ServerSocket{
	
	public ServidorSuma() throws IOException{
		super(12345);
		
	}
	
	public void aceptarPeticiones() {
		while(true) {
			try (
					Socket cliente = accept();
					DataInputStream dis = new DataInputStream(cliente.getInputStream());
					DataOutputStream dos = new DataOutputStream(cliente.getOutputStream())){
				//Capturamos los datos que nos envia el cliente
				double n1 = dis.readDouble();
				double n2 = dis.readDouble();
				//Sumamos los datos y los devolvemos al cliente
				dos.writeDouble(n1 + n2);
				//La conexión se cerrará em cuanto termine el bloque try-con-recursos
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		//Conectamos al servidor para escuchar las solicitudes
		try {
			ServidorSuma servidor = new ServidorSuma();
			servidor.aceptarPeticiones();
			servidor.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
