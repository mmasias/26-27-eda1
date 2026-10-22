public class Lista<T> {
    private Nodo<T> cabeza;
    private Nodo<T> ultimo;
    private int tamano;
    private int comparaciones;

    public void agregar(T dato) {
        Nodo<T> nuevo = new Nodo<T>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
        }
        ultimo = nuevo;
        tamano++;
    }

    // COSTE O(n): recorrido secuencial, cada paso cuenta una comparacion
    public T buscar(T clave) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            comparaciones++;
            if (actual.getDato().equals(clave)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public boolean contiene(T clave) {
        return buscar(clave) != null;
    }

    // COSTE O(n): hay que localizar el nodo; despues se reenlaza sin desplazar nada
    public boolean eliminar(T clave) {
        Nodo<T> anterior = null;
        Nodo<T> actual = cabeza;
        while (actual != null) {
            comparaciones++;
            if (actual.getDato().equals(clave)) {
                if (anterior == null) {
                    cabeza = actual.getSiguiente();
                } else {
                    anterior.setSiguiente(actual.getSiguiente());
                }
                if (actual == ultimo) {
                    ultimo = anterior;
                }
                tamano--;
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
        return false;
    }

    public Nodo<T> getCabeza() {
        return cabeza;
    }

    public int tamano() {
        return tamano;
    }

    public int comparaciones() {
        return comparaciones;
    }
} 
