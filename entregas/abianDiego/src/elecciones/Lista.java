package elecciones;

class Lista {
    private static final String VALOR_DUMMY = "";

    private Nodo cabeza;
    private int longitud;

    Lista() {
        cabeza = null;
        longitud = 0;
    }

    void insertarAlFinal(String valor) {
        Nodo dummy = new Nodo(VALOR_DUMMY);
        dummy.siguiente = cabeza;
        Nodo actual = dummy;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        actual.siguiente = new Nodo(valor);
        cabeza = dummy.siguiente;
        longitud = longitud + 1;
    }

    void eliminar(String valor) {
        assert contiene(valor);
        Nodo dummy = new Nodo(VALOR_DUMMY);
        dummy.siguiente = cabeza;
        Nodo actual = dummy;
        while (!actual.siguiente.valor.equals(valor)) {
            actual = actual.siguiente;
        }
        actual.siguiente = actual.siguiente.siguiente;
        cabeza = dummy.siguiente;
        longitud = longitud - 1;
    }

    boolean contiene(String valor) {
        Nodo actual = cabeza;
        while (actual != null && !actual.valor.equals(valor)) {
            actual = actual.siguiente;
        }
        return actual != null;
    }

    int longitud() {
        return longitud;
    }

    String[] aArray() {
        String[] elementos = new String[longitud];
        Nodo actual = cabeza;
        int posicion = 0;
        while (actual != null) {
            elementos[posicion] = actual.valor;
            actual = actual.siguiente;
            posicion = posicion + 1;
        }
        return elementos;
    }
}
