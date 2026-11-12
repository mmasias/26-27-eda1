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
        return 0;
    }

    public boolean estaVacia() {
        return true;
    }
}