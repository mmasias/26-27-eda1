public class Caja {
  private Cliente cliente;
  private boolean abierta;
  private Caja proxima;
  private int clientesAtendidos;
  private Console console;

  public Caja() {
    abierta = false;
    console = new Console();

  }

  public void proxima(Caja caja) {
    proxima = caja;
  }

  public Caja obtenerProxima() {
    return proxima;
  }

  public boolean puedeAtender() {
    return this.estaLibre() && abierta == true;
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
