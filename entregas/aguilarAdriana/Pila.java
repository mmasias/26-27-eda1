package entregas.aguilarAdriana;

public class Pila {
    private Nodo tope;

    public Pila() {
        this.tope = null;
    }

    public void push(int valor) {
        Nodo nuevoNodo = new Nodo(valor);
        nuevoNodo.siguiente = this.tope;
        this.tope = nuevoNodo;
    }

    public int pop() {
        if (this.estaVacia()) {
            return -1;
        }
        int valor = this.tope.dato;
        this.tope = this.tope.siguiente;
        return valor;
    }

    public int peek() {
        if (this.estaVacia()) {
            return -1;
        }
        return this.tope.dato;
    }

    public boolean estaVacia() {
        return this.tope == null;
    }

    public void imprimirPila() {
        Nodo actual = this.tope;
        System.out.print("Tope -> ");
        while (actual != null) {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        }
        System.out.println("null");
    }
}
