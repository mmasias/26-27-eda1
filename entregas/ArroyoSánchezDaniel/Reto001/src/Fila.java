public class Fila {

    private Cliente[] clientes;
    private int numClientes;
    private int capacidadMax = 30;

    public Fila() {
        clientes = new Cliente[capacidadMax];
        numClientes = 0;
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

            int posicion = 0;

            for (int i = 0; i < numClientes; i++) {
                if (clientes[i].esPreferente()) {
                    posicion = i + 1;
                }
            }

            for (int i = numClientes; i > posicion; i--) {
                clientes[i] = clientes[i - 1];
            }

            clientes[posicion] = cliente;
            numClientes++;

        } else {
            System.out.println("La fila está llena.");
        }
    }

    public Cliente desencolar() {

        if (numClientes > 0) {

            Cliente cliente = clientes[0];

            for (int i = 0; i < numClientes - 1; i++) {
                clientes[i] = clientes[i + 1];
            }

            clientes[numClientes - 1] = null;
            numClientes--;

            return cliente;
        }

        return null;
    }

    public int getNumClientes() {
        return numClientes;
    }

    public Cliente getCliente(int posicion) {
        if (posicion >= 0 && posicion < numClientes) {
            return clientes[posicion];
        }

        return null;
    }

    public void aumentarTiempoClientes() {

        for (int i = 0; i < numClientes; i++) {
            clientes[i].aumentarTiempo();
        }
    }

    public void eliminarClientesAburridos() {

        int i = 0;

        while (i < numClientes) {

            if (clientes[i].clienteSeMarcha()) {

                for (int j = i; j < numClientes - 1; j++) {
                    clientes[j] = clientes[j + 1];
                }

                clientes[numClientes - 1] = null;
                numClientes--;

            } else {
                i++;
            }
        }
    }

    @Override
    public String toString() {

        String resultado = "Fila: ";

        for (int i = 0; i < numClientes; i++) {
            resultado += clientes[i] + " ";
        }

        return resultado;
    }
}