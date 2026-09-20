```java
public class Caja {

    private Cliente cliente;
    private boolean abierta;
    private int clientesAtendidos;
    private Console console;

    public Caja() {
        abierta = false;
        clientesAtendidos = 0;
        console = new Console();
    }

    public boolean puedeAtender() {
        return abierta && estaLibre();
    }

    public boolean estaLibre() {
        return cliente == null;
    }

    public void añadirCliente(Cliente nuevoCliente) {
        cliente = nuevoCliente;
    }

    public void procesarAtencion() {
        if (abierta && !estaLibre()) {
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
        if (abierta == false) {
            console.writeln(" cerrada");
        } else {
            if (cliente == null) {
                console.writeln(" libre");
            } else {
                console.writeln(" Abierta -> cliente atendiendo");
            }
        }
    }
}
```
