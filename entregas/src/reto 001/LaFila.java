public class LaFila {
    private Cola cola;
    private Tiempo tiempo;
    private int personasAtendidas;
    private Console console;

    public LaFila() {
        cola = new Cola();
        tiempo = new Tiempo();
        personasAtendidas = 0;
        console = new Console();
    }

    public static void main(String[] args) {
        LaFila simulacion = new LaFila();
        simulacion.simular();
    }

    public void simular() {
        while (!tiempo.haFinalizado()) {
            this.procesarLlegadaCliente();
            this.procesarAtencion();
            tiempo.avanzar();
        }

        this.mostrarResumen();
    }

    private void procesarLlegadaCliente() {
        if (this.llegaCliente()) {
            Cliente cliente = new Cliente();
            cola.anadirCliente(cliente);
        }
    }

    private boolean llegaCliente() {
        final double PROBABILIDAD_LLEGADA = 0.6;

        return Math.random() <= PROBABILIDAD_LLEGADA;
    }

    private void procesarAtencion() {
        if (this.hayCajaLibre() && cola.hayClientes()) {
            cola.quitarCliente();
            personasAtendidas = personasAtendidas + 1;
        }
    }

    private boolean hayCajaLibre() {
        final double PROBABILIDAD_CAJA_LIBRE = 0.4;

        return Math.random() <= PROBABILIDAD_CAJA_LIBRE;
    }

    private void mostrarResumen() {
        console.writeln("Personas atendidas: " + personasAtendidas);
        console.writeln("Personas que quedaron en fila: "
            + cola.obtenerCantidadClientes());
    }
}
