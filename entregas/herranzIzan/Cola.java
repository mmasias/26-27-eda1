public class Cola {

    private Nodo frente;
    private Nodo ultimo;
    private int cantidad;

    public Cola() {
        frente = null;
        ultimo = null;
        cantidad = 0;
    }

    public void encolar(String dato) {
        Nodo nuevo = new Nodo(dato);

        if (frente == null) {
            frente = nuevo;
        } else {
            ultimo.siguiente = nuevo;
        }

        ultimo = nuevo;
        cantidad++;
    }

    public String desencolar() {
    if (frente == null) {
        throw new IllegalStateException(
            "La cola esta vacia."
        );
    }

    String dato = frente.dato;
    frente = frente.siguiente;

    if (frente == null) {
        ultimo = null;
    }

    cantidad--;
    return dato;
    }

    public String verFrente() {
    if (frente == null) {
        throw new IllegalStateException(
            "La cola esta vacia."
        );
    }

    return frente.dato;
    }

    public boolean estaVacia() {
    return frente == null;
    }

    public int tamano() {
    return cantidad;
    }

}
