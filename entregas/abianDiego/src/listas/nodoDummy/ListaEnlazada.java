package listas.nodoDummy;

class ListaEnlazada {
    private Nodo cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }

    public void imprimirLista() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        }
        System.out.println("null");
    }

    // Si la posición excede el tamaño, se inserta al final.
    public void insertarEnPosicion(int posicion, int dato) {
        Nodo dummy = new Nodo(-1);
        dummy.siguiente = cabeza;
        Nodo actual = dummy;
        int pasos = 0;
        while (actual.siguiente != null && pasos < posicion) {
            actual = actual.siguiente;
            pasos++;
        }
        Nodo nuevoNodo = new Nodo(dato);
        nuevoNodo.siguiente = actual.siguiente;
        actual.siguiente = nuevoNodo;
        cabeza = dummy.siguiente;
    }

    public void insertarEnPosicionSinDummy(int posicion, int dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (cabeza == null || posicion <= 0) {
            nuevoNodo.siguiente = cabeza;
            cabeza = nuevoNodo;
            return;
        }

        Nodo actual = cabeza;
        int pasos = 1;
        while (actual.siguiente != null && pasos < posicion) {
            actual = actual.siguiente;
            pasos++;
        }
        nuevoNodo.siguiente = actual.siguiente;
        actual.siguiente = nuevoNodo;
    }

    public void eliminarPorValor(int valor) {
        Nodo dummy = new Nodo(-1);
        dummy.siguiente = cabeza;
        Nodo actual = dummy;
        while (actual.siguiente != null) {
            if (actual.siguiente.dato == valor) {
                actual.siguiente = actual.siguiente.siguiente;
            } else {
                actual = actual.siguiente;
            }
        }
        cabeza = dummy.siguiente;
    }

    public void eliminarPorValorSinDummy(int valor) {
        while (cabeza != null && cabeza.dato == valor) {
            cabeza = cabeza.siguiente;
        }

        if (cabeza == null) {
            return;
        }

        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.dato == valor) {
                actual.siguiente = actual.siguiente.siguiente;
            } else {
                actual = actual.siguiente;
            }
        }
    }

    // Lista ordenada ascendentemente: elimina todos los nodos cuyo valor aparece más de una vez.
    public void eliminarRepetidos() {
        Nodo dummy = new Nodo(-1);
        dummy.siguiente = cabeza;
        Nodo actual = dummy;
        while (actual.siguiente != null) {
            if (actual.siguiente.siguiente != null && actual.siguiente.siguiente.dato == actual.siguiente.dato) {
                int repetido = actual.siguiente.dato;
                while (actual.siguiente != null && actual.siguiente.dato == repetido) {
                    actual.siguiente = actual.siguiente.siguiente;
                }
            } else {
                actual = actual.siguiente;
            }
        }
        cabeza = dummy.siguiente;
    }

    public void eliminarRepetidosSinDummy() {
        while (cabeza != null && cabeza.siguiente != null && cabeza.siguiente.dato == cabeza.dato) {
            int repetido = cabeza.dato;
            while (cabeza != null && cabeza.dato == repetido) {
                cabeza = cabeza.siguiente;
            }
        }

        if (cabeza == null) {
            return;
        }

        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.siguiente != null && actual.siguiente.siguiente.dato == actual.siguiente.dato) {
                int repetido = actual.siguiente.dato;
                while (actual.siguiente != null && actual.siguiente.dato == repetido) {
                    actual.siguiente = actual.siguiente.siguiente;
                }
            } else {
                actual = actual.siguiente;
            }
        }
    }

    // Listas ordenadas ascendentemente. Reenlaza los nodos de a y b (solo se crea el dummy) y las deja vacías.
    public static ListaEnlazada fusionar(ListaEnlazada a, ListaEnlazada b) {
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

        a.cabeza = null;
        b.cabeza = null;

        ListaEnlazada resultado = new ListaEnlazada();
        resultado.cabeza = dummy.siguiente;
        return resultado;
    }
}
