package planificador;

public class Proceso {
    public String nombre;
    public int llegada;
    public int rafaga;
    public int tiempoRestante;
    public EstadoProceso estado;

    // Métricas
    public int fin;
    public int respuesta;
    public boolean haentradocpu = false;

    public Proceso(String nombre, int llegada, int rafaga) {
        this.nombre = nombre;
        this.llegada = llegada;
        this.rafaga = rafaga;
        this.tiempoRestante = rafaga;
        this.estado = EstadoProceso.nuevo;
    }

    // Constructor de copia (el que usamos en el Main para clonar)
    public Proceso(Proceso p) {
        this.nombre = p.nombre;
        this.llegada = p.llegada;
        this.rafaga = p.rafaga;
        this.tiempoRestante = p.tiempoRestante;
        this.estado = p.estado;
        this.fin = p.fin;
        this.respuesta = p.respuesta;
        this.haentradocpu = p.haentradocpu;
    }
}