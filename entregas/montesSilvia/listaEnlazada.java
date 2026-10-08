public class ListaEnlazada {

    private Nodo cabeza;
    public ListaEnlazada() {
        cabeza = null;
    }
    public void insertar(int valor) {
        Nodo nuevo = new Nodo(valor);

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

        Nodo anterior = dummy;
        Nodo actual = cabeza;

        while (actual != null) {

            if (actual.siguiente != null
                    && actual.valor == actual.siguiente.valor) {

                int valorRepetido = actual.valor;
                while (actual != null && actual.valor == valorRepetido) {
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
        while (cabeza != null
                && cabeza.siguiente != null
                && cabeza.valor == cabeza.siguiente.valor) {

            int valorRepetido = cabeza.valor;
            while (cabeza != null && cabeza.valor == valorRepetido) {
                cabeza = cabeza.siguiente;
            }
        }
        if (cabeza == null) {
            return;
        }

        Nodo anterior = cabeza;
        Nodo actual = cabeza.siguiente;

        while (actual != null) {
            if (actual.siguiente != null
                    && actual.valor == actual.siguiente.valor) {

                int valorRepetido = actual.valor;
                while (actual != null
                        && actual.valor == valorRepetido) {
                    actual = actual.siguiente;
                }

                anterior.siguiente = actual;

            } else {

                anterior = actual;
                actual = actual.siguiente;
            }
        }
    }

    public static ListaEnlazada fusionar(ListaEnlazada a,
                                         ListaEnlazada b) {

        ListaEnlazada resultado = new ListaEnlazada();
        Nodo dummy = new Nodo(0);
        Nodo ultimo = dummy;

        Nodo actualA = a.cabeza;
        Nodo actualB = b.cabeza;
        while (actualA != null && actualB != null) {

            if (actualA.valor <= actualB.valor) {

                ultimo.siguiente = actualA;
                actualA = actualA.siguiente;

            } else {

                ultimo.siguiente = actualB;
                actualB = actualB.siguiente;
            }

            ultimo = ultimo.siguiente;
        }

        
        if (actualA != null) {
            ultimo.siguiente = actualA;
        }

        if (actualB != null) {
            ultimo.siguiente = actualB;
        }
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

        StringBuilder resultado = new StringBuilder();

        Nodo actual = cabeza;

        while (actual != null) {

            resultado.append(actual.valor);

            if (actual.siguiente != null) {
                resultado.append(" -> ");
            }

            actual = actual.siguiente;
        }

        return resultado.toString();
    }
}
