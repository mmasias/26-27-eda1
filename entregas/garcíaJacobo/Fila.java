public class Fila {

    private Cliente primero;
    private Cliente ultimo;
    private Console console;

    public Fila() {
        console = new Console();

    }

    public Cliente primero() {
        return primero;
    }

    public boolean hayGente() {
        return primero != null;
    }

    public Cliente sacar() {
        Cliente cliente = primero;
        primero = cliente.obtenerProximo();
        if (primero == null) {
            ultimo = null;
        }
        return cliente;
    }

    public void añadirCliente(Cliente cliente) {
        if (!this.hayGente()) {
            primero = cliente;
            ultimo = cliente;
        } else {

            ultimo.proximoCliente(cliente);
            ultimo = cliente;
        }
    }

    public void mostrar() {
        console.writeln("FILA:");
        Cliente clienteActual = primero;
        int contador = 0;
        while (clienteActual != null) {
            contador++;
            console.writeln("Cliente " + contador);
            clienteActual = clienteActual.obtenerProximo();

        }
    }

    public int obtenerNumero() {
        int contador = 0;
        Cliente clienteActual = primero;

        while (clienteActual != null) {
            clienteActual = clienteActual.obtenerProximo();
            contador++;
        }
        return contador;
    }

}
