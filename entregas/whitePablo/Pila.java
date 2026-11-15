public class Pila {
    private int cima;
    private int cantidad;
    private Cola resto;

    public Pila() {
        cantidad = 0;
        resto = null;
    }

    public void apilar(int valor) {
        if (cantidad > 0) {
            if (resto == null) {
                resto = new Cola();
            }
            resto.encolar(cima);
            for (int i = 0; i < cantidad - 1; i++) {
                resto.encolar(resto.desencolar());
            }
        }
        cima = valor;
        cantidad++;
    }

    public int desapilar() {
        assert !estaVacia();
        int valor = cima;
        cantidad--;
        if (cantidad > 0) {
            cima = resto.desencolar();
        }
        return valor;
    }

    public boolean estaVacia() {
        return cantidad == 0;
    }
}