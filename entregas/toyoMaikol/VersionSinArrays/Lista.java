public class Lista {
    private Nodo cabeza;
    private int tamano;

    public Lista() {
        this.cabeza = null;
        this.tamano = 0;
    }

    public int obtenerTamano() {
        return tamano;
    }

    public boolean estaVacia() {
        return tamano == 0;
    }

    public void encolar(Cliente cliente) {
        Nodo nuevo = new Nodo(cliente);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.obtenerSiguiente() != null) {
                actual = actual.obtenerSiguiente();
            }
            actual.agregarSiguiente(nuevo);
        }
        tamano++;
    }

    public Cliente desencolar() {
        if (cabeza == null) return null;
        Cliente atendido = cabeza.obtenerCliente();
        cabeza = cabeza.obtenerSiguiente();
        tamano--;
        return atendido;
    }

    public Cliente obtener(int indice) {
        if (indice < 0 || indice >= tamano) return null;
        Nodo actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.obtenerSiguiente();
        }
        return actual.obtenerCliente();
    }

    public void insertarCliente(int indice, Cliente cliente) {
        if (indice <= 0) {
            Nodo nuevo = new Nodo(cliente);
            nuevo.agregarSiguiente(cabeza);
            cabeza = nuevo;
            tamano++;
            return;
        }
        if (indice >= tamano) {
            encolar(cliente);
            return;
        }

        Nodo actual = cabeza;
        for (int i = 0; i < indice - 1; i++) {
            actual = actual.obtenerSiguiente();
        }
        Nodo nuevo = new Nodo(cliente);
        nuevo.agregarSiguiente(actual.obtenerSiguiente());
        actual.agregarSiguiente(nuevo);
        tamano++;
    }

    public void insertarClientePreferente(Cliente cliente) {
        if (cabeza == null) {
            encolar(cliente);
            return;
        }

        Nodo nodoActual = cabeza;
        int posicionAInsertar = 0;
        int posicionActual = 0;

        while (nodoActual != null) {
            if (nodoActual.obtenerCliente().tienePreferencia()) {
                posicionAInsertar = posicionActual + 1;
            }
            nodoActual = nodoActual.obtenerSiguiente();
            posicionActual++;
        }

        insertarCliente(posicionAInsertar, cliente);
    }

    public Cliente eliminarCliente(int indice) {
        if (indice < 0 || indice >= tamano || cabeza == null) return null;

        if (indice == 0) {
            return desencolar();
        }

        Nodo nodoActual = cabeza;
        for (int i = 0; i < indice - 1; i++) {
            nodoActual = nodoActual.obtenerSiguiente();
        }

        Nodo nodoAEliminar = nodoActual.obtenerSiguiente();
        nodoActual.agregarSiguiente(nodoAEliminar.obtenerSiguiente());
        tamano--;
        return nodoAEliminar.obtenerCliente();
    }
}