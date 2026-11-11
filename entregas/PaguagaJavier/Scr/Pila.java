public class Pila {

    private Cola cola;
    private int tamano;

    public Pila() {
        this.cola = new Cola();
        this.tamano = 0;
    }

    public void apilar(int valor){
                if (!estaVacia()) {
            empujarValores(valor);
        } else {
            this.cola.encolar(valor);
        }
        this.tamano++;
    }

    public int desapilar(){
        assert !estaVacia() : "Precondición violada: no se puede desapilar de una pila vacía";
        this.tamano--;
        return this.cola.desencolar();
    }

    public boolean estaVacia(){
        return this.cola.estaVacia();
    }

    private void empujarValores(int valor){
        this.cola.enconlar(valor);
        for (int i = 0; i < this.tamano; i++) {
            this.cola.encolar(this.cola.desencolar());
        }
    }
}