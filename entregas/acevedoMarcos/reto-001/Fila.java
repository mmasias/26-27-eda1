public class Fila {

    private Cliente primero;
    private Cliente ultimo;
    private int numeroClientes;

    private final int MAXIMO_PERSONAS = 30;
    private final double PROBABILIDAD_ABURRIRSE = 0.3;

    private Console console;

    public Fila() {
        primero = null;
        ultimo = null;
        numeroClientes = 0;
        console = new Console();
    }

    public boolean hayGente() {
        return primero != null;
    }

    public int obtenerNumero() {
        return numeroClientes;
    }

    public boolean estaLlena() {
        return numeroClientes >= MAXIMO_PERSONAS;
    }

    public Cliente primero() {
        return primero;
    }

    public boolean añadirCliente(Cliente cliente) {

        if (estaLlena()) {
            return false;
        }

        if (!hayGente()) {
            primero = cliente;
            ultimo = cliente;
        } else {
            ultimo.establecerSiguiente(cliente);
            ultimo = cliente;
        }

        numeroClientes++;

        return true;
    }

    public Cliente sacar() {

        if (!hayGente()) {
            return null;
        }

        Cliente cliente = primero;

        primero = primero.obtenerSiguiente();

        cliente.establecerSiguiente(null);

        numeroClientes--;

        if (numeroClientes == 0) {
            ultimo = null;
        }

        return cliente;
    }

    public boolean añadirPreferente(Cliente cliente) {

        if (estaLlena()) {
            return false;
        }

        if (!hayGente()) {
            primero = cliente;
            ultimo = cliente;
            numeroClientes++;
            return true;
        }

        if (!primero.esPreferente()) {
            cliente.establecerSiguiente(primero);
            primero = cliente;
            numeroClientes++;
            return true;
        }

        Cliente actual = primero;

        while (actual.obtenerSiguiente() != null &&
               actual.obtenerSiguiente().esPreferente()) {

            actual = actual.obtenerSiguiente();
        }

        cliente.establecerSiguiente(actual.obtenerSiguiente());
        actual.establecerSiguiente(cliente);

        if (cliente.obtenerSiguiente() == null) {
            ultimo = cliente;
        }

        numeroClientes++;

        return true;
    }

    public void comprobarAburrimiento(int minutoActual) {

        if (minutoActual < 20) {
            return;
        }

        if (minutoActual % 5 != 0) {
            return;
        }

        Cliente actual = primero;
        Cliente anterior = null;

        while (actual != null) {

            Cliente siguiente = actual.obtenerSiguiente();

            if (actual.minutosEnCola(minutoActual) > 8 &&
                Math.random() < PROBABILIDAD_ABURRIRSE) {

                if (anterior == null) {
                    primero = siguiente;
                } else {
                    anterior.establecerSiguiente(siguiente);
                }

                if (actual == ultimo) {
                    ultimo = anterior;
                }

                numeroClientes--;

                actual.establecerSiguiente(null);

            } else {
                anterior = actual;
            }

            actual = siguiente;
        }

        if (numeroClientes == 0) {
            primero = null;
            ultimo = null;
        }
    }

    public void mostrar() {

        console.writeln("FILA:");

        if (!hayGente()) {
            console.writeln("  Vacia");
        } else {

            Cliente actual = primero;
            int posicion = 1;

            while (actual != null) {

                if (actual.esPreferente()) {
                    console.writeln(
                        "  Cliente " + posicion + " - PREFERENTE"
                    );
                } else {
                    console.writeln(
                        "  Cliente " + posicion
                    );
                }

                actual = actual.obtenerSiguiente();
                posicion++;
            }
        }

        console.writeln(
            "Longitud: " + numeroClientes + " metros"
        );
    }
}