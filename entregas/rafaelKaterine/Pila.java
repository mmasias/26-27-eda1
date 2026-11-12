
public class Pila {

    private Cola cola;
    private int cantidad;

    public Pila() {
        cola = new Cola();
        cantidad = 0;
    }

    public void apilar(int valor) {

        cola.encolar(valor);

        for (int i = 0; i < cantidad; i++) {
            cola.encolar(cola.desencolar());
        }

        cantidad++;
    }

    public int desapilar() {

        assert !estaVacia();

        cantidad--;

        return cola.desencolar();
    }

    public boolean estaVacia() {
        return cantidad == 0;
    }
}
