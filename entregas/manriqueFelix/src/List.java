public class List {
    private Node first;
    private Node last;
    private int size;

    public int comparisons;

    public void add(String value) {
        Node node = new Node(value);
        if (first == null) {
            first = node;
        } else {
            last.next = node;
        }
        last = node;
        size++;
    }

    public boolean contains(String value) {
        comparisons = 0;
        Node current = first;
        while (current != null) {
            comparisons++;
            if (current.data.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /** Linear search + unlink. */
    public boolean remove(String value) {
        comparisons = 0;
        Node previous = null;
        Node current = first;
        while (current != null) {
            comparisons++;
            if (current.data.equals(value)) {
                if (previous == null) {
                    first = current.next;
                } else {
                    previous.next = current.next;
                }
                if (current == last) {
                    last = previous;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public String[] toArray() {
        String[] result = new String[size];
        Node current = first;
        int i = 0;
        while (current != null) {
            result[i] = current.data;
            i++;
            current = current.next;
        }
        return result;
    }

    @Override
    public String toString() {
        String text = "[";
        Node current = first;
        while (current != null) {
            text += current.data;
            if (current.next != null) {
                text += ", ";
            }
            current = current.next;
        }
        return text + "]";
    }
}