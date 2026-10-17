
public class ListaEnlazada {
    private Nodo cabeza;

    public ListaEnlazada() {
        cabeza = null;
    }

    public void insertarEnPosicion(int posicion, int dato) {
        Nodo nuevo = new Nodo(dato);

        if (posicion == 0) {
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            int i = 0;

            while (actual != null && i < posicion - 1) {
                actual = actual.siguiente;
                i++;
            }

            if (actual != null) {
                nuevo.siguiente = actual.siguiente;
                actual.siguiente = nuevo;
            }
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
        Nodo dummy = new Nodo(-1);
        dummy.siguiente = cabeza;
        Nodo previo = dummy;
        Nodo actual = cabeza;

        while (actual != null) {
            if (actual.siguiente != null
                    && actual.dato == actual.siguiente.dato) {
                int valor = actual.dato;

                while (actual != null && actual.dato == valor) {
                    actual = actual.siguiente;
                }

                previo.siguiente = actual;
            } else {
                previo = actual;
                actual = actual.siguiente;
            }
        }

        cabeza = dummy.siguiente;
    }

    public void eliminarRepetidosSinDummy() {
        eliminarRepetidos();
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
