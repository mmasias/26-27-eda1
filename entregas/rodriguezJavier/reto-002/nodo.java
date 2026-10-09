class Nodo {
    private int valor;
    private Nodo siguiente;

    public Nodo(int valor) {
        this.valor = valor;
        this.siguiente = null;
    }

    public int obtenerValor() {
        return this.valor;
    }

    public void establecerValor(int valor) {
        this.valor = valor;
    }

    public Nodo obtenerSiguiente() {
        return this.siguiente;
    }

    public void establecerSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}