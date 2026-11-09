class Nodo{
    int dato;
    Nodo siguiente;

    public Nodo(int dato){
        this.dato = dato;
        this.siguiente = null;
    }
}

class Pila{
    private Nodo tope;

    public Pila(){
        this.tope = null;
    }

    public void apilar(int valor){
        Nodo nuevoNodo = new Nodo(valor);
        nuevoNodo.siguiente = tope;
        tope = nuevoNodo;
    }

    public int desapilar(){

        int valor = tope.dato;
        tope = tope.siguiente;
        return valor;
    }

    public boolean estaVacia(){
        return tope == null;
    }
}

class Cola{
    Pila pilaIntermedia = new Pila();
    Pila pilaFinal = new Pila();

    public void encolar(int valor){
        pilaIntermedia.apilar(valor);
        pilaFinal.apilar(pilaIntermedia.desapilar());
    }

    public int desencolar(){
        return pilaFinal.desapilar();
    }

    public boolean estaVacia(){
        return pilaFinal.estaVacia();
    }
}

public class ExamenParcial{
    public static void main(String[] args) {
        Cola cola = new Cola();

        cola.encolar(1);
        cola.encolar(2);
        cola.encolar(3);
        System.out.println(cola.desencolar());
        cola.encolar(4);
        System.out.println(cola.desencolar());
        System.out.println(cola.desencolar());
    }
}