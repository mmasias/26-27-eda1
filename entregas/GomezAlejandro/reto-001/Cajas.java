public class Cajas {

    private final Cliente[] clientes;
    private final int[] productosPendientes;
    private int numCajas;

    private final int capacidadMax = 10;

    public Cajas() {
        clientes = new Cliente[capacidadMax];
        productosPendientes = new int[capacidadMax];

        numCajas = 1;
    }

    public boolean abrirCaja() {

        if (numCajas < capacidadMax && Math.random() < 0.4) {
            numCajas++;
            return true;
        }

        return false;
    }

    public boolean cerrarCaja(Fila fila) {

        if (fila.getNumClientes() == 0 && numCajas > 1) {

            for (int i = numCajas - 1; i > 0; i--) {

                if (clientes[i] == null) {

                    for (int j = i; j < numCajas - 1; j++) {
                        clientes[j] = clientes[j + 1];
                        productosPendientes[j] = productosPendientes[j + 1];
                    }

                    clientes[numCajas - 1] = null;
                    productosPendientes[numCajas - 1] = 0;

                    numCajas--;

                    return true;
                }
            }
        }

        return false;
    }

    public boolean hayCajaLibre() {

        for (int i = 0; i < numCajas; i++) {

            if (clientes[i] == null) {
                return true;
            }
        }

        return false;
    }

    public void meterCliente(Cliente cliente) {

        for (int i = 0; i < numCajas; i++) {

            if (clientes[i] == null) {

                clientes[i] = cliente;
                productosPendientes[i] = cliente.numProductos();

                return;
            }
        }
    }

    public int atender() {

        int clientesAtendidos = 0;

        for (int i = 0; i < numCajas; i++) {

            if (clientes[i] != null) {

                int maxProductos = productosPendientes[i];
                int productos = (int) (Math.random() * maxProductos) + 1;

                productosPendientes[i] =
                        productosPendientes[i] - productos;

                if (productosPendientes[i] == 0) {

                    clientes[i] = null;
                    clientesAtendidos++;
                }
            }
        }

        return clientesAtendidos;
    }

    @Override
    public String toString() {

        String resultado = "Cajas: ";

        for (int i = 0; i < numCajas; i++) {

            resultado = resultado + "Caja " + (i + 1) + ": ";

            if (clientes[i] == null) {

                resultado = resultado + "[LIBRE]";

            } else {

                for (int j = 0; j < productosPendientes[i]; j++) {
                    resultado = resultado + "[*]";
                }

                resultado = resultado + " " + clientes[i];
            }

            resultado = resultado + "   ";
        }

        return resultado;
    }
}