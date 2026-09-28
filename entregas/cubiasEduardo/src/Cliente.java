public class Cliente {

    private boolean tienePrioridad;
    private int minutoLlegada;
    private Cliente adelante;
    private Console console;

    public Cliente(boolean tienePrioridad, int minutoLlegada) {
        this.tienePrioridad = tienePrioridad;
        this.minutoLlegada = minutoLlegada;
        this.adelante = null;
        this.console = new Console();
    }

    public boolean tienePrioridad() {
        return tienePrioridad;
    }

    public int obtenerMinutoLlegada() {
        return minutoLlegada;
    }

    public boolean tieneAdelante() {
        return adelante != null;
    }

    public Cliente obtenerAdelante() {
        return adelante;
    }

    public void conectarAdelante(Cliente otro) {
        this.adelante = otro;
    }

    public void desconectarAdelante() {
        this.adelante = null;
    }

    public boolean esElMismo(Cliente otro) {
        return this == otro;
    }

    public void mostrar() {
        if (tienePrioridad) {
            console.write("[*P*]");
        } else {
            console.write("[ C ]");
        }
    }
}
