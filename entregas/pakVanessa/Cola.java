public class Cola {

    private Pila entrada;
    private Pila salida;

    public Cola() {
        entrada = new Pila();
        salida = new Pila();
    }

    public void encolar(int numero) {
        entrada.apilar(numero);
    }

    public int desencolar() {
        assert !estaVacia() : "No hay elementos en la cola";

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