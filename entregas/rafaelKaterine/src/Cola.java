class Cola<T> {
    private NodoEstructura<T> primero;
    private NodoEstructura<T> ultimo;
    private int tamano;

    public Cola() {
        primero = null;
        ultimo = null;
        tamano = 0;
    }

    public void encolar(T dato) {
        NodoEstructura<T> nuevo = new NodoEstructura<>(dato);
        if (ultimo == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        }
        tamano++;
    }

    public T desencolar() {
        if (primero == null) return null;
        T dato = primero.dato;
        primero = primero.siguiente;
        if (primero == null) ultimo = null;
        tamano--;
        return dato;
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public int getTamano() {
        return tamano;
    }
}