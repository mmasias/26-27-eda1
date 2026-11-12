
public class Pila {

    private int[] elementos;
    private int cima;

    public Pila() {
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
