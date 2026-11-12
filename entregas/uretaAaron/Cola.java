public class Cola {
    private Pila pEntrada;
    private Pila pSalida;

    public Cola() {
        pEntrada = new Pila();
        pSalida = new Pila();
    }

    public void encolar(int valor) {
        pEntrada.apilar(valor);
    }

    public int desencolar() {
        assert !estaVacia();

        if (pSalida.estaVacia()) {
            while (!pEntrada.estaVacia()) {
                pSalida.apilar(pEntrada.desapilar());
            }
        }

        return pSalida.desapilar();
    }

    public boolean estaVacia() {
        return pEntrada.estaVacia() && pSalida.estaVacia();
    }
}