public class Nodo {
    private int valor;
    private Nodo siguiente;

    public Nodo(int valor, Nodo siguiente) {
        this.valor = valor;
        this.siguiente = siguiente;
    }

    public int getValor() {
        return this.valor;
    }

    public Nodo getSiguiente() {
        return this.siguiente;
    }
}