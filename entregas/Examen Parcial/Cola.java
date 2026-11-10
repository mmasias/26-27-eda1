public class Cola {
    private Pila pilaEntrada;
    private Pila pilaSalida;

    public Cola() {
        this.pilaEntrada = new Pila();
        this.pilaSalida = new Pila();
    }

    public void encolar(int valor) {
        pilaEntrada.apilar(valor);
    }

    public int desencolar() {
        assert !estaVacia() : "La cola está vacía";

        if (pilaSalida.estaVacia()) {
            while (!pilaEntrada.estaVacia()) {
                pilaSalida.apilar(pilaEntrada.desapilar());
            }
        }

        return pilaSalida.desapilar();
    }

    public boolean estaVacia() {
        return pilaEntrada.estaVacia() && pilaSalida.estaVacia();
    }
}