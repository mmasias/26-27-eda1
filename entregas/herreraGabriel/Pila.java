public class Pila {
    Nodo tope;

    public void apilar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = tope;
        tope = nuevo;
    }

    public Object desapilar() {
        if (tope == null) return null;
        Object dato = tope.dato;
        tope = tope.siguiente;
        return dato;
    }
}