public class ListaEnlazada {

    private Nodo cabeza;

    public ListaEnlazada() {
        cabeza = null;
    }

    public void insertarFinal(int dato) {
        Nodo nuevo = new Nodo(dato);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;

            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }

            actual.siguiente = nuevo;
        }
    }

    public void imprimirLista() {
        Nodo actual = cabeza;

        if (actual == null) {
            System.out.println("null");
            return;
        }

        while (actual != null) {
            System.out.print(actual.dato);

            if (actual.siguiente != null) {
                System.out.print(" -> ");
            }

            actual = actual.siguiente;
        }

        System.out.println();
    }

    public void eliminarRepetidos() {
        Nodo dummy = new Nodo(-1);
        dummy.siguiente = cabeza;

        Nodo actual = dummy;

        while (actual.siguiente != null) {
            Nodo siguiente = actual.siguiente;

            if (siguiente.siguiente != null
                    && siguiente.dato == siguiente.siguiente.dato) {

                int valor = siguiente.dato;

                while (siguiente != null && siguiente.dato == valor) {
                    siguiente = siguiente.siguiente;
                }

                actual.siguiente = siguiente;

            } else {
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

        Nodo actual = cabeza;

        while (actual != null && actual.siguiente != null) {
            if (actual.siguiente.siguiente != null
                    && actual.siguiente.dato == actual.siguiente.siguiente.dato) {

                int valor = actual.siguiente.dato;
                Nodo repetido = actual.siguiente;

                while (repetido != null && repetido.dato == valor) {
                    repetido = repetido.siguiente;
                }

                actual.siguiente = repetido;

            } else {
                actual = actual.siguiente;
            }
        }
    }

    public static ListaEnlazada fusionar(ListaEnlazada a, ListaEnlazada b) {
        ListaEnlazada resultado = new ListaEnlazada();

        Nodo dummy = new Nodo(-1);
        Nodo ultimo = dummy;

        Nodo actualA = a.cabeza;
        Nodo actualB = b.cabeza;

        while (actualA != null && actualB != null) {
            if (actualA.dato <= actualB.dato) {
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
        } else {
            ultimo.siguiente = actualB;
        }

        resultado.cabeza = dummy.siguiente;

        a.cabeza = null;
        b.cabeza = null;

        return resultado;
    }
}