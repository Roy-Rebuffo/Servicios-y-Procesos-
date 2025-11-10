package Clases;

public class HiloJugador extends Thread {
    private NumeroOculto juego;

    public HiloJugador(String nombre, NumeroOculto juego) {
        super(nombre);
        this.juego = juego;
    }

    @Override
    public void run() {
        while (!juego.isAcertado()) {
            int propuesta = (int) (Math.random() * 101); // número entre 0 y 100
            int resultado = juego.propuestaNumero(propuesta, getName());

            if (resultado == -1 || resultado == 1) break; // ya acertó alguien o este hilo mismo
            try {
                Thread.sleep(100); // pausa breve para dar realismo y evitar spam
            } catch (InterruptedException e) {
                break;
            }
        }

        System.out.println("💤 " + getName() + " termina su ejecución.");
    }
}