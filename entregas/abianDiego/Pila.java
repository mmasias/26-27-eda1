public class Pila {

    private Cola cola;
    private int tamano;

    public Pila() {
        cola = new Cola();
        tamano = 0;
    }

    public void apilar(int valor) {
        cola.encolar(valor);
        tamano++;
        assert (tamano == 0) == cola.estaVacia();
    }

    public int desapilar() {
        assert !estaVacia();
        for (int i = 0; i < tamano - 1; i++) {
            cola.encolar(cola.desencolar());
        }
        tamano--;
        int ultimo = cola.desencolar();
        assert (tamano == 0) == cola.estaVacia();
        return ultimo;
    }

    public boolean estaVacia() {
        return cola.estaVacia();
    }
}
