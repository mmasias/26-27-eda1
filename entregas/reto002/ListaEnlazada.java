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
        Nodo anterior = dummy;
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.siguiente != null && actual.siguiente.dato == actual.dato) {
                int repetido = actual.dato;
                while (actual != null && actual.dato == repetido) {
                    actual = actual.siguiente;
                }
                anterior.siguiente = actual;
            } else {
                anterior = actual;
                actual = actual.siguiente;
            }
        }
        cabeza = dummy.siguiente;
    }

    public void eliminarRepetidosSinDummy() {
        while (cabeza != null && cabeza.siguiente != null && cabeza.dato == cabeza.siguiente.dato) {
            int repetido = cabeza.dato;
            while (cabeza != null && cabeza.dato == repetido) {
                cabeza = cabeza.siguiente;
            }
        }

        if (cabeza == null) {
            return;
        }

        Nodo anterior = cabeza;
        Nodo actual = cabeza.siguiente;
        while (actual != null) {
            if (actual.siguiente != null && actual.siguiente.dato == actual.dato) {
                int repetido = actual.dato;
                while (actual != null && actual.dato == repetido) {
                    actual = actual.siguiente;
                }
                anterior.siguiente = actual;
            } else {
                anterior = actual;
                actual = actual.siguiente;
            }
        }
    }

    public static ListaEnlazada fusionar(ListaEnlazada a, ListaEnlazada b) {
        Nodo dummy = new Nodo(-1);
        Nodo cola = dummy;
        Nodo x = a.cabeza;
        Nodo y = b.cabeza;
        while (x != null && y != null) {
            if (x.dato <= y.dato) {
                cola.siguiente = x;
                x = x.siguiente;
            } else {
                cola.siguiente = y;
                y = y.siguiente;
            }
            cola = cola.siguiente;
        }
        if (x != null) {
            cola.siguiente = x;
        } else {
            cola.siguiente = y;
        }
        a.cabeza = null;
        b.cabeza = null;
        ListaEnlazada resultado = new ListaEnlazada();
        resultado.cabeza = dummy.siguiente;
        return resultado;
    }
}
