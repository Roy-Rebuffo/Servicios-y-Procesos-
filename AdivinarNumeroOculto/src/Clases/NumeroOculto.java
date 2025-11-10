package Clases;

public class NumeroOculto {
    private int numeroSecreto;
    private boolean acertado = false;

    public NumeroOculto(int numeroSecreto) {
        this.numeroSecreto = numeroSecreto;
    }

    public synchronized int propuestaNumero(int num, String nombreHilo) {
        if (acertado) return -1; // a) ya se acertó

        if (num == numeroSecreto) {
            acertado = true;
            System.out.println("🎯 " + nombreHilo + " ha acertado el número: " + numeroSecreto);
            return 1; // b) acierto
        } else {
            System.out.println(nombreHilo + " propuso " + num + " (incorrecto)");
            return 0; // c) incorrecto
        }
    }

    public boolean isAcertado() {
        return acertado;
    }
}
