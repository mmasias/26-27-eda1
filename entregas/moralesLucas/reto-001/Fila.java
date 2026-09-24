public class Fila {

    private Cliente primero;
    private Cliente ultimo;
    private int numeroClientes;

    private final int MAXIMO_PERSONAS = 30;
    private final double PROBABILIDAD_ABURRIRSE = 0.30;

    public Fila() {
        primero = null;
        ultimo = null;
        numeroClientes = 0;
    }

    public boolean estaLlena() {
        return numeroClientes >= MAXIMO_PERSONAS;
    }

    public boolean hayGente() {
        return numeroClientes > 0;
    }

    public int obtenerNumeroClientes() {
        return numeroClientes;
    }

    public Cliente obtenerPrimero() {
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

    public Cliente sacarPrimero() {

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

        Cliente actual = primero;
        Cliente ultimoPreferente = null;

        while (actual != null) {

            if (actual.tieneAtencionPreferente()) {
                ultimoPreferente = actual;
            }

            actual = actual.obtenerSiguiente();
        }

        if (ultimoPreferente == null) {

            cliente.establecerSiguiente(primero);
            primero = cliente;

        } else {

            cliente.establecerSiguiente(
                    ultimoPreferente.obtenerSiguiente()
            );

            ultimoPreferente.establecerSiguiente(cliente);

            if (ultimoPreferente == ultimo) {
                ultimo = cliente;
            }
        }

        numeroClientes++;

        return true;
    }

    public boolean colocarDetrasDeConocido(Cliente cliente) {

        if (estaLlena()) {
            return false;
        }

        if (!hayGente()) {
            return añadirCliente(cliente);
        }

        int posicion = (int) (Math.random() * numeroClientes);

        Cliente actual = primero;

        for (int i = 0; i < posicion; i++) {
            actual = actual.obtenerSiguiente();
        }

        cliente.establecerSiguiente(
                actual.obtenerSiguiente()
        );

        actual.establecerSiguiente(cliente);

        if (actual == ultimo) {
            ultimo = cliente;
        }

        numeroClientes++;

        return true;
    }

    public boolean entregarCompras() {

        if (numeroClientes < 2) {
            return false;
        }

        Cliente actual = primero;

        while (actual != null) {

            if (actual.tieneCompras()) {
                actual.entregarCompras();
                return true;
            }

            actual = actual.obtenerSiguiente();
        }

        return false;
    }

    public void comprobarAburrimiento(int minutoActual) {

        Cliente actual = primero;
        Cliente anterior = null;

        while (actual != null) {

            if (actual.minutosEnFila(minutoActual) > 8
                    && Math.random() < PROBABILIDAD_ABURRIRSE) {

                if (anterior == null) {
                    primero = actual.obtenerSiguiente();
                } else {
                    anterior.establecerSiguiente(
                            actual.obtenerSiguiente()
                    );
                }

                if (actual == ultimo) {
                    ultimo = anterior;
                }

                numeroClientes--;

                actual = actual.obtenerSiguiente();

            } else {

                anterior = actual;
                actual = actual.obtenerSiguiente();
            }
        }

        if (numeroClientes == 0) {
            primero = null;
            ultimo = null;
        }
    }

    public void mostrar() {

        System.out.println(
                "FILA: " + numeroClientes + " personas"
        );

        Cliente actual = primero;
        int posicion = 1;

        while (actual != null) {

            if (actual.tieneAtencionPreferente()) {
                System.out.println(
                        "  Persona " + posicion + " (preferente)"
                );
            } else {
                System.out.println(
                        "  Persona " + posicion
                );
            }

            actual = actual.obtenerSiguiente();
            posicion++;
        }
    }
}