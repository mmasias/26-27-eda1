class ArrayEnlazado {
    private Nodo cabeza;
    private int longitud;
    private Console console;

    public ArrayEnlazado(int longitud) {
        assert longitud > 0;

        cabeza = null;
        this.longitud = longitud;
        console = new Console();
        for (int i = 0; i < longitud; i++) {
            this.agregarAlInicio(0);
        }
    }

    public int longitud() {
        return longitud;
    }

    public int obtener(int indice) {
        assert this.esIndiceValido(indice);

        return this.nodoEn(indice).dato;
    }

    public void asignar(int indice, int dato) {
        assert this.esIndiceValido(indice);

        this.nodoEn(indice).dato = dato;
    }

    public void imprimir() {
        console.write("[ ");
        Nodo actual = cabeza;
        while (actual != null) {
            console.write(actual.dato + " ");
            actual = actual.siguiente;
        }
        console.writeln("]");
    }

    private void agregarAlInicio(int dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
    }

    private Nodo nodoEn(int indice) {
        Nodo actual = cabeza;
        int pasos = 0;
        while (pasos < indice) {
            actual = actual.siguiente;
            pasos++;
        }
        return actual;
    }

    private boolean esIndiceValido(int indice) {
        return indice >= 0 && indice < longitud;
    }
}