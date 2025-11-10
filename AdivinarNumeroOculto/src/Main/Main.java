package Main;

import Clases.HiloJugador;
import Clases.NumeroOculto;

public class Main {
    public static void main(String[] args) {
        int numero = (int) (Math.random() * 101);
        NumeroOculto juego = new NumeroOculto(numero);

        System.out.println("🔢 Se ha generado un número oculto entre 0 y 100.\n");

        // Crear y arrancar 10 hilos
        for (int i = 1; i <= 10; i++) {
            new HiloJugador("Jugador-" + i, juego).start();
        }
    }
}
