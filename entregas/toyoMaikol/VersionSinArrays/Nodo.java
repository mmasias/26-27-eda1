public class Nodo {
    private Cliente cliente;
    private Nodo siguiente;

    public Nodo(Cliente cliente) {
        this.cliente = cliente;
        this.siguiente = null;
    }

    public Cliente obtenerCliente() { return cliente; }
    public void agregarCliente(Cliente cliente) { this.cliente = cliente; }
    public Nodo obtenerSiguiente() { return siguiente; }
    public void agregarSiguiente(Nodo siguiente) { this.siguiente = siguiente; }
}