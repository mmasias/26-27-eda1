public class Queue {
    private QueueNode first;
    private QueueNode last;

    private static class QueueNode {
        Papeleta value;
        QueueNode next;

        public QueueNode(Papeleta value) {
            this.value = value;
            this.next = null;
        }
    }

    public Queue() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertEnd(Papeleta value) {
        QueueNode newNode = new QueueNode(value);

        if (last == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
    }

    public Papeleta removeFirst() {
        if (first == null) {
            return null;
        }

        Papeleta value = first.value;
        first = first.next;

        if (first == null) {
            last = null;
        }

        return value;
    }
}