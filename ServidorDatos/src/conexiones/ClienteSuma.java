package conexiones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteSuma {
	public static void main(String[] args) {
		//conectamos con el servidor
		try (Socket socket = new Socket("localhost", 12345);
			     DataInputStream dis = new DataInputStream(socket.getInputStream());
			     DataOutputStream dos = new DataOutputStream(socket.getOutputStream())){
			//Pedimos los datos al usuario
			Scanner teclado = new Scanner(System.in);
			System.out.println("Dame dos números: ");
			double n1 = teclado.nextDouble();
			double n2 = teclado.nextDouble();
			//mandamos los input(numeros escritos) al servidor
			dos.writeDouble(n1);
			dos.writeDouble(n2);
			//El servidor nos devuelve el resultado y lo recepcionamos
			double suma = dis.readDouble();
			System.out.println("La suma de los dos números es: " + suma);
			//el socket se cierra en cuanto termina el bloque try-con-recursos
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
