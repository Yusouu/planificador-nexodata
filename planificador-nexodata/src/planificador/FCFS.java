package planificador;

public class FCFS extends Planificador {

    @Override
    protected void anadirACola(Proceso p) {
        colaListos.add(p);
    }

    @Override
    protected boolean debeDesalojar() {
        return false;
    }

    @Override
    protected Proceso elegirSiguiente() {
        return colaListos.remove(0);
    }
}