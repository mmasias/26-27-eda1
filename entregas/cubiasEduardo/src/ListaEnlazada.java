
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

    public void insertarAlFinal(int dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
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
        } else {
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

        if (cabeza != null) {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                if (actual.siguiente.dato == valor) {
                    actual.siguiente = actual.siguiente.siguiente;
                } else {
                    actual = actual.siguiente;
                }
            }
        }
    }

    public void eliminarRepetidos() {
        Nodo dummy = new Nodo(-1);
        dummy.siguiente = cabeza;
        eliminarDuplicadosDesde(dummy);
        cabeza = dummy.siguiente;
    }

    public void eliminarRepetidosSinDummy() {
        while (cabeza != null && cabeza.siguiente != null && cabeza.dato == cabeza.siguiente.dato) {
            int duplicado = cabeza.dato;
            do {
                cabeza = cabeza.siguiente;
            } while (cabeza != null && cabeza.dato == duplicado);
        }
        if (cabeza != null) {
            eliminarDuplicadosDesde(cabeza);
        }
    }

    private void eliminarDuplicadosDesde(Nodo actual) {
        while (actual.siguiente != null && actual.siguiente.siguiente != null) {
            if (actual.siguiente.dato == actual.siguiente.siguiente.dato) {
                int duplicado = actual.siguiente.dato;
                do {
                    actual.siguiente = actual.siguiente.siguiente;
                } while (actual.siguiente != null && actual.siguiente.dato == duplicado);
            } else {
                actual = actual.siguiente;
            }
        }
    }

    public static ListaEnlazada fusionar(ListaEnlazada a, ListaEnlazada b) {
        ListaEnlazada resultado = new ListaEnlazada();
        Nodo dummy = new Nodo(-1);
        Nodo actual = dummy;
        Nodo pa = (a != null) ? a.cabeza : null;
        Nodo pb = (b != null) ? b.cabeza : null;

        while (pa != null && pb != null) {
            if (pa.dato <= pb.dato) {
                actual.siguiente = pa;
                pa = pa.siguiente;
            } else {
                actual.siguiente = pb;
                pb = pb.siguiente;
            }
            actual = actual.siguiente;
        }

        if (pa != null) {
            actual.siguiente = pa;
        } else {
            actual.siguiente = pb;
        }

        resultado.cabeza = dummy.siguiente;

        if (a != null) {
            a.cabeza = null;
        }
        if (b != null) {
            b.cabeza = null;
        }

        return resultado;
    }
}
