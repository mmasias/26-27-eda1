public class Caja {

    protected int numero;
    private Cliente cliente;
    private int personasAtendidas;
    private boolean abierta;
    private Console console;

    public Caja(int numero) {
        this.numero = numero;
        personasAtendidas = 0;
        abierta = false;
        console = new Console();
    }

    public void abrir() {
        abierta = true;
    }

    public boolean estaAbierta() {
        return abierta;
    }

    public boolean estaLibre() {
        return abierta && cliente == null;
    }

    public void asignar(Cliente cliente) {
        this.cliente = cliente;
    }

    public void avanzarAtencion() {
        if (cliente != null) {
            personasAtendidas = personasAtendidas + 1;
            cliente = null;
            abierta = false;
        }
    }

    public void mostrar() {
        console.write("Caja [" + numero + "] ");
        if (!abierta) {
            console.writeln("Cerrada");
        } else if (this.estaLibre()) {
            console.writeln("Libre");
        } else {
            console.writeln("Atendiendo cliente");
        }
    }

    public int obtenerPersonasAtendidas() {
        return personasAtendidas;
    }


}