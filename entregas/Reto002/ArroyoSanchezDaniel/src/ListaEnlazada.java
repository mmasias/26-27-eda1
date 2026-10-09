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
        Nodo dummy = new Nodo(0);
        dummy.siguiente = cabeza;
        Nodo actual = dummy;

        while (actual.siguiente != null) {
            int valorActual = actual.siguiente.dato;
            boolean repetido = false;

            while (actual.siguiente != null && actual.siguiente.dato == valorActual) {
                repetido = true;
                actual.siguiente = actual.siguiente.siguiente;
            }

            if (!repetido) {
                actual = actual.siguiente;
            }
        }

        cabeza = dummy.siguiente;
    }

    public void eliminarRepetidosSinDummy() {
        if (cabeza == null) {
            return;
        }

        Nodo actual = cabeza;

        while (actual != null) {
            int valorActual = actual.dato;
            boolean repetido = false;

            while (actual.siguiente != null && actual.siguiente.dato == valorActual) {
                repetido = true;
                actual.siguiente = actual.siguiente.siguiente;
            }

            if (!repetido) {
                actual = actual.siguiente;
            } else {

                if (cabeza.dato == valorActual) {
                    cabeza = cabeza.siguiente;
                    actual = cabeza;
                } else {
                    Nodo temporal = cabeza;
                    while (temporal.siguiente != null && temporal.siguiente.dato != valorActual) {
                        temporal = temporal.siguiente;
                    }
                    if (temporal.siguiente != null) {
                        temporal.siguiente = temporal.siguiente.siguiente;
                    }
                    actual = temporal;
                }
            }
        }

    }

    public static ListaEnlazada fusionar(ListaEnlazada a, ListaEnlazada b) {
        ListaEnlazada resultado = new ListaEnlazada();
        Nodo actualA = a.cabeza;
        Nodo actualB = b.cabeza;
        Nodo dummy = new Nodo(-1);
        Nodo actualResultado = dummy;

        while (actualA != null && actualB != null) {
            if (actualA.dato <= actualB.dato) {
                actualResultado.siguiente = new Nodo(actualA.dato);
                actualA = actualA.siguiente;
            } else {
                actualResultado.siguiente = new Nodo(actualB.dato);
                actualB = actualB.siguiente;
            }
            actualResultado = actualResultado.siguiente;
        }

        while (actualA != null) {
            actualResultado.siguiente = new Nodo(actualA.dato);
            actualA = actualA.siguiente;
            actualResultado = actualResultado.siguiente;
        }

        while (actualB != null) {
            actualResultado.siguiente = new Nodo(actualB.dato);
            actualB = actualB.siguiente;
            actualResultado = actualResultado.siguiente;
        }

        resultado.cabeza = dummy.siguiente;
        return resultado;
    }
}