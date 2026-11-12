public class Pila{
    private Cola cola;
    private int tamaño;
    
    public Pila(){
        cola = new Cola();
        tamaño=0;
    }

    public void apilar(int valor){
        cola.encolar(valor);
        for (int i = 0; i<tamaño; i++){
            cola.encolar(cola.desencolar());
        }
        tamaño++;
    }

    public int desapilar(){
        assert !estaVacia() : "No es posible desapilar una pila vacia";
        tamaño--;
        return cola.desencolar();
    }

    public boolean estaVacia(){
        return cola.estaVacia();
    }
}