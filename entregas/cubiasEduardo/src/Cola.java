public class Cola {
    private Pila pila;

    public Cola() {
        this.pila = new Pila();
    }

    public void encolar(int valor) {
        pila.apilar(valor);
    }

    public boolean estaVacia() {
        return pila.estaVacia();
    }

    public int desencolar() {
        assert !this.estaVacia() : "Violación de precondición: La cola está vacía.";

        Pila pilaVolteada = new Pila();

        while (!pila.estaVacia()) {
            pilaVolteada.apilar(pila.desapilar());
        }

        int valorDesencolado = pilaVolteada.desapilar();

        while (!pilaVolteada.estaVacia()) {
            pila.apilar(pilaVolteada.desapilar());
        }

        return valorDesencolado;
    }
    
}
