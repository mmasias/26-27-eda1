public class Lista {
    private int[] elementos;
    private int tamaño;

    public Lista() {
        elementos = new int[10];
        tamaño = 0;
    }

    public void insertarAlPrincipio(int elemento) {
        if (tamaño == elementos.length) {
            int[] nuevoArreglo = new int[elementos.length * 2];
            for (int i = 0; i < tamaño; i++) {
                nuevoArreglo[i] = elementos[i];
            }
            elementos = nuevoArreglo;
        }

        for (int i = tamaño; i > 0; i--) {
            elementos[i] = elementos[i - 1];
        }
        elementos[0] = elemento;
        tamaño++;
    }

    public void eliminarAlPrincipio() {
        if (tamaño == 0) {
            return;
        }

        for (int i = 0; i < tamaño - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        tamaño--;
    }

    public int obtenerElemento() {
        return obtenerElemento(0);
    }

    public int obtenerElemento(int posicion) {
        assert posicion >= 0 && posicion < tamaño : "Posición fuera de rango";
        return elementos[posicion];
    }

    public void imprimirLista() {
        for (int i = 0; i < tamaño; i++) {
            System.out.print(elementos[i] + " -> ");
        }
        System.out.println("null");
    }
}