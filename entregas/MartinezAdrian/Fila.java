public class Fila {

    private Cliente primero;
    private Cliente ultimo;
    private int tamaño;
    private Console console;

    public Fila() {
        primero = null;
        ultimo = null;
        tamaño = 0;
        console = new Console();
    }


    public void añadirCliente(Cliente cliente) {
        cliente.establecerSiguiente(null);
        cliente.establecerAnterior(ultimo);
        if (ultimo == null) {
            primero = cliente;
        } else {
            ultimo.establecerSiguiente(cliente);
        }
        ultimo = cliente;
        tamaño = tamaño + 1;
    }

    public boolean hayClientes() {
        return tamaño > 0;
    }

    public Cliente quitarCliente() {
        Cliente cliente = primero;
        primero = cliente.obtenerSiguiente();
        if (primero == null) {
            ultimo = null;
        } else {
            primero.establecerAnterior(null);
        }
        cliente.establecerAnterior(null);
        cliente.establecerSiguiente(null);
        tamaño = tamaño - 1;
        return cliente;
    }

    public void mostrar() {
        Cliente clienteActual = primero;
        while (clienteActual != null) {
            clienteActual.mostrar();
            clienteActual = clienteActual.obtenerSiguiente();
        }
        console.writeln();
    }


    public int obtenerCantidadPersonasEnFila() {
        return tamaño;
    }


}