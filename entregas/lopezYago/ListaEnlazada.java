package entregas.lopezYago;

public class ListaEnlazada {
    private Nodo primero;

    public ListaEnlazada() {
        primero = null;
    }

    public void agregarAlFinal(int valor) {
        Nodo nuevo = new Nodo(valor);
        if (primero == null) {
            primero = nuevo;
            return;
        }
        Nodo actual = primero;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        actual.siguiente = nuevo;
    }

    public void eliminarRepetidos() {
        Nodo dummy = new Nodo(0);
        dummy.siguiente = primero;

        Nodo previo = dummy;
        Nodo actual = primero;

        while (actual != null) {
            if (actual.siguiente != null && actual.valor == actual.siguiente.valor) {
                int repetido = actual.valor;
                while (actual != null && actual.valor == repetido) {
                    actual = actual.siguiente;
                }
                previo.siguiente = actual;
            } else {
                previo = actual;
                actual = actual.siguiente;
            }
        }
        primero = dummy.siguiente;
    }

    public void eliminarRepetidosSinDummy() {
        while (primero != null && primero.siguiente != null
                && primero.valor == primero.siguiente.valor) {
            int repetido = primero.valor;
            while (primero != null && primero.valor == repetido) {
                primero = primero.siguiente;
            }
        }
        if (primero == null) {
            return;
        }

        Nodo previo = primero;
        while (previo.siguiente != null) {
            Nodo actual = previo.siguiente;
            if (actual.siguiente != null && actual.valor == actual.siguiente.valor) {
                int repetido = actual.valor;
                while (actual != null && actual.valor == repetido) {
                    actual = actual.siguiente;
                }
                previo.siguiente = actual;
            } else {
                previo = actual;
            }
        }
    }

    @Override
    public String toString() {
        if (primero == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        Nodo actual = primero;
        while (actual != null) {
            sb.append(actual.valor);
            if (actual.siguiente != null) {
                sb.append(" -> ");
            }
            actual = actual.siguiente;
        }
        return sb.toString();
    }
}