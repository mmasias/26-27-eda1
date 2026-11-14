public class PilaConNodos {

    private Nodo cima;

    public PilaConNodos() {
        cima = null;
    }

    public void apilar(int valor) {
        cima = new Nodo(valor, cima);
    }

    public int desapilar() {
        assert !estaVacia() : "No es posible desapilar una pila vacia";
        int valor = cima.getValor();
        cima = cima.getSiguiente();
        return valor;
    }

    public boolean estaVacia() {
        return cima == null;
    }
}
