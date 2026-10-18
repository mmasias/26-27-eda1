package entregas.GomezAlejandro.reto_003;

public class Lista {

    private static final int CAPACIDAD_INICIAL = 10;

    private int[] lista;
    private int longitud;

    public Lista() {
        lista = new int[CAPACIDAD_INICIAL];
        longitud = 0;
    }

    public void insertarInicio(int dato) {
        insertar(0, dato);
    }

    public void insertarFinal(int dato) {
        insertar(longitud, dato);
    }

    public void insertar(int posicion, int dato) {
        assert posicion >= 0 && posicion <= longitud;

        if (longitud == lista.length) {
            ampliarArray();
        }

        for (int i = longitud - 1; i >= posicion; i--) {
            lista[i + 1] = lista[i];
        }

        lista[posicion] = dato;
        longitud++;
    }

    public void eliminarInicio() {
        eliminar(0);
    }

    public void eliminarFinal() {
        eliminar(longitud - 1);
    }

    public void eliminar(int posicion) {
        assert posicion >= 0 && posicion < longitud;

        for (int i = posicion; i < longitud - 1; i++) {
            lista[i] = lista[i + 1];
        }

        longitud--;
    }

    public void modificar(int posicion, int dato) {
        assert posicion >= 0 && posicion < longitud;

        lista[posicion] = dato;
    }

    public int obtener(int posicion) {
        assert posicion >= 0 && posicion < longitud;

        return lista[posicion];
    }

    public int longitud() {
        return longitud;
    }

    public boolean estaVacia() {
        return longitud == 0;
    }

    public int capacidad() {
        return lista.length;
    }

    private void ampliarArray() {
        int[] auxiliar = new int[lista.length * 2];

        for (int i = 0; i < longitud; i++) {
            auxiliar[i] = lista[i];
        }

        lista = auxiliar;
    }
}