
public class Lista {

    private Node first = null;

    public int size() {
        int total = 0;
        Node current = first;

        while (current != null) {
            total++;
            current = current.getNext();
        }

        return total;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertEnd(String value) {
        Node newNode = new Node(value);

        if (isEmpty()) {
            first = newNode;
            return;
        }

        Node current = first;

        while (current.getNext() != null) {
            current = current.getNext();
        }

        current.setNext(newNode);
    }

    public boolean contains(String value) {
        Node current = first;

        while (current != null) {
            if (current.getValue().equals(value)) {
                return true;
            }

            current = current.getNext();
        }

        return false;
    }

    public boolean delete(String value) {
        if (isEmpty()) {
            return false;
        }

        if (first.getValue().equals(value)) {
            first = first.getNext();
            return true;
        }

        Node previous = first;
        Node current = first.getNext();

        while (current != null) {
            if (current.getValue().equals(value)) {
                previous.setNext(current.getNext());
                return true;
            }

            previous = current;
            current = current.getNext();
        }

        return false;
    }

    public String[] listAll() {
        String[] elements = new String[size()];
        Node current = first;
        int index = 0;

        while (current != null) {
            elements[index] = current.getValue();
            index++;
            current = current.getNext();
        }

        return elements;
    }
}
