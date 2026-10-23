public class Pila {

    private Nodo cima;
    private int cantidad;

    public Pila() {
        cima = null;
        cantidad = 0;
    }

    public void apilar(String dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = cima;
        cima = nuevo;
        cantidad++;
    }

    
    public String desapilar() {
    if (cima == null) {
        throw new IllegalStateException(
            "La pila esta vacia."
        );
    }

    String dato = cima.dato;
    cima = cima.siguiente;
    cantidad--;

    return dato;
    }

    public String verCima() {
    if (cima == null) {
        throw new IllegalStateException(
            "La pila esta vacia."
        );
    }

    return cima.dato;
    }

    public boolean estaVacia() {
    return cima == null;
    }

    public int tamano() {
    return cantidad;
    }

}
