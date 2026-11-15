Public class ColaConPila {
    private Pila entrada;
    private Pila salida;

    public class ColaconPila(){
        this.entrada = new pila();
        this.salida = new pila();
    }

    public void encolar (int valor){
        this.entrada.apilar(int valor)
    }

    public int desencolar(){
        assert !estaVacia() : "Precondicion violada: no se puede desencolar una cola vacia"
        prepararSalida();
        return this.salida.desapilar();
    }

    public int verfrente(){
        assert !estaVacia() : "Precondicion violada: no se puede consultar el frente de una cola vacia";
        prepararSalida();
        int valor = this.salida.desapilar();
        this.salida.apilar(valor)
        return valor;
    }

    public boolean estaVacia(){
        return this.entrada.estaVacia && this.salida.estaVacia();
    }

    private void prepararSalida() {
        if (this.salida.estaVacia()) {
            while (!this.entrada.estaVacia()) {
                this.salida.apilar(this.entrada.desapilar());
            }
        }
    }
}