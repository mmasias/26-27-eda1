public class Caja {

    private Cliente cliente;
    private boolean abierta;
    private int clientesAtendidos;
    private Console console;

    public Caja() {
        cliente = null;
        abierta = false;
        clientesAtendidos = 0;
        console = new Console();
    }

    public boolean puedeAtender() {
        return abierta && cliente == null;
    }

    public boolean estaLibre() {
        return cliente == null;
    }

    public void añadirCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void procesarAtencion() {
        if (abierta && cliente != null) {
            clientesAtendidos++;
            cliente = null;
            abierta = false;
        }
    }

    public int clientesAtendidos() {
        return clientesAtendidos;
    }

    public boolean estaAbierta() {
        return abierta;
    }

    public void abrir() {
        abierta = true;
    }

    public void mostrar() {
        if (!abierta) {
            console.writeln(" cerrada");
        } else if (cliente == null) {
            console.writeln(" libre");
        } else {
            console.writeln(" Abierta -> cliente atendiendo");
        }
    }
}