public class ListaEnlazada {
    Nodo cabeza;

    public void agregar(int valor) {
        Nodo nuevo = new Nodo(valor);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo aux = cabeza;

            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }

            aux.siguiente = nuevo;
        }
    }

    public void eliminarRepetidos() {
        Nodo dummy = new Nodo(0);
        dummy.siguiente = cabeza;

        Nodo anterior = dummy;
        Nodo actual = cabeza;

        while (actual != null) {
            if (actual.siguiente != null
                    && actual.valor == actual.siguiente.valor) {

                int valor = actual.valor;

                while (actual != null && actual.valor == valor) {
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
        while (cabeza != null && cabeza.siguiente != null
                && cabeza.valor == cabeza.siguiente.valor) {

            int valor = cabeza.valor;

            while (cabeza != null && cabeza.valor == valor) {
                cabeza = cabeza.siguiente;
            }
        }

        Nodo actual = cabeza;

        while (actual != null && actual.siguiente != null) {
            if (actual.siguiente.siguiente != null
                    && actual.siguiente.valor
                    == actual.siguiente.siguiente.valor) {

                int valor = actual.siguiente.valor;

                while (actual.siguiente != null
                        && actual.siguiente.valor == valor) {
                    actual.siguiente = actual.siguiente.siguiente;
                }
            } else {
                actual = actual.siguiente;
            }
        }
    }

    public static ListaEnlazada fusionar(
            ListaEnlazada a, ListaEnlazada b) {

        ListaEnlazada resultado = new ListaEnlazada();

        Nodo dummy = new Nodo(0);
        Nodo aux = dummy;

        Nodo x = a.cabeza;
        Nodo y = b.cabeza;

        while (x != null && y != null) {
            if (x.valor <= y.valor) {
                aux.siguiente = x;
                x = x.siguiente;
            } else {
                aux.siguiente = y;
                y = y.siguiente;
            }

            aux = aux.siguiente;
        }

        if (x != null) {
            aux.siguiente = x;
        } else {
            aux.siguiente = y;
        }

        resultado.cabeza = dummy.siguiente;

        a.cabeza = null;
        b.cabeza = null;

        return resultado;
    }

    public String toString() {
        String texto = "";
        Nodo aux = cabeza;

        while (aux != null) {
            texto += aux.valor + " -> ";
            aux = aux.siguiente;
        }

        return texto + "null";
    }