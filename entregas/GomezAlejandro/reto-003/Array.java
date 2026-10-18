package entregas.GomezAlejandro.reto_003;

public class Array {

    private int tamaño;
    private Nodo cabeza;

    public Array(int tamaño) {
        assert tamaño >= 0;

        Nodo auxiliar = new Nodo(-1);
        Nodo actual = auxiliar;

        for (int i = 0; i < tamaño; i++) {
            Nodo nuevo = new Nodo(0);
            actual.siguiente = nuevo;
            actual = nuevo;
        }

        this.tamaño = tamaño;
        this.cabeza = auxiliar.siguiente;
    }

    private Nodo obtenerPosicion(int posicion) {
        assert posicion >= 0 && posicion < tamaño;

        Nodo actual = cabeza;

        for (int i = 0; i < posicion; i++) {
            actual = actual.siguiente;
        }

        return actual;
    }

    public int obtener(int posicion) {
        return obtenerPosicion(posicion).dato;
    }

    public int longitud() {
        return tamaño;
    }

    public void asignar(int posicion, int valor) {
        obtenerPosicion(posicion).dato = valor;
    }

    public void mostrar() {
        System.out.print("[");

        for (int i = 0; i < tamaño; i++) {
            if (i > 0) {
                System.out.print(", ");
            }

            System.out.print(obtener(i));
        }

        System.out.println("]");
    }
}