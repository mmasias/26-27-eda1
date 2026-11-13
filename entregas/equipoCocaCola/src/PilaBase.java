public class PilaBase {

    private Node top = null;
    private int size = 0;

    public void apilar(int value) {
        Node newNode = new Node(value);
        newNode.setNext(this.top);
        this.top = newNode;
        this.size++;
    }

    public int desapilar() {
        assert !estaVacia() : "La pila está vacía.";

        int value = this.top.getValue();
        this.top = this.top.getNext();
        this.size--;
        return value;
    }

    public boolean estaVacia() {
        return this.size == 0;
    }
}