package entregas.nicolasCrespo;

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
            boolean esRepetido = false;

            while (nodoActual.siguiente != null
                    && nodoActual.dato == nodoActual.siguiente.dato) {
                esRepetido = true;
                nodoActual = nodoActual.siguiente;
            }

            if (esRepetido) {
                nodoAnterior.siguiente = nodoActual.siguiente;
            } else {
                nodoAnterior = nodoActual;
            }
        }

        cabeza = nodoDummy.siguiente;
    }

    public void eliminarRepetidosSinDummy() {
        while (cabeza != null
                && cabeza.siguiente != null
                && cabeza.dato == cabeza.siguiente.dato) {
            int valorRepetido = cabeza.dato;

            while (cabeza != null && cabeza.dato == valorRepetido) {
                cabeza = cabeza.siguiente;
            }
        }

        if (cabeza == null) {
            return;
        }

        Nodo nodoAnterior = cabeza;
        Nodo nodoActual = cabeza.siguiente;

        while (nodoActual != null && nodoActual.siguiente != null) {
            if (nodoActual.dato == nodoActual.siguiente.dato) {
                int valorRepetido = nodoActual.dato;

                while (nodoActual != null && nodoActual.dato == valorRepetido) {
                    nodoActual = nodoActual.siguiente;
                }

                nodoAnterior.siguiente = nodoActual;
            } else {
                nodoAnterior = nodoActual;
                nodoActual = nodoActual.siguiente;
            }
        }
    }

    public static ListaEnlazada fusionar(ListaEnlazada a, ListaEnlazada b) {
        ListaEnlazada resultado = new ListaEnlazada();
        Nodo nodoDummy = new Nodo(0);
        Nodo ultimo = nodoDummy;

        while (a.cabeza != null && b.cabeza != null) {
            if (a.cabeza.dato <= b.cabeza.dato) {
                ultimo.siguiente = a.cabeza;
                a.cabeza = a.cabeza.siguiente;
            } else {
                ultimo.siguiente = b.cabeza;
                b.cabeza = b.cabeza.siguiente;
            }
            ultimo = ultimo.siguiente;
        }

        if (a.cabeza != null) {
            ultimo.siguiente = a.cabeza;
        } else {
            ultimo.siguiente = b.cabeza;
        }

        resultado.cabeza = nodoDummy.siguiente;
        a.cabeza = null;
        b.cabeza = null;
        return resultado;
    }
}
