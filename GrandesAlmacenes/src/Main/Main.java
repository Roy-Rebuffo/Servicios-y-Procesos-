package Main;

import Clases.Almacen;
import Clases.Cliente;
import Clases.Puerta;

public class Main {

    public static void main(String[] args) {

       final int N_CLIENTES = 300;
       final int N_PRODUCTOS = 100;
       
       Cliente[] clientes = new Cliente[N_CLIENTES]; //Los hilos
       Almacen almacen = new Almacen(N_PRODUCTOS); //Total de productos 
       Puerta puerta = new Puerta();
       
       for (int i = 0; i < N_CLIENTES; i++) {
		clientes[i] = new Cliente(puerta,almacen, "Cliente " + i);
		clientes[i].start();
       }
    }
}

