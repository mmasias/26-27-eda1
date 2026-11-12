public class Cola {
    private Pila pilaOriginal;
    private Pila pilaEspejo;

    public Cola() {
        this.pilaOriginal = new Pila();
        this.pilaEspejo = new Pila();
    }

    public void encolar(int valor) {
        pilaOriginal.apilar(valor);
    }

    public int desencolar() {
        assert !estaVacia();

        while (!pilaOriginal.estaVacia()) {
            pilaEspejo.apilar(pilaOriginal.desapilar());
        }

        int valorExtraido = pilaEspejo.desapilar();

        while (!pilaEspejo.estaVacia()) {
            pilaOriginal.apilar(pilaEspejo.desapilar());
        }

        return valorExtraido;
    }

    public boolean estaVacia() {
        return pilaOriginal.estaVacia() && pilaEspejo.estaVacia();
    }
}
