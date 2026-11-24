public class PilaConCola {

    private Cola cola;
    private int numeroElementos;

    public PilaConCola() {
        cola = new Cola();
        numeroElementos = 0;
    }

    public void apilar(int valor) {

        cola.encolar(valor);

    
        for (int i = 0; i < numeroElementos; i++) {
            cola.encolar(cola.desencolar());
        }

        numeroElementos++;
    }

    public int desapilar() {
        assert !estaVacia() : "La pila esta vacia";

        int valor = cola.desencolar();
        numeroElementos--;

        return valor;
    }

    public boolean estaVacia() {
        return cola.estaVacia();
    }
}