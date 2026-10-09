package listaConArray;

public class ListaConArray {
    private static final int CAPACIDAD_INICIAL = 4;

    private int[] elementos;
    private int longitud;

    public ListaConArray() {
        this.elementos = new int[CAPACIDAD_INICIAL];
        this.longitud = 0;
    }

    public int longitud() {
        return longitud;
    }

    public boolean estaVacia() {
        return longitud == 0;
    }

    public int obtener(int posicion) {
        assert esPosicionOcupada(posicion);
        return elementos[posicion];
    }

    public void insertarAlFinal(int valor) {
        insertar(longitud, valor);
    }

    public void insertar(int posicion, int valor) {
        assert esPosicionDondeSePuedeInsertar(posicion);
        if (estaLleno()) {
            duplicarCapacidad();
        }
        abrirHuecoEn(posicion);
        elementos[posicion] = valor;
        longitud++;
    }

    public int eliminar(int posicion) {
        assert esPosicionOcupada(posicion);
        int valorEliminado = elementos[posicion];
        cerrarHuecoEn(posicion);
        longitud--;
        return valorEliminado;
    }

    private boolean esPosicionOcupada(int posicion) {
        return posicion >= 0 && posicion < longitud;
    }

    private boolean esPosicionDondeSePuedeInsertar(int posicion) {
        return posicion >= 0 && posicion <= longitud;
    }

    private boolean estaLleno() {
        return longitud == elementos.length;
    }

    private void duplicarCapacidad() {
        int[] elementosAmpliados = new int[elementos.length * 2];
        for (int posicion = 0; posicion < longitud; posicion++) {
            elementosAmpliados[posicion] = elementos[posicion];
        }
        elementos = elementosAmpliados;
    }

    private void abrirHuecoEn(int posicionDelHueco) {
        for (int posicion = longitud; posicion > posicionDelHueco; posicion--) {
            elementos[posicion] = elementos[posicion - 1];
        }
    }

    private void cerrarHuecoEn(int posicionDelHueco) {
        for (int posicion = posicionDelHueco; posicion < longitud - 1; posicion++) {
            elementos[posicion] = elementos[posicion + 1];
        }
    }
}
