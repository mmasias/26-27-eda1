package entregas.scr.herreraGabriel;
class ListaEnlazada {
    private Nodo cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }

    public void imprimirLista() {
        for (Nodo actual = cabeza; actual != null; actual = actual.siguiente) {
            System.out.print(actual.dato + " -> ");
        }
        System.out.println("null");
    }

    public void insertarEnPosicion(int posicion, int dato) {
        Nodo dummy = new Nodo(-1);
        dummy.siguiente = cabeza;
        
        Nodo actual = dummy;
        for (int pasos = 0; actual.siguiente != null && pasos < posicion; pasos++) {
            actual = actual.siguiente;
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
        for (int pasos = 1; actual.siguiente != null && pasos < posicion; pasos++) {
            actual = actual.siguiente;
        }
        nuevoNodo.siguiente = actual.siguiente;
        actual.siguiente = nuevoNodo;
    }

    public void eliminarPorValor(int valor) {
        Nodo dummy = new Nodo(-1);
        dummy.siguiente = cabeza;
        
        for (Nodo actual = dummy; actual.siguiente != null; ) {
            if (actual.siguiente.dato == valor) {
                actual.siguiente = actual.siguiente.siguiente;
            } else {
                actual = actual.siguiente;
            }
        }
        cabeza = dummy.siguiente;
    }

    public void eliminarPorValorSinDummy(int valor) {
        for (; cabeza != null && cabeza.dato == valor; cabeza = cabeza.siguiente) {
        }

        if (cabeza == null) {
            return;
        }

        for (Nodo actual = cabeza; actual.siguiente != null; ) {
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
        
        for (Nodo previo = dummy, actual = cabeza; actual != null; ) {
            if (actual.siguiente != null && actual.dato == actual.siguiente.dato) {
                int valorDuplicado = actual.dato;
                for (; actual != null && actual.dato == valorDuplicado; actual = actual.siguiente) {
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
        for (; cabeza != null && cabeza.siguiente != null && cabeza.dato == cabeza.siguiente.dato; ) {
            int valorDuplicado = cabeza.dato;
            for (; cabeza != null && cabeza.dato == valorDuplicado; cabeza = cabeza.siguiente) {
            }
        }

        if (cabeza == null) {
            return;
        }

        for (Nodo previo = cabeza, actual = cabeza.siguiente; actual != null; ) {
            if (actual.siguiente != null && actual.dato == actual.siguiente.dato) {
                int valorDuplicado = actual.dato;
                for (; actual != null && actual.dato == valorDuplicado; actual = actual.siguiente) {
                }
                previo.siguiente = actual;
            } else {
                previo = actual;
                actual = actual.siguiente;
            }
        }
    }
}