public class Fila {

    private Cliente[] clientes;
    private int numeroClientes;

    private int[] estados;
    private int numeroEstados;

    private final double PROBABILIDAD_ABURRIRSE = 0.3;

    private Console console;

    public Fila() {

        clientes = new Cliente[1000];
        estados = new int[240];

        numeroClientes = 0;
        numeroEstados = 0;

        console = new Console();
    }

    public Cliente primero() {
        return clientes[0];
    }

    public boolean hayGente() {
        return numeroClientes != 0;
    }

    public Cliente sacar() {

        Cliente clienteSacado = clientes[0];

        for (int i = 0; i < numeroClientes - 1; i++) {
            clientes[i] = clientes[i + 1];
        }

        numeroClientes--;

        return clienteSacado;
    }

    public void añadirCliente(Cliente cliente) {

        clientes[numeroClientes] = cliente;
        numeroClientes++;
    }

    public int obtenerNumero() {
        return numeroClientes;
    }

    public void registrarEstado() {

        estados[numeroEstados] = numeroClientes;
        numeroEstados++;
    }

    public void mostrar() {

        console.writeln("FILA:");

        if (hayGente()) {

            for (int i = 0; i < numeroClientes; i++) {
                console.writeln("  Cliente " + (i + 1));
            }

        } else {
            console.writeln("  Vacia");
        }
    }

    public void comprobarAburrimiento(int minutoActual) {

        int i = 0;

        while (i < numeroClientes) {

            if (clientes[i].minutosEnCola(minutoActual) > 8) {

                if (Math.random() < PROBABILIDAD_ABURRIRSE) {

                    for (int j = i; j < numeroClientes - 1; j++) {
                        clientes[j] = clientes[j + 1];
                    }

                    numeroClientes--;
                } else {
                    i++;
                }

            } else {
                i++;
            }
        }
    }
}