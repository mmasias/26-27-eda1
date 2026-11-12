
public class Cola {
    private Nodo primero;
    private Nodo ultimo;

    public Cola() {
        primero = null;
        ultimo = null;
    }

    public void encolar(int valor) {
        Nodo nuevo = new Nodo(valor, null);
        if (ultimo == null) {
            primero = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
        }
        ultimo = nuevo;
    }

    public int desencolar() {
        assert !estaVacia();
        int valor = primero.getValor();
        primero = primero.getSiguiente();
        if (primero == null) {
            ultimo = null;
        }
        return valor;
    }

    public boolean estaVacia() {
        return primero == null;
    }
}
