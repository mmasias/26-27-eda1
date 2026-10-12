public class ListaConArray {

    private int[] elementos;
    private int tamaño;

    public ListaConArray(int capacidad) {
        elementos = new int[capacidad];
        tamaño = 0;
    }

    public void añadir(int elemento) {
        if (tamaño < elementos.length) {
            elementos[tamaño] = elemento;
            tamaño++;
        } else {
            System.out.println("La lista está llena.");
        }
    }

    public int obtener(int posicion) {
        if (posicion >= 0 && posicion < tamaño) {
            return elementos[posicion];
        } else {
            System.out.println("Posición no válida.");
            return -1;
        }
    }

    public void modificar(int posicion, int elemento) {
        if (posicion >= 0 && posicion < tamaño) {
            elementos[posicion] = elemento;
        } else {
            System.out.println("Posición no válida.");
        }
    }

    public void eliminar(int posicion) {
        if (posicion >= 0 && posicion < tamaño) {
            for (int i = posicion; i < tamaño - 1; i++) {
                elementos[i] = elementos[i + 1];
            }

            tamaño--;
        } else {
            System.out.println("Posición no válida.");
        }
    }

    public int tamaño() {
        return tamaño;
    }

    public void mostrar() {
        System.out.print("[");

        for (int i = 0; i < tamaño; i++) {
            System.out.print(elementos[i]);

            if (i < tamaño - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");
    }
}