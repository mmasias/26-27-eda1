public class Cola {

    private Pila pila;

    public Cola() {
        pila = new Pila();
    }

    public void encolar(int valor) {
        pila.apilar(valor);
    }

    public int desencolar() {
        assert !estaVacia();
        Pila pilaDePaso = new Pila();
        while (!pila.estaVacia()) {
            pilaDePaso.apilar(pila.desapilar());
        }
        int primero = pilaDePaso.desapilar();
        while (!pilaDePaso.estaVacia()) {
            pila.apilar(pilaDePaso.desapilar());
        }
        return primero;
    }

    public boolean estaVacia() {
        return pila.estaVacia();
    }
}
