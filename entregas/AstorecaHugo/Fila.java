
public class Fila {

    private Nodo primero;
    private Nodo ultimo;
    private int minutosSinClientes;
    private int tamaño;
    private Console console;

    public Fila() {
        primero = null;
        ultimo = null;
        minutosSinClientes = 0;
        tamaño = 0;
        console = new Console();
    }

    public void registrarEstado() {
        if (tamaño == 0) {
            minutosSinClientes = minutosSinClientes + 1;
        }
    }

    public void añadirCliente(Cliente cliente) {
        Nodo nuevo = new Nodo(cliente);

        if (tamaño == 0) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        }

        tamaño = tamaño + 1;
    }

    public boolean hayClientes() {
        return tamaño > 0;
    }

    public Cliente quitarCliente() {
        Cliente cliente = primero.cliente;

        primero = primero.siguiente;
        tamaño = tamaño - 1;

        if (tamaño == 0) {
            ultimo = null;
        }

        return cliente;
    }

    public void mostrar() {
        Nodo actual = primero;

        while (actual != null) {
            actual.cliente.mostrar();
            actual = actual.siguiente;
        }

        console.writeln();
    }

    public int obtenerMinutosSinClientes() {
        return minutosSinClientes;
    }

    public int obtenerCantidadPersonasEnCola() {
        return tamaño;
    }

    public Cliente primero() {
        return primero.cliente;
    }

    private class Nodo {

        private Cliente cliente;
        private Nodo siguiente;

        public Nodo(Cliente cliente) {
            this.cliente = cliente;
            siguiente = null;
        }
    }
}
