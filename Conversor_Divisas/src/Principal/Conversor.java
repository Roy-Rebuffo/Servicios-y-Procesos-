package Principal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Conversor extends ServerSocket{
	
	//Constructor que luego servira de servidor 123456
	public Conversor() throws IOException {
		super(12345);
	}
	//Metodo para convertir las monedas
	public double conversor(double dinero, int codigo) {
		switch (codigo) {
		case 1:
			dinero = dinero * 1.19;
			break;
		case 2:
			dinero = dinero * 0.87;
			break;
		case 3:
			dinero = dinero * 181.67;
			break;
			
		default:
			System.out.println("No se puede convertir aun...");
			break;
		}
		
		return dinero;
	}
	
	public void aceptarPeticiones() {
		while (true) {
			try (
					Socket cliente = accept();
					DataInputStream dis = new DataInputStream(cliente.getInputStream());
					DataOutputStream dos = new DataOutputStream(cliente.getOutputStream())){
				//Capturamos los datos que nos envia el cliente
				double dinero = dis.readDouble();
				int codigo = dis.readInt();
				
				//Convertimos y pasamos datos al cliente
				dos.writeDouble(conversor(dinero, codigo));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
			Conversor servidor = new Conversor();
			servidor.aceptarPeticiones();
			servidor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
