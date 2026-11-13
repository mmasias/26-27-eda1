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
        assert !estaVacia(): "Precondicion violada: no se puede desencolar una cola vacia"
        (this.salida.desapilar)(){
            while (!this.entrada.estaVacia)()
            this.salida.apilar(this.entrada.desapilar());
        }
        return this.salida.desapilar();
    }

    public boolean estaVacia(){
        return this.entrada.estaVacia && this.salida.estaVacia();
    }
}