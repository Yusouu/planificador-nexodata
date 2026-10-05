package planificador;

import java.util.ArrayList;
import java.util.List;

public abstract class Planificador {

    protected List<Proceso> colaListos = new ArrayList<>();
    protected int tiempo = 0;
    protected int cambiosContexto = 0;
    protected Proceso procesoEnCpu = null;
    protected Proceso ultimoEjecutado = null;
    protected int tiempoEnCpuActual = 0;

    public void simular(List<Proceso> procesos) {
        int procesosTerminados = 0;
        int totalProcesos = procesos.size();

        while (procesosTerminados < totalProcesos) {
            for (Proceso p : procesos) {
                if (p.llegada == tiempo) {
                    p.estado = EstadoProceso.listo;
                    anadirACola(p);
                }
            }

            if (procesoEnCpu != null) {
                if (procesoEnCpu.tiempoRestante == 0) {
                    procesoEnCpu.estado = EstadoProceso.terminado;
                    procesoEnCpu.fin = tiempo;
                    procesoEnCpu = null;
                    procesosTerminados++;
                } else if (debeDesalojar()) {
                    procesoEnCpu.estado = EstadoProceso.listo;
                    anadirACola(procesoEnCpu);
                    procesoEnCpu = null;
                }
            }

            if (procesoEnCpu == null && !colaListos.isEmpty()) {
                Proceso siguiente = elegirSiguiente();

                if (ultimoEjecutado != null && ultimoEjecutado != siguiente) {
                    cambiosContexto++;
                }

                procesoEnCpu = siguiente;
                ultimoEjecutado = siguiente;
                procesoEnCpu.estado = EstadoProceso.ejecucion;

                if (!procesoEnCpu.haentradocpu) {
                    procesoEnCpu.respuesta = tiempo - procesoEnCpu.llegada;
                    procesoEnCpu.haentradocpu = true;
                }
                tiempoEnCpuActual = 0;
            }

            if (procesoEnCpu != null) {
                procesoEnCpu.tiempoRestante--;
                tiempoEnCpuActual++;
            }

            tiempo++;
        }
    }

    protected abstract void anadirACola(Proceso p);
    protected abstract boolean debeDesalojar();
    protected abstract Proceso elegirSiguiente();
}