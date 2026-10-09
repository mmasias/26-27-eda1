class ListaEnlazada {
    private Nodo cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }

    public void insertarEnPosicion(int posicion, int valor) {
        Nodo dummy = new Nodo(-1);
        dummy.establecerSiguiente(cabeza);
        Nodo actual = dummy;
        int pasos = 0;
        while (actual.obtenerSiguiente() != null && pasos < posicion) {
            actual = actual.obtenerSiguiente();
            pasos = pasos + 1;
        }
        Nodo nuevoNodo = new Nodo(valor);
        nuevoNodo.establecerSiguiente(actual.obtenerSiguiente());
        actual.establecerSiguiente(nuevoNodo);
        cabeza = dummy.obtenerSiguiente();
    }

    public void imprimirLista() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.obtenerValor() + " -> ");
            actual = actual.obtenerSiguiente();
        }
        System.out.println("null");
    }

    public void eliminarRepetidos() {
        Nodo dummy = new Nodo(-1);
        dummy.establecerSiguiente(cabeza);

        Nodo previo = dummy;
        Nodo actual = cabeza;

        while (actual != null) {
            if (actual.obtenerSiguiente() != null
                    && actual.obtenerValor() == actual.obtenerSiguiente().obtenerValor()) {
                int valorRepetido = actual.obtenerValor();
                while (actual != null && actual.obtenerValor() == valorRepetido) {
                    actual = actual.obtenerSiguiente();
                }
                previo.establecerSiguiente(actual);
            } else {
                previo = actual;
                actual = actual.obtenerSiguiente();
            }
        }

        cabeza = dummy.obtenerSiguiente();
    }

    public void eliminarRepetidosSinDummy() {
        while (cabeza != null && cabeza.obtenerSiguiente() != null
                && cabeza.obtenerValor() == cabeza.obtenerSiguiente().obtenerValor()) {
            int valorRepetido = cabeza.obtenerValor();
            while (cabeza != null && cabeza.obtenerValor() == valorRepetido) {
                cabeza = cabeza.obtenerSiguiente();
            }
        }

        if (cabeza == null) {
            return;
        }

        Nodo previo = cabeza;
        Nodo actual = cabeza.obtenerSiguiente();

        while (actual != null) {
            if (actual.obtenerSiguiente() != null
                    && actual.obtenerValor() == actual.obtenerSiguiente().obtenerValor()) {
                int valorRepetido = actual.obtenerValor();
                while (actual != null && actual.obtenerValor() == valorRepetido) {
                    actual = actual.obtenerSiguiente();
                }
                previo.establecerSiguiente(actual);
            } else {
                previo = actual;
                actual = actual.obtenerSiguiente();
            }
        }
    }

    public static ListaEnlazada fusionar(ListaEnlazada a, ListaEnlazada b) {
        ListaEnlazada resultado = new ListaEnlazada();
        Nodo dummy = new Nodo(-1);
        Nodo cola = dummy;

        Nodo nodoA = a.cabeza;
        Nodo nodoB = b.cabeza;

        while (nodoA != null && nodoB != null) {
            if (nodoA.obtenerValor() <= nodoB.obtenerValor()) {
                cola.establecerSiguiente(nodoA);
                nodoA = nodoA.obtenerSiguiente();
            } else {
                cola.establecerSiguiente(nodoB);
                nodoB = nodoB.obtenerSiguiente();
            }
            cola = cola.obtenerSiguiente();
        }

        if (nodoA != null) {
            cola.establecerSiguiente(nodoA);
        } else {
            cola.establecerSiguiente(nodoB);
        }

        resultado.cabeza = dummy.obtenerSiguiente();

        a.cabeza = null;
        b.cabeza = null;

        return resultado;
    }
}