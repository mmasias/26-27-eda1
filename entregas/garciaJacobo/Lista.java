package entregas.garciaJacobo;

public class Lista {

    private int[] lista;
    private int longitud;
    private final int CAPACIDAD_INICIAL = 10;

    public Lista() {
        this.lista = new int[CAPACIDAD_INICIAL];
        this.longitud = 0;
    }

    public void insertarInicio(int dato) {

        if (estaLleno()) {
            ampliarArray();
        }

        for (int i = longitud - 1; i >= 0; i--) {
            lista[i + 1] = lista[i];
        }

        lista[0] = dato;
        longitud++;
    }

    public void insertarFinal(int dato) {

        if (estaLleno()) {
            ampliarArray();
        }

        lista[longitud] = dato;
        longitud++;
    }

    public void insertar(int posicion, int dato) {
        assert posicion >= 0 && posicion <= longitud;
        if (estaLleno()) {
            ampliarArray();
        }
        for (int i = longitud - 1; i >= posicion; i--) {
            lista[i + 1] = lista[i];
        }
        lista[posicion] = dato;
        longitud++;

    }

    public void eliminarInicio() {
        assert longitud > 0;

        for (int i = 0; i < longitud - 1; i++) {
            lista[i] = lista[i + 1];
        }
        longitud--;
    }

    public void eliminarFinal() {
        assert longitud > 0;

        longitud--;
    }

    public void eliminar(int posicion) {
        assert posicion >= 0 && posicion < longitud;
        for (int i = posicion + 1; i < longitud; i++) {
            lista[i - 1] = lista[i];
        }
        longitud--;
    }

    public void modificar(int posicion, int dato) {
        assert posicion >= 0 && posicion < longitud;
        lista[posicion] = dato;
    }

    public int obtener(int posicion) {
        assert posicion >= 0 && posicion < longitud ;
        return lista[posicion];
    }

    public int longitud() {
        return longitud;
    }

    public boolean estaVacia() {
        return longitud == 0;
    }

    private void ampliarArray() {

        int[] auxiliar = new int[lista.length * 2];

        for (int i = 0; i < longitud; i++) {
            auxiliar[i] = lista[i];
        }
        lista = auxiliar;
    }

    private boolean estaLleno() {
        return lista.length == longitud;
    }

    public int capacidad() {
      return lista.length;
    }
}
