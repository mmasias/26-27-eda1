public class ListaConArray {
    private static final int CAPACIDAD_INICIAL = 4;

    private int[] elementos;
    private int cantidad;
    private int desplazamientosUltimaOperacion;
    private int ampliacionesRealizadas;

    public ListaConArray() {
        this.elementos = new int[CAPACIDAD_INICIAL];
        this.cantidad = 0;
        this.desplazamientosUltimaOperacion = 0;
        this.ampliacionesRealizadas = 0;
    }

    public int longitud() {
        return cantidad;
    }

    public boolean estaVacia() {
        return cantidad == 0;
    }

    public void insertarAlFinal(int dato) {
        insertarEnPosicion(cantidad, dato);
    }

    public void insertarAlPrincipio(int dato) {
        insertarEnPosicion(0, dato);
    }

    public void insertarEnPosicion(int posicion, int dato) {
        if (posicion < 0 || posicion > cantidad) {
            System.out.println("ERROR: no se puede insertar en la posición " + posicion + ", rango válido [0, " + cantidad + "]");
            return;
        }
        ampliarSiEstaLleno();
        desplazamientosUltimaOperacion = 0;
        for (int indice = cantidad; indice > posicion; indice--) {
            elementos[indice] = elementos[indice - 1];
            desplazamientosUltimaOperacion++;
        }
        elementos[posicion] = dato;
        cantidad++;
    }

    public void eliminarEnPosicion(int posicion) {
        if (posicion < 0 || posicion >= cantidad) {
            System.out.println("ERROR: no se puede eliminar la posición " + posicion + ", la lista tiene " + cantidad + " elementos");
            return;
        }
        desplazamientosUltimaOperacion = 0;
        for (int indice = posicion; indice < cantidad - 1; indice++) {
            elementos[indice] = elementos[indice + 1];
            desplazamientosUltimaOperacion++;
        }
        cantidad--;
    }

    public void eliminarPorValor(int valor) {
        int posicionEscritura = 0;
        for (int posicionLectura = 0; posicionLectura < cantidad; posicionLectura++) {
            if (elementos[posicionLectura] != valor) {
                elementos[posicionEscritura] = elementos[posicionLectura];
                posicionEscritura++;
            }
        }
        cantidad = posicionEscritura;
    }

    public int obtener(int posicion) {
        if (posicion < 0 || posicion >= cantidad) {
            System.out.println("ERROR: posición " + posicion + " no válida, la lista tiene " + cantidad + " elementos");
            return -1;
        }
        return elementos[posicion];
    }

    public int desplazamientosDeLaUltimaOperacion() {
        return desplazamientosUltimaOperacion;
    }

    public int ampliaciones() {
        return ampliacionesRealizadas;
    }

    public int capacidadInterna() {
        return elementos.length;
    }

    public void imprimirLista() {
        for (int indice = 0; indice < cantidad; indice++) {
            System.out.print(elementos[indice] + " -> ");
        }
        System.out.println("null");
    }

    public void imprimirInterior() {
        System.out.print("[");
        for (int indice = 0; indice < elementos.length; indice++) {
            if (indice < cantidad) {
                System.out.print(elementos[indice]);
            } else {
                System.out.print("_");
            }
            if (indice < elementos.length - 1) {
                System.out.print("|");
            }
        }
        System.out.println("]  cantidad=" + cantidad + "  capacidad=" + elementos.length);
    }

    private void ampliarSiEstaLleno() {
        if (cantidad < elementos.length) {
            return;
        }
        int[] elementosAmpliados = new int[elementos.length * 2];
        for (int indice = 0; indice < cantidad; indice++) {
            elementosAmpliados[indice] = elementos[indice];
        }
        elementos = elementosAmpliados;
        ampliacionesRealizadas++;
    }
}
