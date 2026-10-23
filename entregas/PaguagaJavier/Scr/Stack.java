public class Stack {
    private Nodo top;
    private int size;

    public Stack() {
        this.top = null;
        this.size = 0;
    }

    public void push(String data) {
        Nodo newNodo = new Nodo(data);
        newNodo.next = top;
        top = newNodo;
        size++;
    }

    public String pop() {
        if (isEmpty()) return null;
        String data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }
}