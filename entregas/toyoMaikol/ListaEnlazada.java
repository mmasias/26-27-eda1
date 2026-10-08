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

    public void eliminarRepetidos() {
        Nodo dummy = new Nodo(-1);
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
        Nodo previo = null;
        Nodo actual = cabeza;

        while (actual != null) {
            if (actual.siguiente != null && actual.dato == actual.siguiente.dato) {
                int valor = actual.dato;
                while (actual != null && actual.dato == valor) {
                    actual = actual.siguiente;
                }
                if (previo == null) {
                    cabeza = actual;
                } else {
                    previo.siguiente = actual;
                }
            } else {
                previo = actual;
                actual = actual.siguiente;
            }
        }
    }

    public static ListaEnlazada fusionar(ListaEnlazada a, ListaEnlazada b) {
        Nodo dummy = new Nodo(-1);
        Nodo ultimoNodoResultado = dummy;
        Nodo nodoActualA = a.cabeza;
        Nodo nodoActualB = b.cabeza;

        while (nodoActualA != null && nodoActualB != null) {
            if (nodoActualA.dato <= nodoActualB.dato) {
                ultimoNodoResultado.siguiente = nodoActualA;
                nodoActualA = nodoActualA.siguiente;
            } else {
                ultimoNodoResultado.siguiente = nodoActualB;
                nodoActualB = nodoActualB.siguiente;
            }
            ultimoNodoResultado = ultimoNodoResultado.siguiente;
        }

        if (nodoActualA != null) {
            ultimoNodoResultado.siguiente = nodoActualA;
        } else {
            ultimoNodoResultado.siguiente = nodoActualB;
        }

        ListaEnlazada resultado = new ListaEnlazada();
        resultado.cabeza = dummy.siguiente;

        a.cabeza = null;
        b.cabeza = null;
        return resultado;
    }
}
