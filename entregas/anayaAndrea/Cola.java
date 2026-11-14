
public class Cola {

    private PilaAuxiliar entrada;
    private PilaAuxiliar salida;

    public Cola() {
        entrada = new PilaAuxiliar();
        salida = new PilaAuxiliar();
    }

    public void encolar(int valor) {
        entrada.apilar(valor);
    }

    public int desencolar() {

        assert !estaVacia();

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
