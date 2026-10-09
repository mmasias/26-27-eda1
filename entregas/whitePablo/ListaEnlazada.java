package listas.nodoDummy;

class ListaEnlazada {
    private Nodo cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }

    public String comoTexto() {
        StringBuilder texto = new StringBuilder();
        Nodo actual = cabeza;
        while (actual != null) {
            texto.append(actual.dato).append(" -> ");
            actual = actual.siguiente;
        }
        texto.append("null");
        return texto.toString();
    }

    public void imprimirLista() {
        System.out.println(comoTexto());
    }

    public boolean estaVacia() {
        return cabeza == null;
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
        Nodo anterior = dummy;
        while (anterior.siguiente != null) {
            Nodo candidato = anterior.siguiente;
            if (candidato.siguiente != null && candidato.siguiente.dato == candidato.dato) {
                int valor = candidato.dato;
                while (anterior.siguiente != null && anterior.siguiente.dato == valor) {
                    anterior.siguiente = anterior.siguiente.siguiente;
                }
            } else {
                anterior = candidato;
            }
        }
        cabeza = dummy.siguiente;
    }

    public void eliminarRepetidosSinDummy() {
        while (cabeza != null && cabeza.siguiente != null && cabeza.dato == cabeza.siguiente.dato) {
            int valor = cabeza.dato;
            while (cabeza != null && cabeza.dato == valor) {
                cabeza = cabeza.siguiente;
            }
        }

        if (cabeza == null) {
            return;
        }

        Nodo anterior = cabeza;
        while (anterior.siguiente != null) {
            Nodo candidato = anterior.siguiente;
            if (candidato.siguiente != null && candidato.siguiente.dato == candidato.dato) {
                int valor = candidato.dato;
                while (anterior.siguiente != null && anterior.siguiente.dato == valor) {
                    anterior.siguiente = anterior.siguiente.siguiente;
                }
            } else {
                anterior = candidato;
            }
        }
    }

    public static ListaEnlazada fusionar(ListaEnlazada a, ListaEnlazada b) {
        if (a == b) {
            throw new IllegalArgumentException("No se puede fusionar una lista consigo misma");
        }

        Nodo dummy = new Nodo(-1);
        Nodo cola = dummy;
        Nodo actualA = a.cabeza;
        Nodo actualB = b.cabeza;

        while (actualA != null && actualB != null) {
            if (actualA.dato <= actualB.dato) {
                cola.siguiente = actualA;
                actualA = actualA.siguiente;
            } else {
                cola.siguiente = actualB;
                actualB = actualB.siguiente;
            }
            cola = cola.siguiente;
        }
        cola.siguiente = (actualA != null) ? actualA : actualB;

        ListaEnlazada resultado = new ListaEnlazada();
        resultado.cabeza = dummy.siguiente;

        a.cabeza = null;
        b.cabeza = null;

        return resultado;
    }
}