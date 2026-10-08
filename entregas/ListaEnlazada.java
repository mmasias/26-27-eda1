public class ListaEnlazada {
    private Nodo cabeza;

    public ListaEnlazada() {
        cabeza = null;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void agregarAlFinal(int dato) {
        Nodo nuevo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevo;
            return;
        }
        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        actual.siguiente = nuevo;
    }

    public void eliminarRepetidos() {
        Nodo dummy = new Nodo(0);
        dummy.siguiente = cabeza;

        Nodo previo = dummy;
        Nodo actual = cabeza;

        while (actual != null) {
            if (actual.siguiente != null && actual.dato == actual.siguiente.dato) {
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
        while (cabeza != null && cabeza.siguiente != null
                && cabeza.dato == cabeza.siguiente.dato) {
            int valor = cabeza.dato;
            while (cabeza != null && cabeza.dato == valor) {
                cabeza = cabeza.siguiente;
            }
        }
        if (cabeza == null) {
            return;
        }

        Nodo previo = cabeza;
        Nodo actual = cabeza.siguiente;

        while (actual != null) {
            if (actual.siguiente != null && actual.dato == actual.siguiente.dato) {
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
    }

    public static ListaEnlazada fusionar(ListaEnlazada a, ListaEnlazada b) {
        Nodo dummy = new Nodo(0);
        Nodo cola = dummy;
        Nodo pa = a.cabeza;
        Nodo pb = b.cabeza;

        while (pa != null && pb != null) {
            if (pa.dato <= pb.dato) {
                cola.siguiente = pa;
                pa = pa.siguiente;
            } else {
                cola.siguiente = pb;
                pb = pb.siguiente;
            }
            cola = cola.siguiente;
        }
        cola.siguiente = (pa != null) ? pa : pb;

        ListaEnlazada resultado = new ListaEnlazada();
        resultado.cabeza = dummy.siguiente;

        a.cabeza = null;
        b.cabeza = null;
        return resultado;
    }

    @Override
    public String toString() {
        if (cabeza == null) {
            return "null";
        }
        String resultado = "";
        Nodo actual = cabeza;
        while (actual != null) {
            resultado += actual.dato;
            if (actual.siguiente != null) {
                resultado += " -> ";
            }
            actual = actual.siguiente;
        }
        return resultado;
    }
}