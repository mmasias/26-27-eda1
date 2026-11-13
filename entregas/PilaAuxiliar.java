
public class PilaAuxiliar {

    private int[] elementos;
    private int cima;

    public PilaAuxiliar() {
        elementos = new int[100];
        cima = 0;
    }

    public void apilar(int valor) {
        assert cima < elementos.length;

        elementos[cima] = valor;
        cima++;
    }

    public int desapilar() {
        assert !estaVacia();

        cima--;
        return elementos[cima];
    }

    public boolean estaVacia() {
        return cima == 0;
    }
}
