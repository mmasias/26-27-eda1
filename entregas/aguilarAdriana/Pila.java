package entregas.aguilarAdriana;

public class Pila {
 
    private Nodo tope;
 
    public Pila() {
        this.tope = null;
    }
 
    public void apilar(int valor) {
        Nodo nuevoNodo = new Nodo(valor);
        nuevoNodo.siguiente = tope;
        tope = nuevoNodo;
    }
 
    public int desapilar() {
        assert !estaVacia() : "Precondición: la pila no puede estar vacía";
        int valor = tope.dato;
        tope = tope.siguiente;
        return valor;
    }
 
    public boolean estaVacia() {
        return tope == null;
    }
 
    private static class Nodo {
        private int dato;
        private Nodo siguiente;
 
        private Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }
}