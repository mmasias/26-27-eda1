public class Caja {

    private Cliente cliente;
    private boolean abierta;
    private int clientesAtendidos;

    public Caja() {
        abierta = false;
        cliente = null;
        clientesAtendidos = 0;
    }

    public boolean estaAbierta() {
        return abierta;
    }

    public boolean estaLibre() {
        return cliente == null;
    }

    public boolean puedeAtender() {
        return abierta && estaLibre();
    }

    public void abrir() {
        abierta = true;
    }

    public void añadirCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void atender() {
        if (abierta && cliente != null) {
            clientesAtendidos++;
            cliente = null;
            abierta = false;
        }
    }

    public int obtenerClientesAtendidos() {
        return clientesAtendidos;
    }
}