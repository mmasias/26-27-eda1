public class Cola {
    private Pila pilaEntrada;
    private Pila pilaSalida;

    public Cola() {
        this.pilaEntrada = new Pila();
        this.pilaSalida = new Pila();
    }

    public void encolar(int valor) {
        this.pilaEntrada.apilar(valor);
    }

    public int desencolar() {
        assert !this.estaVacia(); 
        
        if (this.pilaSalida.estaVacia()) {
            while (!this.pilaEntrada.estaVacia()) {
                this.pilaSalida.apilar(this.pilaEntrada.desapilar());
            }
        }
        return this.pilaSalida.desapilar();
    }

    public boolean estaVacia() {
        return this.pilaEntrada.estaVacia() && this.pilaSalida.estaVacia();
    }
}