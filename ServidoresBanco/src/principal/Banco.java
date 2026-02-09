package principal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Banco extends ServerSocket{

	public Banco() throws IOException {
		super(12345);
	}
	
	public double mensualidad(double capital, double ianual, double años) {
		double im = ianual/1200;
		double t = años*12;
		double mensualidad = (capital * im)/(1-Math.pow(1+im, -t));
		return mensualidad;
	}
	
	public void aceptarPeticiones() {
		while(true) {
			try (
					Socket cliente = accept();
					DataInputStream dis = new DataInputStream(cliente.getInputStream());
					DataOutputStream dos = new DataOutputStream(cliente.getOutputStream())){
				//Capturamos los datos que nos envia el cliente
				double capital = dis.readDouble();
				double ianual = dis.readDouble();
				double años = dis.readDouble();
				
				dos.writeDouble(mensualidad(capital, ianual, años));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		//Conectamos al servidor para escuchar las solicitudes
		try {
			Banco servidor = new Banco();
			servidor.aceptarPeticiones();
			servidor.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
