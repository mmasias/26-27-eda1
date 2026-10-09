package arrayConLista;

public class ArrayConLista {
    private final Nodo cabeza;
    private final int longitud;

    public ArrayConLista(int longitud) {
        assert longitud >= 0;
        Nodo primerNodo = null;
        for (int nodosCreados = 0; nodosCreados < longitud; nodosCreados++) {
            Nodo nuevoNodo = new Nodo(0);
            nuevoNodo.siguiente = primerNodo;
            primerNodo = nuevoNodo;
        }
        this.cabeza = primerNodo;
        this.longitud = longitud;
    }

    public int longitud() {
        return longitud;
    }

    public int obtener(int posicion) {
        return nodoEnLaPosicion(posicion).dato;
    }

    public void asignar(int posicion, int valor) {
        nodoEnLaPosicion(posicion).dato = valor;
    }

    private Nodo nodoEnLaPosicion(int posicion) {
        assert esPosicionValida(posicion);
        Nodo nodoActual = cabeza;
        for (int nodosRecorridos = 0; nodosRecorridos < posicion; nodosRecorridos++) {
            nodoActual = nodoActual.siguiente;
        }
        return nodoActual;
    }

    private boolean esPosicionValida(int posicion) {
        return posicion >= 0 && posicion < longitud;
    }
}
