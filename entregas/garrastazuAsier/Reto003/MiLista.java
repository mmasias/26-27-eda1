public class MiLista {
    private int[] elementos;
    private int tamaño;

    public MiLista() {
        elementos = new int[5];
        tamaño = 0;
    }

    private void ampliar() {
        int[] nuevo = new int[elementos.length * 2];

        for (int i = 0; i < tamaño; i++) {
            nuevo[i] = elementos[i];
        }

        elementos = nuevo;
    }

    public void insertar(int posicion, int valor) {
        if (posicion < 0 || posicion > tamaño) {
            throw new IndexOutOfBoundsException();
        }

        if (tamaño == elementos.length) {
            ampliar();
        }

        for (int i = tamaño; i > posicion; i--) {
            elementos[i] = elementos[i - 1];
        }

        elementos[posicion] = valor;
        tamaño++;
    }

    public void eliminar(int posicion) {
        if (posicion < 0 || posicion >= tamaño) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = posicion; i < tamaño - 1; i++) {
            elementos[i] = elementos[i + 1];
        }

        tamaño--;
    }

    public int obtener(int posicion) {
        if (posicion < 0 || posicion >= tamaño) {
            throw new IndexOutOfBoundsException();
        }

        return elementos[posicion];
    }

    public int tamaño() {
        return tamaño;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

    public boolean contiene(int valor) {
        for (int i = 0; i < tamaño; i++) {
            if (elementos[i] == valor) {
                return true;
            }
        }

        return false;
    }

    public void mostrar() {
        for (int i = 0; i < tamaño; i++) {
            System.out.print(elementos[i] + " ");
        }

        System.out.println();
    }
}