package entregas.garcíaJacobo;

public class Pila {

    private Cola cola;
    private int numeroPersonasEnCola;
    private int ultimo;
    private int tamaño;

    public Pila() {
        cola = new Cola();
        numeroPersonasEnCola = 0;
        ultimo = 0;
        tamaño = 0;
    }

    public void apilar(int valor) {

        Cola nuevaCola = new Cola();

        nuevaCola.encolar(valor);

        numeroPersonasEnCola = tamaño;

        while (numeroPersonasEnCola > 0) {
            int actual = cola.desencolar();
            nuevaCola.encolar(actual);
            numeroPersonasEnCola--;
        }

        cola = nuevaCola;
        ultimo = valor;
        tamaño++;
    }

    public int desapilar() {

        assert !estaVacia();

        int valor = ultimo;
        cola.desencolar();
        tamaño--;

        if (!cola.estaVacia()) {

            Cola nuevaCola = new Cola();

            numeroPersonasEnCola = tamaño;

            while (numeroPersonasEnCola > 0) {

                int actual = cola.desencolar();

                if (numeroPersonasEnCola == tamaño) {
                    ultimo = actual;
                }

                nuevaCola.encolar(actual);
                numeroPersonasEnCola--;
            }

            cola = nuevaCola;

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
