package entregas.paredesFernanda;

public class ListaEnlazada {
    Nodo cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }

    public void insertarAlPrincipio(int dato) {
        Nodo nuevoNodo = new Nodo(dato);
        nuevoNodo.siguiente = cabeza;
        cabeza = nuevoNodo;
    }

    public void imprimirLista() {
        if (cabeza == null) {
            System.out.println("null");
            return;
        }
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

            while (nodoActual.siguiente != null && nodoActual.dato == nodoActual.siguiente.dato) {
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
        while (cabeza != null && cabeza.siguiente != null && cabeza.dato == cabeza.siguiente.dato) {
            int valorRepetido = cabeza.dato;
            while (cabeza != null && cabeza.dato == valorRepetido) {
                cabeza = cabeza.siguiente;
            }
        }

        Nodo actual = cabeza;
        while (actual != null && actual.siguiente != null) {
            if (actual.siguiente.siguiente != null && actual.siguiente.dato == actual.siguiente.siguiente.dato) {
                int valorRepetido = actual.siguiente.dato;
                while (actual.siguiente != null && actual.siguiente.dato == valorRepetido) {
                    actual.siguiente = actual.siguiente.siguiente;
                }
            } else {
                actual = actual.siguiente;
            }
        }
    }

    public static ListaEnlazada fusionar(ListaEnlazada l1, ListaEnlazada l2) {
        ListaEnlazada resultado = new ListaEnlazada();
        Nodo dummy = new Nodo(0);
        Nodo actual = dummy;

        Nodo p1 = (l1 != null) ? l1.cabeza : null;
        Nodo p2 = (l2 != null) ? l2.cabeza : null;

        while (p1 != null && p2 != null) {
            if (p1.dato <= p2.dato) {
                actual.siguiente = p1;
                p1 = p1.siguiente;
            } else {
                actual.siguiente = p2;
                p2 = p2.siguiente;
            }
            actual = actual.siguiente;
        }

        if (p1 != null) {
            actual.siguiente = p1;
        } else if (p2 != null) {
            actual.siguiente = p2;
        }

        resultado.cabeza = dummy.siguiente;
        return resultado;
    }
}