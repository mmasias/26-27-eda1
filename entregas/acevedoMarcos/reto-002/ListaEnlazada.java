
public class ListaEnlazada {
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

        if (cabeza == null || cabeza.siguiente == null) {
            return;
        }

        Nodo nodoAnterior = cabeza;
        Nodo nodoActual = cabeza.siguiente;

        while (nodoActual != null && nodoActual.siguiente != null) {
            if (nodoActual.dato == nodoActual.siguiente.dato) {
                int valorRepetido = nodoActual.dato;

                while (nodoActual != null
                        && nodoActual.dato == valorRepetido) {
                    nodoActual = nodoActual.siguiente;
                }

                nodoAnterior.siguiente = nodoActual;
            } else {
                nodoAnterior = nodoActual;
                nodoActual = nodoActual.siguiente;
            }
        }
    }

    public static ListaEnlazada fusionar(
            ListaEnlazada a, ListaEnlazada b) {

        ListaEnlazada resultado = new ListaEnlazada();

        Nodo dummy = new Nodo(-1);
        Nodo cola = dummy;

        Nodo nodoA = a.cabeza;
        Nodo nodoB = b.cabeza;

        while (nodoA != null && nodoB != null) {
            if (nodoA.dato <= nodoB.dato) {
                cola.siguiente = nodoA;
                nodoA = nodoA.siguiente;
            } else {
                cola.siguiente = nodoB;
                nodoB = nodoB.siguiente;
            }

            cola = cola.siguiente;
        }

        if (nodoA != null) {
            cola.siguiente = nodoA;
        } else {
            cola.siguiente = nodoB;
        }

        resultado.cabeza = dummy.siguiente;

        a.cabeza = null;
        b.cabeza = null;

        return resultado;
    }
}
