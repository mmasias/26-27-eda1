public class Cola {
    private Cliente[] clientes;
    private final int CAPACIDAD_MAXIMA = 100;
    private int tamaño;
    private Console console;

    public Cola() {
        clientes = new Cliente[CAPACIDAD_MAXIMA];
        tamaño = 0;
        console = new Console();
    }

    public void añadirCliente(Cliente cliente) {
        if (tamaño < CAPACIDAD_MAXIMA) {
            clientes[tamaño] = cliente;
            tamaño = tamaño + 1;
        }
    }

    public void añadirClientePreferente(Cliente cliente) {
        int ultimoPreferente = -1;
        for (int i = 0; i < tamaño; i++) {
            if (clientes[i].esPreferente()) {
                ultimoPreferente = i;
            }
        }
        int posicionInsercion;
        if (ultimoPreferente == -1) {
            posicionInsercion = 0;
        } else {
            posicionInsercion = ultimoPreferente + 1;
        }
        for (int i = tamaño; i > posicionInsercion; i--) {
            clientes[i] = clientes[i - 1];
        }
        clientes[posicionInsercion] = cliente;
        tamaño = tamaño + 1;
    }

    public void añadirClienteColado(Cliente cliente) {
        int posConocido = -1;
        for (int i = 0; i < tamaño; i++) {
            if (clientes[i].getId() == cliente.getIdConocido()) {
                posConocido = i;
                break;
            }
        }
        int posicionInsercion;
        if (posConocido == -1) {
            posicionInsercion = tamaño;
        } else {
            posicionInsercion = posConocido + 1;
        }
        for (int i = tamaño; i > posicionInsercion; i--) {
            clientes[i] = clientes[i - 1];
        }
        clientes[posicionInsercion] = cliente;
        tamaño = tamaño + 1;
    }

    public void eliminarAburridos() {
        if (Math.random() < 0.3) {
            for (int i = tamaño - 1; i >= 0; i--) {
                if (clientes[i].estaAburrido()) {
                    console.writeln("Cliente " + clientes[i].getId() + " se aburrio y se fue.");
                    for (int j = i; j < tamaño - 1; j++) {
                        clientes[j] = clientes[j + 1];
                    }
                    clientes[tamaño - 1] = null;
                    tamaño = tamaño - 1;
                }
            }
        }
    }

    public void avanzarMinuto() {
        for (int i = 0; i < tamaño; i++) {
            clientes[i].aumentarTiempo();
        }
    }

    public Cliente getClienteAleatorio() {
        if (tamaño == 0) {
            return null;
        }
        int indice = (int) (Math.random() * tamaño);
        return clientes[indice];
    }

    public boolean hayClientes() {
        return tamaño > 0;
    }

    public Cliente quitarCliente() {
        if (tamaño == 0) {
            return null;
        }
        Cliente cliente = clientes[0];
        for (int i = 0; i < tamaño - 1; i++) {
            clientes[i] = clientes[i + 1];
        }
        clientes[tamaño - 1] = null;
        tamaño = tamaño - 1;
        return cliente;
    }

    public int obtenerCantidadPersonasEnCola() {
        return tamaño;
    }

    public Cliente primero() {
        if (tamaño == 0) {
            return null;
        }
        return clientes[0];
    }

    public void mostrar() {
        for (int i = 0; i < tamaño; i++) {
            console.writeln(clientes[i].toString());
        }
        console.writeln();
    }
}