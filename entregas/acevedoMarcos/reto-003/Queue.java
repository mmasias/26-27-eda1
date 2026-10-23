
public class Queue {

    private Node primero;
    private Node ultimo;

    public Queue() {
        this.primero = null;
        this.ultimo = null;
    }

    public void insertarAlFinal(String valor) {
        Node nuevoNodo = new Node(valor);

        if (estaVacia()) {
            primero = nuevoNodo;
        } else {
            ultimo.setNext(nuevoNodo);
        }

        ultimo = nuevoNodo;
    }

    public String sacarPrimero() {
        assert !estaVacia();

        String resultado = primero.getValue();
        primero = primero.getNext();

        if (primero == null) {
            ultimo = null;
        }

        return resultado;
    }

    public boolean estaVacia() {
        return this.primero == null;
    }
}
