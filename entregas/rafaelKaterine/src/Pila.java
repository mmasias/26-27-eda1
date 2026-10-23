class Pila<T> {
    private NodoEstructura<T> cima;

    public Pila() {
        cima = null;
    }

    public void apilar(T dato) {
        NodoEstructura<T> nuevo = new NodoEstructura<>(dato);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    public T desapilar() {
        if (cima == null) return null;
        T dato = cima.dato;
        cima = cima.siguiente;
        return dato;
    }

    public boolean estaVacia() {
        return cima == null;
    }
}