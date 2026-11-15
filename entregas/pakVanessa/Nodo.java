public class Nodo {

    private int dato;
    private Nodo siguiente;

    public Nodo(int numero, Nodo nodo) {
        dato = numero;
        siguiente = nodo;
    }

    public int getValor() {
        return dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo nodo) {
        siguiente = nodo;
    }
}