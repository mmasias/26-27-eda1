package entregas.garciaJacobo;

public class Queue {
    private Node primero;
    private Node ultimo;

    public Queue() {
        primero = null;
        ultimo = null;
    }

    public void insertarAlFinal(String value) {
        Node nuevo = new Node(value);
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setNext(nuevo);
            ultimo = nuevo;
        }
    }

    public String sacarPrimero() {
        assert primero != null;
        String valor = primero.getValue();
        primero = primero.getNext();
        if (primero == null) {
            ultimo = null;
        }
        return valor;
    }

    public boolean estaVacia(){
        return primero==null;
    }
}
