public class ListaEnlazada {
    private Nodo cabeza;

    public ListaEnlazada() {
        cabeza = null;
    }

    public void insertarAlFinal(int dato) {
        Nodo nuevo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo aux = cabeza;
            while (aux.sig != null) {
                aux = aux.sig;
            }
            aux.sig = nuevo;
        }
    }

    public void eliminarRepetidos() {
        Nodo dummy = new Nodo(-1);
        dummy.sig = cabeza;

        Nodo anterior = dummy;
        Nodo actual = cabeza;

        while (actual != null) {
            if (actual.sig != null && actual.dato == actual.sig.dato) {
                while (actual.sig != null && actual.dato == actual.sig.dato) {
                    actual = actual.sig;
                }
                actual = actual.sig;
                anterior.sig = actual;
            } else {
                anterior = actual;
                actual = actual.sig;
            }
        }

        cabeza = dummy.sig;
    }

    public void eliminarRepetidosSinDummy() {
        while (cabeza != null && cabeza.sig != null && cabeza.dato == cabeza.sig.dato) {
            int valor = cabeza.dato;
            while (cabeza != null && cabeza.dato == valor) {
                cabeza = cabeza.sig;
            }
        }

        if (cabeza == null) {
            return;
        }

        Nodo anterior = cabeza;
        Nodo actual = cabeza.sig;

        while (actual != null) {
            if (actual.sig != null && actual.dato == actual.sig.dato) {
                while (actual.sig != null && actual.dato == actual.sig.dato) {
                    actual = actual.sig;
                }
                actual = actual.sig;
                anterior.sig = actual;
            } else {
                anterior = actual;
                actual = actual.sig;
            }
        }
    }

    public static ListaEnlazada fusionar(ListaEnlazada a, ListaEnlazada b) {
        if (a == b) {
            throw new IllegalArgumentException("No se puede fusionar una lista consigo misma");
        }

        Nodo dummy = new Nodo(-1);
        Nodo ultimo = dummy;
        Nodo pa = a.cabeza;
        Nodo pb = b.cabeza;

        while (pa != null && pb != null) {
            if (pa.dato <= pb.dato) {
                ultimo.sig = pa;
                pa = pa.sig;
            } else {
                ultimo.sig = pb;
                pb = pb.sig;
            }
            ultimo = ultimo.sig;
        }

        if (pa != null) {
            ultimo.sig = pa;
        } else {
            ultimo.sig = pb;
        }

        ListaEnlazada resultado = new ListaEnlazada();
        resultado.cabeza = dummy.sig;

        a.cabeza = null;
        b.cabeza = null;

        return resultado;
    }

    @Override
    public String toString() {
        if (cabeza == null) {
            return "null";
        }
        String s = "";
        Nodo aux = cabeza;
        while (aux != null) {
            s += aux.dato;
            if (aux.sig != null) {
                s += " -> ";
            }
            aux = aux.sig;
        }
        return s;
    }
}