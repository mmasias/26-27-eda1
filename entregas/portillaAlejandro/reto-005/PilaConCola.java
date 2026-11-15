
public class PilaConCola {
    private ColaBase cola;
    private int cantidad;

    public PilaConCola() {
        cola = new ColaBase();
        cantidad = 0;
    }

    public void apilar(int valor) {
        assert cantidad < 4;

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
