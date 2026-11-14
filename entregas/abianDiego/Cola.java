public class Cola {

    private PilaConNodos entrada;
    private PilaConNodos salida;

    public Cola() {
        entrada = new PilaConNodos();
        salida = new PilaConNodos();
    }

    public void encolar(int valor) {
        entrada.apilar(valor);
    }

    public int desencolar() {
        assert !estaVacia() : "No es posible desencolar una cola vacia";
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
