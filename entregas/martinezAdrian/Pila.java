public class Pila {
    private ColaInferior cola;

    public Pila() {
        this.cola = new ColaInferior();
    }

    public void apilar(int valor) {
        ColaInferior nueva = new ColaInferior();
        nueva.encolar(valor);

        while (!cola.estaVacia()) {
            nueva.encolar(cola.desencolar());
        }

        cola = nueva;
    }

    public int desapilar() {
        assert !estaVacia() : "Precondición violada: la pila está vacía";
        return cola.desencolar();
    }

    public boolean estaVacia() {
        return cola.estaVacia();
    }
}