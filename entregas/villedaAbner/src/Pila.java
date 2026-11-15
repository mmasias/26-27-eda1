public class Pila {
    private Nodo cima;

    public Pila() {
        cima = null;
    }

    public void apilar(int valor) {
        cima = new Nodo(valor, cima);
    }

    public int desapilar() {
        assert !estaVacia();
        int valor = cima.valor;
        cima = cima.siguiente;
        return valor;
    }

    public boolean estaVacia() {
        return cima == null;
    }
}