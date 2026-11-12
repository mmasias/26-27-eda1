public class Cola {
    private Pila pila;
    private Pila pilaAuxiliar;

    public Cola() {
        pila = new Pila();
        pilaAuxiliar = new Pila();
    }

    public void encolar(int valor) {
        pila.apilar(valor);
    }

    public int desencolar() {
        assert !estaVacia() : "Precondición violada: la cola está vacía";

        while (!pila.estaVacia()) {
            pilaAuxiliar.apilar(pila.desapilar());
        }

        int elemento = pilaAuxiliar.desapilar();

        while (!pilaAuxiliar.estaVacia()) {
            pila.apilar(pilaAuxiliar.desapilar());
        }

        return elemento;
    }

    public boolean estaVacia() {
        return pila.estaVacia();
    }
}