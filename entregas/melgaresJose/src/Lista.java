public class Lista {

    private Nodo primero;
    private int tamano;

    public int tamano() {
        return this.tamano;
    }

    public boolean estaVacia() {
        return this.primero == null;
    }

    public void insertarFinal(String dato) {
        Nodo nuevo = new Nodo(dato);
        if (this.primero == null) {
            this.primero = nuevo;
        } else {
            Nodo actual = this.primero;
            while (actual.obtenerSiguiente() != null) {
                actual = actual.obtenerSiguiente();
            }
            actual.establecerSiguiente(nuevo);
        }
        this.tamano++;
    }

    public String obtener(int indice) {
        
        Nodo actual = this.primero;
        for (int i = 0; i < indice; i++) {
            actual = actual.obtenerSiguiente();
        }
        return actual.obtenerDato();
    }

    public boolean contiene(String dato) {
        Nodo actual = this.primero;
        while (actual != null) {
            if (dato == null ? actual.obtenerDato() == null : dato.equals(actual.obtenerDato())) {
                return true;
            }
            actual = actual.obtenerSiguiente();
        }
        return false;
    }

    public boolean eliminar(String dato) {
        if (this.primero == null) {
            return false;
        }
        if (dato == null ? this.primero.obtenerDato() == null : dato.equals(this.primero.obtenerDato())) {
            this.primero = this.primero.obtenerSiguiente();
            this.tamano--;
            return true;
        }
        Nodo anterior = this.primero;
        Nodo actual = this.primero.obtenerSiguiente();
        while (actual != null) {
            if (dato == null ? actual.obtenerDato() == null : dato.equals(actual.obtenerDato())) {
                anterior.establecerSiguiente(actual.obtenerSiguiente());
                this.tamano--;
                return true;
            }
            anterior = actual;
            actual = actual.obtenerSiguiente();
        }
        return false;
    }
}
