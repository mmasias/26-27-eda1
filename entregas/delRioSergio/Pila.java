public class Pila {

    private Cola cola;
    private int tamano;

    public Pila() {
        this.cola = new Cola();
        this.tamano = 0;
    }

    public void apilar(int valor) {
        this.cola.encolar(valor);
        for (int i = 0; i < this.tamano; i++) {
            this.cola.encolar(this.cola.desencolar());
        }
        this.tamano++;
    }

    public int desapilar() {
        assert !estaVacia();
        this.tamano--;
        return this.cola.desencolar();
    }

    public boolean estaVacia() {
        return this.cola.estaVacia();
    }
}