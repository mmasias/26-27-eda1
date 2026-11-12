public class Cola {
    private Pila entrada;
    private Pila salida;

    public Cola() {
        entrada = new Pila();
        salida = new Pila();
    }

    public void encolar(int valor) {
        entrada.apilar(valor);
    }

    public int desencolar() {
        assert !estaVacia();
        if (salida.estaVacia()) {
            volcarEntradaEnSalida();
        }
        return salida.desapilar();
    }

    public boolean estaVacia() {
        return entrada.estaVacia() && salida.estaVacia();
    }

    private void volcarEntradaEnSalida() {
        while (!entrada.estaVacia()) {
            salida.apilar(entrada.desapilar());
        }
    }
}