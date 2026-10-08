class ListaEnlazada {
    private Nodo cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }

    public void insertarAlPrincipio(int dato) {
        Nodo nuevoNodo = new Nodo(dato);
        nuevoNodo.siguiente = cabeza;
        cabeza = nuevoNodo;
    }

    public void eliminarAlPrincipio() {
        if (cabeza != null) {
            cabeza = cabeza.siguiente;
        }
    }

    public void eliminarRepetidos() {
        Nodo dummy = new Nodo(0);
        dummy.siguiente = cabeza;
        Nodo anterior = dummy;
        Nodo actual = cabeza;

        while (actual != null) {
            if (actual.siguiente != null && actual.dato == actual.siguiente.dato) {
                int repetido = actual.dato;
                while (actual != null && actual.dato == repetido) {
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
                && cabeza.dato == cabeza.siguiente.dato) {
            int repetido = cabeza.dato;
            while (cabeza != null && cabeza.dato == repetido) {
                cabeza = cabeza.siguiente;
            }
        }

        Nodo anterior = cabeza;
        Nodo actual = cabeza == null ? null : cabeza.siguiente;
        while (actual != null) {
            if (actual.siguiente != null && actual.dato == actual.siguiente.dato) {
                int repetido = actual.dato;
                while (actual != null && actual.dato == repetido) {
                    actual = actual.siguiente;
                }
                anterior.siguiente = actual;
            } else {
                anterior = actual;
                actual = actual.siguiente;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();
        Nodo actual = cabeza;
        while (actual != null) {
            if (resultado.length() > 0) {
                resultado.append(" -> ");
            }
            resultado.append(actual.dato);
            actual = actual.siguiente;
        }
        return resultado.length() == 0 ? "null" : resultado.toString();
    }
    }