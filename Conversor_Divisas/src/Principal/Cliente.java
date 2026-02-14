package Principal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		try (
				Socket socket = new Socket("localhost",12345);
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream())){
			//Pedimos los datos al cliente
			Scanner sc = new Scanner(System.in);
			System.out.print("Dime el capital: ");
			double dinero = Double.valueOf(sc.nextLine());
			
			System.out.print("E/D(1), E/L(2), E/Y(3)");
			int codigo = sc.nextInt();
			
			//Pasamos datos al conversor (servidor)
			dos.writeDouble(dinero);
			dos.writeInt(codigo);
			
			//El conversor(servidor) nos devuelve la conversion
			double conversor = dis.readDouble();
			System.out.println("La conversion es: " + conversor);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
