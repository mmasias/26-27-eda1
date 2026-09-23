public class Cliente {

    private Cliente anterior;
    private Cliente siguiente;
    private Console console;

    public Cliente() {
        console = new Console();
    }

    public Cliente obtenerAnterior() {
        return anterior;
    }

    public Cliente obtenerSiguiente() {
        return siguiente;
    }

    public void establecerAnterior(Cliente anterior) {
        this.anterior = anterior;
    }

    public void establecerSiguiente(Cliente siguiente) {
        this.siguiente = siguiente;
    }

    public void mostrar() {
        console.write("[Cliente]");
    }
}