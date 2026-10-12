package entregas.listaConArray;

public class Lista {
    
    private String[] elementos;
    private int contador;

    public Lista(int capacidad) {
        this.elementos = new String[capacidad];
        contador = 0;
    }

    public int tamaño() {
        return contador;
    }

    public boolean estaVacia() {
        return contador == 0;
    }

    public void añadir(String elemento) {
        if (contador < elementos.length) {
            elementos[contador] = elemento;
            contador++;
        }
    }

    public void insertar(int posicion, String elemento) {
        if (posicion >= 0 && posicion <= contador && contador < elementos.length) {
            for (int i = contador; i > posicion; i--) {
                elementos[i] = elementos[i - 1];
            }
            elementos[posicion] = elemento;
            contador++;
        }
    }

    public String obtener(int posicion) {
        String elementoEncontrado = null;
        if (posicion >= 0 && posicion < contador) {
            elementoEncontrado = elementos[posicion];
        }
        return elementoEncontrado;
    }

    public String eliminar(int posicion) {
        String elementoEliminado = null;
        if (posicion >= 0 && posicion < contador) {
            elementoEliminado = elementos[posicion];
            for (int i = posicion; i < contador - 1; i++) {
                elementos[i] = elementos[i + 1];
            }
            elementos[contador - 1] = null;
            contador--;
        }
        return elementoEliminado;
    }

    public void mostrar() {
        for (int i = 0; i < contador; i++) {
            System.out.println(elementos[i]);
        }
    }
}