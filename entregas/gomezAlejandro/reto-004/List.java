public class List {
    private Node first;

    public List() {
        first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        int size = 0;
        Node current = first;

        while (current != null) {
            size++;
            current = current.next;
        }

        return size;
    }

    public boolean contains(String value) {
        Node current = first;

        while (current != null) {
            if (current.value.equals(value)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public void insertEnd(String value) {
        Node newNode = new Node(value);

        if (first == null) {
            first = newNode;
            return;
        }

        Node current = first;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }

    public boolean delete(String value) {
        if (first == null) {
            return false;
        }

        if (first.value.equals(value)) {
            first = first.next;
            return true;
        }

        Node current = first;

        while (current.next != null) {
            if (current.next.value.equals(value)) {
                current.next = current.next.next;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public String[] toArray() {
        String[] values = new String[size()];
        Node current = first;
        int index = 0;

        while (current != null) {
            values[index] = current.value;
            index++;
            current = current.next;
        }

        return values;
    }

    public void listAll() {
        Node current = first;

        while (current != null) {
            System.out.println(current.value);
            current = current.next;
        }
    }
}