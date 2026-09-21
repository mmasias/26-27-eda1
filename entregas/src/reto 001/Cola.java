public class Cola {
    private Cliente[] clientes;
    private int tamano;

    public Cola() {
        clientes = new Cliente[1000];
        tamano = 0;
    }

    public void anadirCliente(Cliente cliente) {
        clientes[tamano] = cliente;
        tamano = tamano + 1;
    }

    public Cliente quitarCliente() {
        Cliente cliente = clientes[0];

        for (int i = 0; i < tamano - 1; i++) {
            clientes[i] = clientes[i + 1];
        }

        clientes[tamano - 1] = null;
        tamano = tamano - 1;

        return cliente;
    }

    public boolean hayClientes() {
        return tamano > 0;
    }

    public int obtenerCantidadClientes() {
        return tamano;
    }
}
