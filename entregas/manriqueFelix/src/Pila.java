public class Pila {
    private Nodo cima;

    public Pila() {
        this.cima = null;
    }

    public void apilar(int valor) {
        this.cima = new Nodo(valor, this.cima);
    }

    public int desapilar() {
        assert !this.estaVacia();
        int valor = this.cima.getValor();
        this.cima = this.cima.getSiguiente();
        return valor;
    }

    public boolean estaVacia() {
        return this.cima == null;
    }
}