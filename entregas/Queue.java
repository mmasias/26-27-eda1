package entregas;

public class Queue {

    private Nodo first;
    private Nodo last;
    private int size;

    public Queue() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public void enqueue(String value) {
        Nodo newNode = new Nodo(value);
        if (this.isEmpty()) {
            this.first = newNode;
        } else {
            this.last.setNext(newNode);
        }
        this.last = newNode;
        this.size++;
    }

    public String dequeue() {
        if (this.isEmpty()) {
            return null;
        }
        String value = this.first.getValue();
        this.first = this.first.getNext();
        if (this.first == null) {
            this.last = null;
        }
        this.size--;
        return value;
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    public int size() {
        return this.size;
    }
}