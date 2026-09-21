public class Caja {
    private int numero;
    private Cliente cliente;
    private int itemsVendidos;
    private int personasAtendidas;
    private int itemsRestantes;
    private Console console;

    public Caja(int numero) {
        this.numero = numero;
        this.cliente = null;
        this.itemsVendidos = 0;
        this.personasAtendidas = 0;
        this.itemsRestantes = 0;
        this.console = new Console();
    }

    public boolean estaLibre() {
        return this.cliente == null;
    }

    public void asignar(Cliente cliente) {
        this.cliente = cliente;
        this.itemsRestantes = cliente.obtenerItems();
    }

    public void avanzarAtencion() {
        if (!this.estaLibre()) {
            this.itemsRestantes = this.itemsRestantes - 1;
            if (this.itemsRestantes == 0) {
                this.personasAtendidas = this.personasAtendidas + 1;
                this.itemsVendidos = this.itemsVendidos + this.cliente.obtenerItems();
                this.cliente = null;
            }
        }
    }

    public void mostrar() {
        console.write("Caja [" + numero + "] ");
        for (int i = 0; i < itemsRestantes; i++) {
            console.write("[:]");
        }
        console.writeln();
    }

    public int obtenerPersonasAtendidas() {
        return this.personasAtendidas;
    }

    public int obtenerItemsVendidos() {
        return this.itemsVendidos;
    }

    public boolean puedeAtender(Cliente cliente) {
        return true;
    }

    public int obtenerNumero() {
        return this.numero;
    }

    public int obtenerItemsRestantes() {
        return this.itemsRestantes;
    }

    public Console obtenerConsole() {
        return this.console;
    }
}