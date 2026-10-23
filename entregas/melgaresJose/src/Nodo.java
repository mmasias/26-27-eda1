class Nodo {
    private String dato;
    private Nodo siguiente;

    public Nodo(String dato, Nodo siguiente) {
        this.dato = dato;
        this.siguiente = siguiente;
    }
    public Nodo(String dato) {
        this(dato, null);
    }

    public String obtenerDato() {
        return dato;
    }

    public Nodo obtenerSiguiente() {
        return siguiente;
    }

    public void establecerSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}