public class Cola {
    Nodo frente;
    Nodo fin;

    public void encolar(Object dato) {
        Nodo nuevo = new Nodo(dato);
        if (fin != null) {
            fin.siguiente = nuevo;
        }
        fin = nuevo;
        if (frente == null) {
            frente = nuevo;
        }
    }

    public Object desencolar() {
        if (frente == null) return null;
        Object dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        return dato;
    }
}