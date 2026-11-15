public class ColaInferior {
    private Nodo frente;
    private Nodo fin;

    public ColaInferior() {
        this.frente = null;
        this.fin = null;
    }

    public void encolar(int valor) {
        Nodo nuevo = new Nodo(valor, null);
        if (estaVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
    }

    public int desencolar() {
        assert !estaVacia() : "Precondición violada: la cola inferior está vacía";
        int valor = frente.getValor();
        frente = frente.getSiguiente();
        if (frente == null) {
            fin = null;
        }
        return valor;
    }

    public boolean estaVacia() {
        return frente == null;
    }
}