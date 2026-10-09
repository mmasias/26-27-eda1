public class ListaEnlazada {
    private Nodo cabeza;
    private int cantidad;

    public ListaEnlazada() {
        this.cabeza = null;
        this.cantidad = 0;
    }

    public int tamano() {
        return cantidad;
    }

    public void agregarAlFinal(int dato) {
        Nodo dummy = new Nodo(-1);
        dummy.siguiente = cabeza;
        Nodo actual = dummy;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        actual.siguiente = new Nodo(dato);
        cabeza = dummy.siguiente;
        cantidad++;
    }
}