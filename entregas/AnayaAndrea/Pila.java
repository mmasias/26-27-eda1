public class Pila<T> {
    private Nodo<T> cima;
    private int tamano;

    public void apilar(T dato) {
        Nodo<T> nuevo = new Nodo<T>(dato);
        nuevo.setSiguiente(cima);
        cima = nuevo;
        tamano++;
    }

    public T desapilar() {
        if (cima == null) {
            throw new IllegalStateException("Pila vacia");
        }
        T dato = cima.getDato();
        cima = cima.getSiguiente();
        tamano--;
        return dato;
    }

    public boolean estaVacia() {
        return cima == null;
    }

    public int tamano() {
        return tamano;
    }
} 
