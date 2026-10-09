class ListaEnlazada {
    private Nodo cabeza;
    private Console console;

    public ListaEnlazada() {
        cabeza = null;
        console = new Console();
    }

    public void imprimirLista() {
        Nodo actual = cabeza;
        while (actual != null) {
            console.write(actual.dato + " -> ");
            actual = actual.siguiente;
        }
        console.writeln("null");
    }

    public void insertarEnPosicion(int posicion, int dato) {
        Nodo dummy = new Nodo(-1);
        dummy.siguiente = cabeza;
        Nodo actual = dummy;
        int pasos = 0;
        while (actual.siguiente != null && pasos < posicion) {
            actual = actual.siguiente;
            pasos++;
        }
        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
        cabeza = dummy.siguiente;
    }

    public void eliminarRepetidos() {
        Nodo dummy = new Nodo(-1);
        dummy.siguiente = cabeza;
        this.eliminarRepetidosTras(dummy);
        cabeza = dummy.siguiente;
    }

    public void eliminarRepetidosSinDummy() {
        while (cabeza != null && this.empiezaRepeticion(cabeza)) {
            cabeza = this.siguienteDistinto(cabeza);
        }

        if (cabeza == null) {
            return;
        }

        this.eliminarRepetidosTras(cabeza);
    }

    private void eliminarRepetidosTras(Nodo anterior) {
        assert anterior != null;

        Nodo actual = anterior;
        while (actual.siguiente != null) {
            if (this.empiezaRepeticion(actual.siguiente)) {
                actual.siguiente = this.siguienteDistinto(actual.siguiente);
            } else {
                actual = actual.siguiente;
            }
        }
    }

    private boolean empiezaRepeticion(Nodo nodo) {
        return nodo.siguiente != null && nodo.siguiente.dato == nodo.dato;
    }

    private Nodo siguienteDistinto(Nodo nodo) {
        Nodo actual = nodo;
        while (actual != null && actual.dato == nodo.dato) {
            actual = actual.siguiente;
        }
        return actual;
    }

    public static ListaEnlazada fusionar(ListaEnlazada a, ListaEnlazada b) {
        assert a != null && b != null;

        Nodo dummy = new Nodo(-1);
        Nodo ultimo = dummy;
        while (!a.estaVacia() || !b.estaVacia()) {
            ultimo.siguiente = conMenorCabeza(a, b).sacar();
            ultimo = ultimo.siguiente;
        }

        ListaEnlazada resultado = new ListaEnlazada();
        resultado.cabeza = dummy.siguiente;
        return resultado;
    }

    private static ListaEnlazada conMenorCabeza(ListaEnlazada a, ListaEnlazada b) {
        if (a.estaVacia()) {
            return b;
        }
        if (b.estaVacia()) {
            return a;
        }
        return a.cabeza.dato <= b.cabeza.dato ? a : b;
    }

    private boolean estaVacia() {
        return cabeza == null;
    }

    private Nodo sacar() {
        assert !this.estaVacia();

        Nodo primero = cabeza;
        cabeza = cabeza.siguiente;
        primero.siguiente = null;
        return primero;
    }
}
