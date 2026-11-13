public class Pila {

    private Cola cola;
    private int cantidad;

    public Pila() {
        this(new Cola(), 0);
    }

    private Pila(Cola cola, int cantidad) {
        this.cola = cola;
        this.cantidad = cantidad;
    }

    public void apilar(int elemento) {
        this.cola.encolar(elemento);
        for (int i = 0; i < this.cantidad; i++) {
            int temporal = this.cola.desencolar();
            this.cola.encolar(temporal);
        }
        this.cantidad++;
    }

    public int desapilar() {
        this.cantidad--;
        return this.cola.desencolar();
    }

    public boolean estaVacia() {
        return this.cantidad == 0;
    }
}