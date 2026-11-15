public class Pila {
    private Cola cola = new Cola();
    private Cola auxiliar = new Cola();

    public void apilar(int valor) {
        cola.enqueue(valor);
    }

    public boolean estaVacia() {
        return cola.estaVacia();
    }

    public int desapilar() {
        assert !estaVacia();

        int valorDesapilar = 0;

        while (!cola.estaVacia()) {
            int valorCambio = cola.dequeue();

            if (cola.estaVacia()) {
                valorDesapilar = valorCambio;
            } else {
                auxiliar.enqueue(valorCambio);
            }
        }

        Cola temporal = cola;
        cola = auxiliar;
        auxiliar = temporal;

        return valorDesapilar;
    }
}