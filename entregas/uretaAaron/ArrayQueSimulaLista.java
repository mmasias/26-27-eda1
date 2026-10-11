package listas.simulacion;

public class ArrayQueSimulaLista {

    private int[] datos;
    private int cantidad;

    public ArrayQueSimulaLista(int capacidadInicial) {
        this.datos = new int[capacidadInicial];
        this.cantidad = 0;
    }

    public int tamano() {
        return cantidad;
    }

    public void insertarAlFinal(int valor) {
        insertarEn(cantidad, valor);
    }

    public void insertarEn(int indice, int valor) {
        if (indice < 0 || indice > cantidad) {
            return;
        }

        if (cantidad == datos.length) {
            redimensionar();
        }

        int i = cantidad;
        while (i > indice) {
            datos[i] = datos[i - 1];
            i--;
        }

        datos[indice] = valor;
        cantidad++;
    }

    public void eliminarEn(int indice) {
        if (indice < 0 || indice >= cantidad) {
            return;
        }

        int i = indice;
        while (i < cantidad - 1) {
            datos[i] = datos[i + 1];
            i++;
        }

        cantidad--;
        datos[cantidad] = 0;
    }

    private void redimensionar() {
        int[] nuevo = new int[datos.length * 2];
        int i = 0;
        while (i < datos.length) {
            nuevo[i] = datos[i];
            i++;
        }
        datos = nuevo;
    }

    public void imprimir() {
        System.out.print("[ ");
        int i = 0;
        while (i < cantidad) {
            System.out.print(datos[i] + " ");
            i++;
        }
        System.out.println("]");
    }
}