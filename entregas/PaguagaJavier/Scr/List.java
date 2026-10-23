public class List {
    private Nodo head;
    private int size;

    public List() {
        this.head = null;
        this.size = 0;
    }

    public void add(String data) {
        Nodo newNodo = new Nodo(data);
        if (head == null) {
            head = newNodo;
        } else {
            Nodo current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNodo;
        }
        size++;
    }

    public boolean remove(String data) {
        if (head == null) return false;

        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return true;
        }

        Nodo current = head;
        while (current.next != null && !current.next.data.equals(data)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            size--;
            return true;
        }
        return false;
    }

    public boolean contains(String data) {
        Nodo current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public String[] toArray() {
        String[] arr = new String[size];
        Nodo current = head;
        int i = 0;
        while (current != null) {
            arr[i++] = current.data;
            current = current.next;
        }
        return arr;
    }
}