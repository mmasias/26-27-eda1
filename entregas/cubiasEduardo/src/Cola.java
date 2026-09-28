public class Cola {

    protected Cliente[] clientes;
    protected int tamaño;
    protected Console console;

    public Cola() {
        this(15);
    }

    public Cola(int capacidad) {
        clientes = new Cliente[capacidad];
        tamaño = 0;
        console = new Console();
    }

    public boolean encolar(Cliente nuevoCliente) {
        if (estaLlena() || nuevoCliente == null) {
            return false;
        }

        if (nuevoCliente.tienePrioridad()) {
            insertarPrioritario(nuevoCliente);
        } else {
            insertarAlFinal(nuevoCliente);
        }

        tamaño++;
        return true;
    }

    protected void insertarAlFinal(Cliente cliente) {
        clientes[tamaño] = cliente;
    }

    protected void insertarPrioritario(Cliente clientePrioritario) {
        int indiceInsercion = 0;
        for (int i = 0; i < tamaño; i++) {
            if (clientes[i].tienePrioridad()) {
                indiceInsercion = i + 1;
            }
        }

        for (int i = tamaño; i > indiceInsercion; i--) {
            clientes[i] = clientes[i - 1];
        }

        clientes[indiceInsercion] = clientePrioritario;
    }

    public Cliente desencolar() {
        if (estaVacia()) {
            return null;
        }

        Cliente atendido = clientes[0];
        for (int i = 0; i < tamaño - 1; i++) {
            clientes[i] = clientes[i + 1];
        }
        clientes[tamaño - 1] = null;
        tamaño--;
        return atendido;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

    public boolean estaLlena() {
        return tamaño >= clientes.length;
    }

    public int obtenerCantidadPersonasEnCola() {
        return tamaño;
    }

    public int obtenerCapacidadMaxima() {
        return clientes.length;
    }

    public Cliente primero() {
        if (estaVacia()) {
            return null;
        }
        return clientes[0];
    }

    public void mostrar() {
        for (int i = 0; i < tamaño; i++) {
            clientes[i].mostrar();
        }
        console.writeln();
    }
}
