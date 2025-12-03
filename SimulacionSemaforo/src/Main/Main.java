package Main;

import Clases.Coche;
import Clases.Cruce;

public class Main {

    public static void main(String[] args) {
        final int N_COCHES = 10;
        
        // 1. Crear el cruce/semáforo (el hilo monitor)
        Cruce cruce = new Cruce(N_COCHES);
        
        // 2. Iniciar los hilos Coches (se bloquearán en espera)
        for (int i = 0; i < N_COCHES; i++) {
            new Coche(cruce, i).start();
        }
        
        // 3. Iniciar el ciclo del semáforo.
        cruce.start();
    }
}