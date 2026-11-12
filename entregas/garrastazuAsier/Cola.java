package entregas.garrastazuAsier;

public class Cola {

    private Pila primera;
    private Pila ultima;

    public Cola() {
        primera = new Pila();
        ultima = new Pila();
    }

    public void encolar(int valor) {
        ultima.apilar(valor);
    }

    public int desencolar() {
        assert !estaVacia();

        if (primera.estaVacia()) {
            while (!ultima.estaVacia()) {
                primera.apilar(ultima.desapilar());
            }
        }

        return primera.desapilar();
    }

    public boolean estaVacia() {
        return primera.estaVacia() && ultima.estaVacia();
    }
}