
public class PilaBase {

    private int elemento1;
    private int elemento2;
    private int elemento3;
    private int elemento4;
    private int cantidad;

    public PilaBase() {
        cantidad = 0;
    }

    public void apilar(int valor) {
        assert cantidad < 4;

        switch (cantidad) {
            case 0: elemento1 = valor; break;
            case 1: elemento2 = valor; break;
            case 2: elemento3 = valor; break;
            case 3: elemento4 = valor; break;
        }

        cantidad++;
    }

    public int desapilar() {
        assert !estaVacia();

        cantidad--;

        switch (cantidad) {
            case 0: return elemento1;
            case 1: return elemento2;
            case 2: return elemento3;
            default: return elemento4;
        }
    }

    public boolean estaVacia() {
        return cantidad == 0;
    }
}
