public class Stack {
    private Node top;
    private int size;

    public void push(String value) {
        Node node = new Node(value);
        node.next = top;
        top = node;
        size++;
    }

    /** Takes the ballot on top. Returns null if the stack is empty. */
    public String pop() {
        if (top == null) {
            return null;
        }
        String value = top.data;
        top = top.next;
        size--;
        return value;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }
}