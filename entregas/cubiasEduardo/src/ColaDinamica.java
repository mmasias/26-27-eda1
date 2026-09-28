public class ColaDinamica extends Cola {

    private int capacidadInicial;
    private int vecesRedimensionada;

    public ColaDinamica() {
        this(15);
    }

    public ColaDinamica(int capacidadInicial) {
        super(capacidadInicial);
        this.capacidadInicial = capacidadInicial;
        this.vecesRedimensionada = 0;
    }

    @Override
    public boolean encolar(Cliente nuevoCliente) {
        if (nuevoCliente == null) {
            return false;
        }

        if (super.estaLlena()) {
            redimensionar();
        }

        if (nuevoCliente.tienePrioridad()) {
            insertarPrioritario(nuevoCliente);
        } else {
            insertarAlFinal(nuevoCliente);
        }

        tamaño++;
        return true;
    }

    private void redimensionar() {
        Cliente[] nuevoArreglo = new Cliente[clientes.length * 2];
        for (int i = 0; i < clientes.length; i++) {
            nuevoArreglo[i] = clientes[i];
        }
        clientes = nuevoArreglo;
        vecesRedimensionada++;
        console.writeln(">>> Aforo alcanzado: arreglo duplicado a " + clientes.length + " posiciones <<<");
    }

    @Override
    public boolean estaLlena() {
        return false;
    }

    public int obtenerCapacidadInicial() {
        return capacidadInicial;
    }

    public int obtenerCapacidadActual() {
        return clientes.length;
    }

    public int obtenerVecesRedimensionada() {
        return vecesRedimensionada;
    }
}
