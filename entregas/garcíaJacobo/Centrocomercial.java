public class CentroComercial {

    private Fila fila;
    private Tiempo tiempo;
    private Caja primera;
    private Caja ultima;
    private Console console;
    private boolean haLlegadoCliente;
    private final double PROBABILIDAD_LLEGADA_CLIENTES = 0.6;
    private final double PROBABILIDAD_CAJA_LIBRE = 0.4;
    private final int NUMERO_CAJAS = 4;

    public CentroComercial() {
        fila = new Fila();
        tiempo = new Tiempo();
        console = new Console();
        for (int i = 0; i < NUMERO_CAJAS; i++) {
            if (!this.hayCajas()) {
                primera = new Caja();
                ultima = primera;
            } else {
                Caja caja = new Caja();
                ultima.proxima(caja);
                ultima = caja;
            }
        }

    }

    private boolean hayCajas() {
        return primera != null;
    }

    public void ejecutar() {
        do {

            tiempo.avanzar();
            this.procesarLlegadaCliente();
            this.procesarAperturaCaja();
            this.atenderCliente();
            this.asignarClienteACaja();
            this.mostrarEstado();
            this.pausar();

        } while (!tiempo.haFinalizado());

        this.mostrarResumen();
    }

    private void procesarAperturaCaja() {
        if (Math.random() < PROBABILIDAD_CAJA_LIBRE) {
            Caja cajaActual = primera;
            while (cajaActual != null) {
                if (!cajaActual.estaAbierta()) {
                    cajaActual.abrir();
                }

                cajaActual = cajaActual.obtenerProxima();
            }
        }
    }

    private void mostrarEstado() {
        console.cleanScreen();
        tiempo.mostrar(haLlegadoCliente);
        fila.mostrar();
        this.mostrarCajas();

    }

    private void mostrarCajas() {
        Caja cajaActual = primera;
        int contador = 1;
        while (cajaActual != null) {
            console.write("Caja[" + contador + "]");
            cajaActual.mostrar();
            cajaActual = cajaActual.obtenerProxima();
            contador++;
        }
    }

    private void mostrarResumen() {
        Caja cajaActual = primera;
        int numeroClientesAtendidos = 0;
        int personasEnFila = 0;
        while (cajaActual != null) {
            numeroClientesAtendidos = numeroClientesAtendidos + cajaActual.clientesAtendidos();
            cajaActual = cajaActual.obtenerProxima();
        }
        personasEnFila = fila.obtenerNumero();
        console.writeln("Numero de clientes atendidos: " + numeroClientesAtendidos);
        console.writeln("Personas en fila: " + personasEnFila);
    }

    private void pausar() {
        console.pause(2);
    }

    private void atenderCliente() {

        Caja cajaActual = primera;
        while (cajaActual != null) {
            cajaActual.procesarAtencion();
            cajaActual = cajaActual.obtenerProxima();
        }
    }

    private void asignarClienteACaja() {
        Caja cajaActual = primera;
        while (cajaActual != null) {
            if (cajaActual.puedeAtender() && fila.hayGente()) {
                Cliente cliente = fila.sacar();
                cajaActual.añadirCliente(cliente);

            }
            cajaActual = cajaActual.obtenerProxima();
        }
    }

    private void procesarLlegadaCliente() {
        haLlegadoCliente = Math.random() < PROBABILIDAD_LLEGADA_CLIENTES;
        if (haLlegadoCliente) {
            Cliente cliente = new Cliente(tiempo.obtenerMinuto());
            fila.añadirCliente(cliente);
        }
    }

}
