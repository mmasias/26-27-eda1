
public class Cola {

    private static final int CAPACIDAD = 4;

    private Pila entrada;
    private Pila salida;
    private int cantidad;

    public Cola() {
        entrada = new Pila();
        salida = new Pila();
        cantidad = 0;
    }

    public void encolar(int valor) {
        assert cantidad < CAPACIDAD;

        entrada.apilar(valor);
        cantidad++;
    }

    public int desencolar() {
        assert !estaVacia();

        if (salida.estaVacia()) {
            while (!entrada.estaVacia()) {
                salida.apilar(entrada.desapilar());
            }
        }

        cantidad--;
        return salida.desapilar();
    }

    public boolean estaVacia() {
        return cantidad == 0;
    }
}
