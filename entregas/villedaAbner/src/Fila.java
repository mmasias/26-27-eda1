public class Fila {
    private int cantidadActualPersonas;
    final private double PROBABILIDAD_DE_PERSONA = 0.6;
    private Cliente[] filaClientes;
    final private int MAXIMO_PERSONAS_LINEA = 30;

    public Fila() {
        filaClientes = new Cliente[35];
        cantidadActualPersonas = 0;

    }

    public boolean llegaCliente() {
        return Math.random() < PROBABILIDAD_DE_PERSONA;
    }

    public void recibePersona(Cliente clienteRecibido) {
        int personasVipTotal = 0;
        if (cantidadActualPersonas < MAXIMO_PERSONAS_LINEA) {
            filaClientes[cantidadActualPersonas] = clienteRecibido;
            cantidadActualPersonas++;
        }
        if (clienteRecibido.esPreferente()) {
            for (int i = 0; i < cantidadActualPersonas - 1; i++) {
                if (!filaClientes[i].esPreferente()) {
                    break;
                }
                personasVipTotal++;
            }

            for (int i = cantidadActualPersonas; i > personasVipTotal; i--) {
                filaClientes[i] = filaClientes[i - 1];
            }
            filaClientes[personasVipTotal] = clienteRecibido;

        }
    }

    public void eliminaPersona(int personaEliminada) {
        filaClientes[0] = null;
        recorrerArray(0);
    }

    public int getNumeroPersonas() {
        return cantidadActualPersonas;
    }

    public Cliente getCliente(int posicion) {
        return filaClientes[posicion];
    }

    public void clienteSaleFila(int posicionCliente) {
        filaClientes[posicionCliente] = null;
        recorrerArray(posicionCliente);

    }

    public boolean estaAburrido(int posicion) {
        if (filaClientes[posicion].getMinutoLlegada() > 8) {
            return Math.random() < 0.3;

        } else {
            return false;
        }
    }

    private void recorrerArray(int posicionARecorrer) {
        for (int i = posicionARecorrer; i < cantidadActualPersonas - 1; i++) {
            filaClientes[i] = filaClientes[i + 1];
        }
        cantidadActualPersonas--;
        filaClientes[cantidadActualPersonas] = null;

    }

    public void colarCliente(Cliente clienteColado) {
        if (cantidadActualPersonas >= MAXIMO_PERSONAS_LINEA)
            return;
        if (cantidadActualPersonas == 0) {
            filaClientes[0] = clienteColado;
            cantidadActualPersonas++;
            return;
        }
        int posicionAmigo = (int) (Math.random() * cantidadActualPersonas);
        int posicionDestino = posicionAmigo + 1;

        for (int i = cantidadActualPersonas; i > posicionDestino; i--) {
            filaClientes[i] = filaClientes[i - 1];
        }
        filaClientes[posicionDestino] = clienteColado;
        cantidadActualPersonas++;

    }
}
