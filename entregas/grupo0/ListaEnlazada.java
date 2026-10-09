class ListaEnlazada {
    private Nodo cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
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
        Nodo dummy = new Nodo(-1);
        dummy.siguiente = cabeza;
        Nodo actual = dummy;
        int pasos = 0;
        while (actual.siguiente != null && pasos < posicion) {
            actual = actual.siguiente;
            pasos++;
        }
        Nodo nuevoNodo = new Nodo(dato);
        nuevoNodo.siguiente = actual.siguiente;
        actual.siguiente = nuevoNodo;
        cabeza = dummy.siguiente;
    }

    public void insertarEnPosicionSinDummy(int posicion, int dato) {
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

    public void eliminarPorValor(int valor) {
        Nodo dummy = new Nodo(-1);
        dummy.siguiente = cabeza;
        Nodo actual = dummy;
        while (actual.siguiente != null) {
            if (actual.siguiente.dato == valor) {
                actual.siguiente = actual.siguiente.siguiente;
            } else {
                actual = actual.siguiente;
            }
        }
        cabeza = dummy.siguiente;
    }

    public void eliminarPorValorSinDummy(int valor) {
        while (cabeza != null && cabeza.dato == valor) {
            cabeza = cabeza.siguiente;
        }

        if (cabeza == null) {
            return;
        }

        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.dato == valor) {
                actual.siguiente = actual.siguiente.siguiente;
            } else {
                actual = actual.siguiente;
            }
        }
    }

    public void eliminarRepetidos() {
        Nodo dummy = new Nodo(-1);
        dummy.siguiente = cabeza;
        Nodo actual = dummy;

        while (actual.siguiente != null && actual.siguiente.siguiente != null) {
            if (actual.siguiente.dato == actual.siguiente.siguiente.dato) {
                int valorRepetido = actual.siguiente.dato;
                while (actual.siguiente != null && actual.siguiente.dato == valorRepetido) {
                    actual.siguiente = actual.siguiente.siguiente;
                }
            } else {
                actual = actual.siguiente;
            }
        }

        cabeza = dummy.siguiente;
    }

    public void eliminarRepetidosSinDummy() {
        while (cabeza != null && cabeza.siguiente != null && cabeza.dato == cabeza.siguiente.dato) {
            int valorRepetido = cabeza.dato;

            while (cabeza != null && cabeza.dato == valorRepetido) {
                cabeza = cabeza.siguiente;
            }
        }

        if (cabeza == null || cabeza.siguiente == null) {
            return;
        }

        Nodo actual = cabeza;
        while (actual.siguiente != null && actual.siguiente.siguiente != null) {
            if (actual.siguiente.dato == actual.siguiente.siguiente.dato) {
                int valorRepetido = actual.siguiente.dato;
                while (actual.siguiente != null && actual.siguiente.dato == valorRepetido) {
                    actual.siguiente = actual.siguiente.siguiente;
                }
            } else {
                actual = actual.siguiente;
            }
        }
    }
}
