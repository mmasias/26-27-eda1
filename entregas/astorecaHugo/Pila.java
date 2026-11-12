public class Pila {

    private Cola cola;
    private int ultimo;
    private int posicionPersonaEnCola;

    public Pila() {
        cola = new Cola();
        ultimo = 0;
        posicionPersonaEnCola = 0;
    }

    public void apilar(int valor) {

        cola.encolar(valor);
        posicionPersonaEnCola++;

        int contador = posicionPersonaEnCola;

        while (contador > 0) {
            int actual = cola.desencolar();
            cola.encolar(actual);
            ultimo = actual;
            contador--;
        }
    }

    public int desapilar() {

        assert !estaVacia();

        int valor = ultimo;
        int contador = posicionPersonaEnCola;

        while (contador > 1) {
            int actual = cola.desencolar();
            cola.encolar(actual);
            contador--;
        }

        cola.desencolar();
        posicionPersonaEnCola--;

        if (!cola.estaVacia()) {

            contador = posicionPersonaEnCola;

            while (contador > 0) {
                int actual = cola.desencolar();
                cola.encolar(actual);
                ultimo = actual;
                contador--;
            }

        } else {
            ultimo = 0;
        }

        return valor;
    }

    public boolean estaVacia() {

        if (cola.estaVacia()) {
            return true;
        } else {
            return false;
        }
    }
}