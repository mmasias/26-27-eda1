class ListaEnlazada {
    private Nodo cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }

    public void insertarAlPrincipio(int dato) {
        Nodo nuevoNodo = new Nodo(dato);
        nuevoNodo.siguiente = cabeza;
        cabeza = nuevoNodo;
    }

    public void eliminarAlPrincipio() {
        if (cabeza != null) {
            cabeza = cabeza.siguiente;
        }
    }

    public int obtenerElemento(int posicion) {
        Nodo actual = cabeza;
        for (int i = 0; i < posicion; i++) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    public void imprimirLista() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        }
        System.out.println("null");
    }

    public void insertarEnPosicion(int posicion, int dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (cabeza == null || posicion <= 0) {
            nuevoNodo.siguiente = cabeza;
            cabeza = nuevoNodo;
            return;
        }

        Nodo actual = cabeza;
        int pasos = 1;
        while (actual.siguiente != null && pasos < posicion) {
            actual = actual.siguiente;
            pasos++;
        }
        nuevoNodo.siguiente = actual.siguiente;
        actual.siguiente = nuevoNodo;
    }
}
