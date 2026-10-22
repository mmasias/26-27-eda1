package entregas.uretaAaron;

public class Nodo {

    private String value;
    private Nodo next;

    public Nodo(String value) {
        this.value = value;
        this.next = null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String newValue) {
        this.value = newValue;
    }

    public Nodo getNext() {
        return next;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }
}