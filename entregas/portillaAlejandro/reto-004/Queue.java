
public class Queue {

    private Node primero;
    private Node ultimo;

    public Queue() {
        this.primero = null;
        this.ultimo = null;
    }

    public void insertarAlFinal(String value) {

        Node nuevo = new Node(value);

        if (estaVacia()) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setNext(nuevo);
            ultimo = nuevo;
        }
    }

    public String sacarPrimero() {

        assert !estaVacia();

        String resultado = primero.getValue();

        primero = primero.getNext();

        if (estaVacia()) {
            ultimo = null;
        }

        return resultado;
    }

    public boolean estaVacia() {
        return primero == null;
    }
}