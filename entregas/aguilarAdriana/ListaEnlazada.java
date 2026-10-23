package entregas.aguilarAdriana;

public class ListaEnlazada {
    private Nodo cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }

    public void insertarAlPrincipio(int dato) {
        Nodo nuevoNodo = new Nodo(dato);
        nuevoNodo.siguiente = this.cabeza;
        this.cabeza = nuevoNodo;
    }

    public void eliminarAlPrincipio() {
        if (this.cabeza != null) {
            this.cabeza = this.cabeza.siguiente;
        }
    }

    public boolean contiene(int dato) {
        Nodo actual = this.cabeza;
        while (actual != null) {
            if (actual.dato == dato) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public boolean eliminar(int dato) {
        if (this.cabeza == null) {
            return false;
        }
        if (this.cabeza.dato == dato) {
            this.cabeza = this.cabeza.siguiente;
            return true;
        }

        Nodo anterior = this.cabeza;
        Nodo actual = this.cabeza.siguiente;
        while (actual != null) {
            if (actual.dato == dato) {
                anterior.siguiente = actual.siguiente;
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        return false;
    }

    public int cantidad() {
        int cantidad = 0;
        Nodo actual = this.cabeza;
        while (actual != null) {
            cantidad++;
            actual = actual.siguiente;
        }
        return cantidad;
    }

    public void imprimirLista() {
        Nodo actual = this.cabeza;
        while (actual != null) {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        }
        System.out.println("null");
    }
}
