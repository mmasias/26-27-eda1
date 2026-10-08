public class ListaEnlazada {
    private Nodo cabeza;   // única referencia que guarda la lista

    public ListaEnlazada() {
        cabeza = null;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void agregarAlFinal(int dato) {
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

    @Override
    public String toString() {
        if (cabeza == null) {
            return "null";
        }
        String resultado = "";
        Nodo actual = cabeza;
        while (actual != null) {
            resultado += actual.dato;
            if (actual.siguiente != null) {
                resultado += " -> ";
            }
            actual = actual.siguiente;
        }
        return resultado;
    }
}