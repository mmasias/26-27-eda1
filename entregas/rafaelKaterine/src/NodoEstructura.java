class NodoEstructura<T> {
    T dato;
    NodoEstructura<T> siguiente;

    public NodoEstructura(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}