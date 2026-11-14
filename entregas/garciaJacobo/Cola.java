
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
        int valor = primero.getValor();
        primero = primero.getSiguiente();
        if (primero == null) {
            ultimo = null;
        }
        return valor;
    }

    public boolean estaVacia() {
        return primero == null;
    }
}
