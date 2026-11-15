
public class ColaBase {

    private int elemento1;
    private int elemento2;
    private int elemento3;
    private int elemento4;

    private int primero;
    private int ultimo;
    private int cantidad;

    public ColaBase() {
        primero = 0;
        ultimo = 0;
        cantidad = 0;
    }

    public void encolar(int valor) {
        assert cantidad < 4;

        switch (ultimo) {
            case 0: elemento1 = valor; break;
            case 1: elemento2 = valor; break;
            case 2: elemento3 = valor; break;
            case 3: elemento4 = valor; break;
        }

        ultimo = (ultimo + 1) % 4;
        cantidad++;
    }

    public int desencolar() {
        assert !estaVacia();

        int valor;

        switch (primero) {
            case 0: valor = elemento1; break;
            case 1: valor = elemento2; break;
            case 2: valor = elemento3; break;
            default: valor = elemento4; break;
        }

        primero = (primero + 1) % 4;
        cantidad--;

        return valor;
    }

    public boolean estaVacia() {
        return cantidad == 0;
    }
}
