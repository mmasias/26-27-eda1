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

    private Nodo nodoEn(int indice) {
        Nodo actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual;
    }

    public int obtener(int indice) {
        return nodoEn(indice).dato;
    }

    public void establecer(int indice, int dato) {
        nodoEn(indice).dato = dato;
    }

    @Override
    public String toString() {
        String texto = "[";
        Nodo actual = cabeza;
        while (actual != null) {
            texto += actual.dato;
            if (actual.siguiente != null) {
                texto += ", ";
            }
            actual = actual.siguiente;
        }
        return texto + "]";
    }
}