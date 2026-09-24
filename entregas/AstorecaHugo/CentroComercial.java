
public class CentroComercial {

    private final double PROBABILIDAD_LLEGADA_CLIENTE = 0.6;
    private final double PROBABILIDAD_DE_CAJA_VACIA = 0.4;
    private Fila filas;
    private Tiempo tiempo;
    private Caja caja1;
    private Caja caja2;
    private Caja caja3;
    private Caja caja4;
    private boolean haLlegadoCliente;
    private Console console;

    public CentroComercial() {
        console = new Console();
        filas = new Fila();
        tiempo = new Tiempo();
        caja1 = new Caja(1);
        caja2 = new Caja(2);
        caja3 = new Caja(3);
        caja4 = new Caja(4);
    }

    public void simular() {
        do {

            tiempo.avanzarTiempo();
            this.procesarLlegadaCliente();
            this.asignarClienteACaja();
            this.procesarCajas();
            this.mostrarEstado();

        } while (!tiempo.haFinalizado());
        this.mostrarResumen();

    }

    private void procesarLlegadaCliente() {
        haLlegadoCliente = Math.random() <= PROBABILIDAD_LLEGADA_CLIENTE;
        if (haLlegadoCliente) {
            Cliente cliente = new Cliente();
            fila.añadirCliente(cliente);
        }
    }

    private void asignarClienteACaja() {
        if (caja1.estaLibre()
                && fila.hayClientes()
                && caja1.puedeAtender(fila.primero())) {

            Cliente cliente = fila.quitarCliente();
            caja1.asignar(cliente);
        }

        if (caja2.estaLibre()
                && fila.hayClientes()
                && caja2.puedeAtender(fila.primero())) {

            Cliente cliente = fila.quitarCliente();
            caja2.asignar(cliente);
        }

        if (caja3.estaLibre()
                && fila.hayClientes()
                && caja3.puedeAtender(fila.primero())) {

            Cliente cliente = fila.quitarCliente();
            caja3.asignar(cliente);
        }

        if (caja4.estaLibre()
                && fila.hayClientes()
                && caja4.puedeAtender(fila.primero())) {

            Cliente cliente = fila.quitarCliente();
            caja4.asignar(cliente);
        }

    

    private void procesarCajas() {
        caja1.avanzarAtencion();
        caja2.avanzarAtencion();
        caja3.avanzarAtencion();
        caja4.avanzarAtencion();
    }

    private void mostrarEstado() {
        console.cleanScreen();
        tiempo.mostrar(haLlegadoCliente);
        fila.mostrar();
        this.mostrarCajas();
    }

    private void mostrarResumen() {
        int personasAtendidas = caja1.obtenerPersonasAtendidas()
                + caja2.obtenerPersonasAtendidas()
                + caja3.obtenerPersonasAtendidas()
                + caja4.obtenerPersonasAtendidas();
        console.writeln("\nResumen final");
        console.writeln("Personas atendidas: " + personasAtendidas);
        console.writeln("Personas en fila: " + fila.obtenerCantidadPersonasEnFila());
        tiempo.mostrar(haLlegadoCliente);
        console.writeln("Fila (" + fila.obtenerCantidadPersonasEnFila() + "): ");
        fila.mostrar();

    }
}
