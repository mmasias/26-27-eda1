public class Cola {
    private Nodo frente;
    private Nodo fin;

    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    public void encolar(int valor) {
        Nodo nuevoNodo = new Nodo(valor);
        if (estaVacia()) {
            frente = nuevoNodo;
        } else {
            fin.siguiente = nuevoNodo;
        }
        fin = nuevoNodo;
    }

    public int desencolar() {
        int valor = frente.valor;
        frente = frente.siguiente;
        
        if (frente == null) {
            fin = null;
        }
        
        return valor;
    }

    public boolean estaVacia() {
        return frente == null;
    }
}