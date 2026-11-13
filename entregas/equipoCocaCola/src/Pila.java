class Pila{
    private Cola cola;

    public Pila(){
        cola = new Cola();
    }

    public void apilar(int valor){
        cola.encolar(valor);
    }

    public int desapilar(){
        assert !estaVacia() : "La pila está vacía.";
        Cola colaAuxiliar = new Cola();
        int ultimoValor = 0;

        while(!cola.estaVacia()){
            ultimoValor = cola.desencolar();

            if (!cola.estaVacia()) {
                colaAuxiliar.encolar(ultimoValor);
            }
        }

        while (!colaAuxiliar.estaVacia()) {
            cola.encolar(colaAuxiliar.desencolar());
        }

        return ultimoValor;
    }

    public boolean estaVacia(){
        return cola.estaVacia();
    }
}