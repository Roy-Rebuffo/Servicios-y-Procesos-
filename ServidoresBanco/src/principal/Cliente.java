package principal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost", 12345);
			     DataInputStream dis = new DataInputStream(socket.getInputStream());
			     DataOutputStream dos = new DataOutputStream(socket.getOutputStream())){
			//Pedimos los datos al usuario
			Scanner teclado = new Scanner(System.in);
			System.out.println("Dime el capital: ");
			double capital = teclado.nextDouble();
			
			System.out.println("Dime el Interés anual: ");
			double ianual = teclado.nextDouble();
			
			System.out.println("Dime el nº de años del crédito: ");
			double años = teclado.nextDouble();
			
			dos.writeDouble(capital);
			dos.writeDouble(ianual);
			dos.writeDouble(años);
			
			//Nos devuelve la mensualidad
			double mensualidad = dis.readDouble();
			System.out.println("La mensualidad es: " + mensualidad);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
