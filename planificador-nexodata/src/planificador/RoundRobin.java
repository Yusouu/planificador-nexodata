package planificador;

public class RoundRobin extends Planificador {

    private int quantum;

    public RoundRobin(int quantum) {
        this.quantum = quantum;
    }

    @Override
    protected void anadirACola(Proceso p) {
        colaListos.add(p);
    }

    @Override
    protected boolean debeDesalojar() {
        return tiempoEnCpuActual >= quantum;
    }

    @Override
    protected Proceso elegirSiguiente() {
        return colaListos.remove(0);
    }
}