package entregas.uretaAaron;

public class Lista {

    private Nodo first;

    public Lista() {
        this.first = null;
    }

    public int size() {
        int count = 0;
        Nodo iterator = this.first;
        while (iterator != null) {
            count++;
            iterator = iterator.getNext();
        }
        return count;
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    public void insertEnd(String value) {
        Nodo newNode = new Nodo(value);
        if (this.first == null) {
            this.first = newNode;
        } else {
            Nodo iterator = this.first;
            while (iterator.getNext() != null) {
                iterator = iterator.getNext();
            }
            iterator.setNext(newNode);
        }
    }

    public boolean contains(String value) {
        Nodo iterator = this.first;
        while (iterator != null) {
            if (iterator.getValue().equals(value)) {
                return true;
            }
            iterator = iterator.getNext();
        }
        return false;
    }

    public boolean delete(String value) {
        if (this.first == null) {
            return false;
        }
        if (this.first.getValue().equals(value)) {
            this.first = this.first.getNext();
            return true;
        }
        Nodo previous = this.first;
        Nodo iterator = this.first.getNext();
        while (iterator != null) {
            if (iterator.getValue().equals(value)) {
                previous.setNext(iterator.getNext());
                return true;
            }
            previous = iterator;
            iterator = iterator.getNext();
        }
        return false;
    }

    public String[] listAll() {
        String[] array = new String[this.size()];
        int count = 0;
        Nodo iterator = this.first;
        while (iterator != null) {
            array[count] = iterator.getValue();
            count++;
            iterator = iterator.getNext();
        }
        return array;
    }
}