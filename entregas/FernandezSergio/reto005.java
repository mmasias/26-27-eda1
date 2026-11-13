class Nodo {
    int dato;
    Nodo siguiente;

    public Nodo(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

class Cola2{
        
    private Nodo frente;
    private Nodo nodoFinal;

    public Cola2() {
        this.frente = null;
        this.nodoFinal = null;
    }
        
    public void encolar2(int valor) {
        Nodo nuevoNodo = new Nodo(valor);
        if (estaVacia2()) {
            frente = nuevoNodo;
            nodoFinal = nuevoNodo;
        } else {
            nodoFinal.siguiente = nuevoNodo;
            nodoFinal = nuevoNodo;
        }
    }

    public int desencolar2() {
        int valor = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            nodoFinal = null;
        }
        return valor;
    }

    public boolean estaVacia2() {
        return frente == null;
    }
}

class Pila{
    
    Cola2 colaIntermedia = new Cola2();
    Cola2 colaFinal = new Cola2();

    public void apilar(int valor){
        while(!colaFinal.estaVacia2()){
            colaIntermedia.encolar2(colaFinal.desencolar2());
        }
        colaFinal.encolar2(valor);
        while(!colaIntermedia.estaVacia2()){
            colaFinal.encolar2(colaIntermedia.desencolar2());
        }
    }

    public int desapilar(){
        return colaFinal.desencolar2();
    }

    public boolean estaVacia(){
        return colaFinal.estaVacia2();
    }
}

class Cola{
    Pila pilaIntermedia = new Pila();
    Pila pilaFinal = new Pila();

    public void encolar(int valor){
        pilaIntermedia.apilar(valor);
    }

    public int desencolar(){
        int resultado;
        
        while (!pilaIntermedia.estaVacia()) {
            pilaFinal.apilar(pilaIntermedia.desapilar());    
        }
        resultado = pilaFinal.desapilar();
        while(!pilaFinal.estaVacia()){
            pilaIntermedia.apilar(pilaFinal.desapilar());
        }

        return resultado;
    }

    public boolean estaVacia(){
        return pilaFinal.estaVacia();
    }
}

public class reto005{
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