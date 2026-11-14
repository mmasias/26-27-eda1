
public class Pila {

    private Cola cola;
    private int elementos;

    public Pila() {
        cola = new Cola();
        elementos = 0;
    }

    public void apilar(int valor) {

        cola.encolar(valor);

        for (int i = 0; i < elementos; i++) {
            cola.encolar(cola.desencolar());
        }

        elementos++;
    }

    public int desapilar() {
        assert !estaVacia();

        elementos--;
        return cola.desencolar();
    }

    public boolean estaVacia() {
        return cola.estaVacia();
    }
}
