package listas.nodoDummy;

public class ListaEnlazada {
    private Nodo cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }

    public void insertarAlFinal(int dato) {
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
        Nodo anterior = dummy;

        while (anterior.siguiente != null && anterior.siguiente.siguiente != null) {
            if (anterior.siguiente.dato == anterior.siguiente.siguiente.dato) {
                int valorDuplicado = anterior.siguiente.dato;
                while (anterior.siguiente != null && anterior.siguiente.dato == valorDuplicado) {
                    anterior.siguiente = anterior.siguiente.siguiente;
                }
            } else {
                anterior = anterior.siguiente;
            }
        }

        cabeza = dummy.siguiente;
    }

    public void eliminarRepetidosSinDummy() {
        while (cabeza != null && cabeza.siguiente != null && cabeza.dato == cabeza.siguiente.dato) {
            int valorDuplicado = cabeza.dato;
            while (cabeza != null && cabeza.dato == valorDuplicado) {
                cabeza = cabeza.siguiente;
            }
        }

        if (cabeza == null) {
            return;
        }

        Nodo actual = cabeza;
        while (actual.siguiente != null && actual.siguiente.siguiente != null) {
            if (actual.siguiente.dato == actual.siguiente.siguiente.dato) {
                int valorDuplicado = actual.siguiente.dato;
                while (actual.siguiente != null && actual.siguiente.dato == valorDuplicado) {
                    actual.siguiente = actual.siguiente.siguiente;
                }
            } else {
                actual = actual.siguiente;
            }
        }
    }


    public static ListaEnlazada fusionar(ListaEnlazada a, ListaEnlazada b) {
        ListaEnlazada resultado = new ListaEnlazada();
        Nodo dummy = new Nodo(-1);
        Nodo cola = dummy;

        Nodo pA = a.cabeza;
        Nodo pB = b.cabeza;

        while (pA != null && pB != null) {
            if (pA.dato <= pB.dato) {
                cola.siguiente = pA;
                pA = pA.siguiente;
            } else {
                cola.siguiente = pB;
                pB = pB.siguiente;
            }
            cola = cola.siguiente;
        }

        if (pA != null) {
            cola.siguiente = pA;
        } else {
            cola.siguiente = pB;
        }

        resultado.cabeza = dummy.siguiente;

        a.cabeza = null;
        b.cabeza = null;

        return resultado;
    }
}