
public class List {

    private Node first = null;

    public int size() {
        int total = 0;
        Node actual = first;

        while (actual != null) {
            total++;
            actual = actual.getNext();
        }

        return total;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertEnd(String value) {
        Node nuevo = new Node(value);

        if (isEmpty()) {
            first = nuevo;
            return;
        }

        Node actual = first;

        while (actual.getNext() != null) {
            actual = actual.getNext();
        }

        actual.setNext(nuevo);
    }

    public boolean contains(String value) {
        Node actual = first;

        while (actual != null) {
            if (actual.getValue().equals(value)) {
                return true;
            }

            actual = actual.getNext();
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

        Node anterior = first;
        Node actual = first.getNext();

        while (actual != null) {
            if (actual.getValue().equals(value)) {
                anterior.setNext(actual.getNext());
                return true;
            }

            anterior = actual;
            actual = actual.getNext();
        }

        return false;
    }

    public String[] listAll() {
        String[] elementos = new String[size()];
        Node actual = first;
        int posicion = 0;

        while (actual != null) {
            elementos[posicion] = actual.getValue();
            posicion++;
            actual = actual.getNext();
        }

        return elementos;
    }
}