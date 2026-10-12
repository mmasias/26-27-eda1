public class ListaConCursores {
    private static final int NULO = -1;

    private int[] datos;
    private int[] siguientes;
    private int cabeza;
    private int primerLibre;
    private int cantidad;

    public ListaConCursores(int capacidad) {
        if (capacidad < 0) {
            System.out.println("ERROR: capacidad negativa (" + capacidad + "), se crea con capacidad 0");
            capacidad = 0;
        }
        datos = new int[capacidad];
        siguientes = new int[capacidad];
        cabeza = NULO;
        cantidad = 0;
        for (int indice = 0; indice < capacidad - 1; indice++) {
            siguientes[indice] = indice + 1;
        }
        if (capacidad > 0) {
            siguientes[capacidad - 1] = NULO;
            primerLibre = 0;
        } else {
            primerLibre = NULO;
        }
    }

    public int longitud() {
        return cantidad;
    }

    public void insertarEnPosicion(int posicion, int dato) {
        if (primerLibre == NULO) {
            System.out.println("ERROR: lista llena, no se puede insertar " + dato);
            return;
        }
        int nuevaCasilla = primerLibre;
        primerLibre = siguientes[primerLibre];
        datos[nuevaCasilla] = dato;

        if (cabeza == NULO || posicion <= 0) {
            siguientes[nuevaCasilla] = cabeza;
            cabeza = nuevaCasilla;
            cantidad++;
            return;
        }

        int actual = cabeza;
        int pasos = 1;
        while (siguientes[actual] != NULO && pasos < posicion) {
            actual = siguientes[actual];
            pasos++;
        }
        siguientes[nuevaCasilla] = siguientes[actual];
        siguientes[actual] = nuevaCasilla;
        cantidad++;
    }

    public void eliminarPorValor(int valor) {
        while (cabeza != NULO && datos[cabeza] == valor) {
            int casillaLiberada = cabeza;
            cabeza = siguientes[cabeza];
            liberar(casillaLiberada);
        }
        if (cabeza == NULO) {
            return;
        }
        int actual = cabeza;
        while (siguientes[actual] != NULO) {
            int candidato = siguientes[actual];
            if (datos[candidato] == valor) {
                siguientes[actual] = siguientes[candidato];
                liberar(candidato);
            } else {
                actual = candidato;
            }
        }
    }

    public void imprimirLista() {
        int actual = cabeza;
        while (actual != NULO) {
            System.out.print(datos[actual] + " -> ");
            actual = siguientes[actual];
        }
        System.out.println("null");
    }

    public void imprimirInterior() {
        System.out.println("  casilla    : " + filaDeIndices());
        System.out.println("  datos      : " + filaDe(datos));
        System.out.println("  siguientes : " + filaDe(siguientes));
        System.out.println("  cabeza=" + cabeza + "  primerLibre=" + primerLibre + "  cantidad=" + cantidad);
    }

    private void liberar(int casilla) {
        siguientes[casilla] = primerLibre;
        primerLibre = casilla;
        cantidad--;
    }

    private String filaDeIndices() {
        String fila = "";
        for (int indice = 0; indice < datos.length; indice++) {
            fila += String.format("%4d", indice);
        }
        return fila;
    }

    private String filaDe(int[] valores) {
        String fila = "";
        for (int indice = 0; indice < valores.length; indice++) {
            fila += String.format("%4d", valores[indice]);
        }
        return fila;
    }
}
