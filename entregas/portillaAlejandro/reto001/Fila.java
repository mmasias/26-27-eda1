public class Fila {
    private Cliente primero;
    private int numeroClientes;

    private final double PROBABILIDAD_ABURRIRSE = 0.3;

    private Console console;

    public Fila() {
        primero = null;
        numeroClientes = 0;
        console = new Console();
    }

    public Cliente primero() {
        return primero;
    }

    public boolean hayGente() {
        return numeroClientes != 0;
    }

    public Cliente sacar() {
        Cliente clienteSacado = primero;

        if (primero != null) {
            primero = primero.obtenerSiguiente();
            clienteSacado.establecerSiguiente(null);
            numeroClientes--;
        }

        return clienteSacado;
    }

    public void añadirCliente(Cliente cliente) {
        if (primero == null) {
            primero = cliente;
        } else {
            Cliente actual = primero;

            while (actual.obtenerSiguiente() != null) {
                actual = actual.obtenerSiguiente();
            }

            actual.establecerSiguiente(cliente);
        }

        numeroClientes++;
    }

    public int obtenerNumero() {
        return numeroClientes;
    }

    public void mostrar() {
        console.writeln("FILA:");

        if (hayGente()) {
            Cliente actual = primero;
            int posicion = 1;

            while (actual != null) {
                console.writeln("  Cliente " + posicion);
                actual = actual.obtenerSiguiente();
                posicion++;
            }
        } else {
            console.writeln("  Vacia");
        }
    }

    public void comprobarAburrimiento(int minutoActual) {
        Cliente actual = primero;
        Cliente anterior = null;

        while (actual != null) {
            if (actual.minutosEnCola(minutoActual) > 8) {
                if (Math.random() < PROBABILIDAD_ABURRIRSE) {

                    if (anterior == null) {
                        primero = actual.obtenerSiguiente();
                    } else {
                        anterior.establecerSiguiente(actual.obtenerSiguiente());
                    }

                    actual = actual.obtenerSiguiente();
                    numeroClientes--;

                } else {
                    anterior = actual;
                    actual = actual.obtenerSiguiente();
                }
            } else {
                anterior = actual;
                actual = actual.obtenerSiguiente();
            }
        }
    }
}