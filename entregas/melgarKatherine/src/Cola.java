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
        assert !estaVacia() : "Error: La cola esta vacia";
        if (salida.estaVacia()) {
            while (!entrada.estaVacia()) {
                salida.apilar(entrada.desapilar());
            }
        }
        return salida.desapilar();
    }
    public boolean estaVacia() {
        return entrada.estaVacia() && salida.estaVacia();
    }
}