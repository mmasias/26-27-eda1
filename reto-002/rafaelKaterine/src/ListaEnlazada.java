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

    public void imprimirLista() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        }
        System.out.println("null");
    }
    
    public void eliminarRepetidos() {
        Nodo nodoDummy = new Nodo(0);
        nodoDummy.siguiente = cabeza;
        Nodo nodoAnterior = nodoDummy;

        while (nodoAnterior.siguiente != null) {
            Nodo nodoActual = nodoAnterior.siguiente;

            if (nodoActual.siguiente != null && nodoActual.dato == nodoActual.siguiente.dato) {
                while (nodoActual.siguiente != null && nodoActual.dato == nodoActual.siguiente.dato) {
                    nodoActual = nodoActual.siguiente;
                }
                nodoAnterior.siguiente = nodoActual.siguiente;
            } else {
                nodoAnterior = nodoAnterior.siguiente;
            }
        }

        cabeza = nodoDummy.siguiente;
    }

    public void eliminarRepetidosSinDummy() {
        while (cabeza != null && cabeza.siguiente != null && cabeza.dato == cabeza.siguiente.dato) {
            int valorRepetido = cabeza.dato;
            while (cabeza != null && cabeza.dato == valorRepetido) {
                eliminarAlPrincipio();
            }
        }
        Nodo nodoActual = cabeza;
        while (nodoActual != null && nodoActual.siguiente != null) {
            if (nodoActual.siguiente.siguiente != null && nodoActual.siguiente.dato == nodoActual.siguiente.siguiente.dato) {
                int valorRepetido = nodoActual.siguiente.dato;
                while (nodoActual.siguiente != null && nodoActual.siguiente.dato == valorRepetido) {
                    nodoActual.siguiente = nodoActual.siguiente.siguiente;
                }
            } else {
                nodoActual = nodoActual.siguiente;
            }
        }
    }



}