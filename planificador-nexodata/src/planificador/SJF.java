package planificador;

public class SJF extends Planificador {

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
        Proceso masCorto = colaListos.get(0);
        for (Proceso p : colaListos) {
            if (p.rafaga < masCorto.rafaga) {
                masCorto=p;
            }
        }
        colaListos.remove(masCorto);
        return masCorto;
    }
}