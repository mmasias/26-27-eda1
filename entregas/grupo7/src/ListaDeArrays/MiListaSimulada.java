class MiListaSimulada {

    int capacidad;
    int[] lista;
    int numeroElementos;

    public MiListaSimulada() {
        capacidad = 10;
        lista = new int[capacidad];
    }

    public int getCabeza() {
        return lista[0];
    }

    public int getCola() {
        return lista[numeroElementos - 1];

    }

    public int getEnMedio() {
        int numeroMedio = capacidad / 2;

        return lista[numeroMedio];
    }

    public void añadirElemento(int elemento) {
        if (arrayEstaLleno()) {
            expandirArray();
        }
        lista[numeroElementos] = elemento;
        numeroElementos++;
    }

    public void apilarElemento(int elemento) {
        if (arrayEstaLleno()) {
            expandirArray();
        }

        for (int i = numeroElementos - 1; i >= 0; i--) {
            lista[i + 1] = lista[i];
        }
        lista[0] = elemento;
        numeroElementos++;
    }

    public void deColar() {
        lista[numeroElementos - 1] = 0;
        numeroElementos--;
    }

    public void dePilar() {
        for (int i = 1; i < numeroElementos; i++) {
            lista[i - 1] = lista[i];
        }
        numeroElementos--;
    }

    public void insertarEn(int posicion, int valor) {
        if (arrayEstaLleno()) {
            expandirArray();
        }

        for (int i = numeroElementos - 1; i >= posicion; i--) {
            lista[i + 1] = lista[i];
        }

        lista[posicion] = valor;
        numeroElementos++;
    }

    public void eliminarEn(int posicion) {
        for (int i = posicion; i < numeroElementos - 1; i++) {
            lista[i] = lista[i + 1];
        }

        numeroElementos--;
    }

    private void expandirArray() {
        capacidad = (int) (capacidad * 1.5);
        int[] nuevaLista = new int[capacidad];
        for (int i = 0; i < numeroElementos; i++) {
            nuevaLista[i] = lista[i];
        }

        lista = nuevaLista;

    }

    public boolean arrayEstaLleno() {
        return numeroElementos == capacidad;
    }

}