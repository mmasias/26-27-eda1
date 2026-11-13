
public class Pila {

    private Cola cola;

    public Pila() {
        cola = new Cola();

    }

    public void apilar(int valor) {
        Cola nuevaCola = new Cola();
        nuevaCola.encolar(valor);

        while (!cola.estaVacia()) {
            int actual = cola.desencolar();
            nuevaCola.encolar(actual);
        }

        cola = nuevaCola;
    }

    public int desapilar() {

        assert !estaVacia();
        return cola.desencolar();
    }

    public boolean estaVacia() {
        return cola.estaVacia();
    }
}
