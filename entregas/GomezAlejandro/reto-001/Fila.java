public class Fila {

    private static final int CAPACIDAD_MAXIMA = 30;
    private static final int PRIMERA_POSICION = 0;
    private static final int ULTIMA_POSICION = 1;

    private final Cliente[] clientes;
    private int numClientes;
    private final int capacidadMax = CAPACIDAD_MAXIMA;

    public Fila() {
        clientes = new Cliente[capacidadMax];
        numClientes = PRIMERA_POSICION;
    }

    public void encolar(Cliente cliente) {

        if (numClientes < capacidadMax) {
            clientes[numClientes] = cliente;
            numClientes++;
        } else {
            System.out.println("La fila está llena.");
        }
    }

    public void encolarPreferente(Cliente cliente) {

        if (numClientes < capacidadMax) {

            int posicion = PRIMERA_POSICION;

            for (int i = PRIMERA_POSICION; i < numClientes; i++) {

                if (clientes[i].esPreferente()) {
                    posicion = i + ULTIMA_POSICION;
                }
            }

            for (int i = numClientes; i > posicion; i--) {
                clientes[i] = clientes[i - ULTIMA_POSICION];
            }

            clientes[posicion] = cliente;
            numClientes++;

        } else {
            System.out.println("La fila está llena.");
        }
    }

    public void colarse(Cliente cliente, int posicion) {

        if (numClientes < capacidadMax
                && posicion >= PRIMERA_POSICION
                && posicion < numClientes) {

            for (int i = numClientes; i > posicion + ULTIMA_POSICION; i--) {
                clientes[i] = clientes[i - ULTIMA_POSICION];
            }

            clientes[posicion + ULTIMA_POSICION] = cliente;
            numClientes++;

        } else {
            System.out.println("La fila está llena.");
        }
    }

    public Cliente desencolar() {

        if (numClientes > PRIMERA_POSICION) {

            Cliente cliente = clientes[PRIMERA_POSICION];

            for (int i = PRIMERA_POSICION; i < numClientes - ULTIMA_POSICION; i++) {
                clientes[i] = clientes[i + ULTIMA_POSICION];
            }

            clientes[numClientes - ULTIMA_POSICION] = null;
            numClientes--;

            return cliente;
        }

        return null;
    }

    public int getNumClientes() {
        return numClientes;
    }

    public Cliente getCliente(int posicion) {

        if (posicion >= PRIMERA_POSICION && posicion < numClientes) {
            return clientes[posicion];
        }

        return null;
    }

    public void aumentarTiempoClientes() {

        for (int i = PRIMERA_POSICION; i < numClientes; i++) {
            clientes[i].aumentarTiempo();
        }
    }

    public void eliminarClientesAburridos() {

        int i = PRIMERA_POSICION;

        while (i < numClientes) {

            if (clientes[i].clienteSeMarcha()) {

                for (int j = i; j < numClientes - ULTIMA_POSICION; j++) {
                    clientes[j] = clientes[j + ULTIMA_POSICION];
                }

                clientes[numClientes - ULTIMA_POSICION] = null;
                numClientes--;

            } else {
                i++;
            }
        }
    }

    public void entregarCompras(int posicion, int destino) {

        if (posicion >= PRIMERA_POSICION
                && posicion < numClientes
                && destino >= PRIMERA_POSICION
                && destino < numClientes
                && posicion != destino) {

            clientes[posicion].entregarCompras(clientes[destino]);

            for (int i = posicion; i < numClientes - ULTIMA_POSICION; i++) {
                clientes[i] = clientes[i + ULTIMA_POSICION];
            }

            clientes[numClientes - ULTIMA_POSICION] = null;
            numClientes--;
        }
    }

    @Override
    public String toString() {

        String resultado = "Fila: ";

        for (int i = PRIMERA_POSICION; i < numClientes; i++) {
            resultado += clientes[i] + " ";
        }

        return resultado;
    }
}