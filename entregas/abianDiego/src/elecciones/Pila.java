package elecciones;

class Pila {
    private Nodo cima;
    private int longitud;

    Pila() {
        cima = null;
        longitud = 0;
    }

    void apilar(String valor) {
        Nodo nuevo = new Nodo(valor);
        nuevo.siguiente = cima;
        cima = nuevo;
        longitud = longitud + 1;
    }

    String desapilar() {
        assert !estaVacia();
        String valor = cima.valor;
        cima = cima.siguiente;
        longitud = longitud - 1;
        return valor;
    }

    boolean estaVacia() {
        return cima == null;
    }

    int longitud() {
        return longitud;
    }
}
