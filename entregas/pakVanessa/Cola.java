public class Cola {

    private Pila pila;
    private int cantidad;

    public Cola() {
        pila = new Pila();
        cantidad = 0;
    }

    public void encolar(int valor) {
        Pila auxiliar = new Pila();

        while (!pila.estaVacia()) {
            auxiliar.apilar(pila.desapilar());
        }

        pila.apilar(valor);

        while (!auxiliar.estaVacia()) {
            pila.apilar(auxiliar.desapilar());
        }

        cantidad++;
    }

    public int desencolar() {
        assert !estaVacia();

        cantidad--;
        return pila.desapilar();
    }

    public boolean estaVacia() {
        return cantidad == 0;
    }
}