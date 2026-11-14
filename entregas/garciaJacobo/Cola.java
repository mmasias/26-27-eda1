
public class Cola {
    private PilaBase entrada;
    private PilaBase salida;

    public Cola() {
        entrada = new PilaBase();
        salida = new PilaBase();
    }

    public void encolar(int valor) {
        entrada.apilar(valor);

    }

    public int desencolar() {
        assert !estaVacia();
        if (salida.estaVacia()) {

            while (!entrada.estaVacia()) {
                int valor = entrada.desapilar();
                salida.apilar(valor);

            }
        }
        
        return salida.desapilar();

    }

    public boolean estaVacia() {
        return entrada.estaVacia() && salida.estaVacia();
    }
}
