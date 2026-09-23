public class CentroComercial {
    private Fila fila;
    private Tiempo tiempo;
    private Caja caja1;
    private Caja caja2;
    private Caja caja3;
    private Caja caja4;
    private Console console;

    private boolean haLlegadoCliente;
    
    private final double PROB_LLEG_CLIENT = 0.6;
    private final double PROB_CAJA_ABIERTA = 0.4;


    public CentroComercial() {
        fila = new Fila();
        tiempo = new Tiempo();
        caja1 = new Caja(1);
        caja2 = new Caja(2);
        caja3 = new Caja(3);
        caja4 = new Caja(4);
        console = new Console();
    }

    public void execute() {
        do {
            tiempo.avanzar();
            this.procesarLlegadaCliente();
            this.abrirCaja();
            this.asignarClientesACajas();
            this.mostrarEstado();
            this.procesarAtencionCajas();
            this.pausar();
        } while (!tiempo.haFinalizado());
        this.mostrarResumen();
    }

    private void mostrarResumen() {
        int personasAtendidas = caja1.obtenerPersonasAtendidas()
            + caja2.obtenerPersonasAtendidas()
            + caja3.obtenerPersonasAtendidas()
            + caja4.obtenerPersonasAtendidas();
        console.writeln("\nResumen final");
        console.writeln("Personas atendidas: " + personasAtendidas);
        console.writeln("Personas en fila: " + fila.obtenerCantidadPersonasEnFila());
    }

    private void pausar() {
        console.pause(1);
    }

    private void mostrarEstado() {
        console.cleanScreen();
        tiempo.mostrar(haLlegadoCliente);
        console.writeln("Fila (" + fila.obtenerCantidadPersonasEnFila() + "): ");
        fila.mostrar();
        caja1.mostrar();
        caja2.mostrar();
        caja3.mostrar();
        caja4.mostrar();
    }

    private void abrirCaja() {
        if (Math.random() <= PROB_CAJA_ABIERTA) {
            if (!caja1.estaAbierta()) {
                caja1.abrir();
            } else if (!caja2.estaAbierta()) {
                caja2.abrir();
            } else if (!caja3.estaAbierta()) {
                caja3.abrir();
            } else if (!caja4.estaAbierta()) {
                caja4.abrir();
            }
        }
    }

    private void procesarAtencionCajas() {
        caja1.avanzarAtencion();
        caja2.avanzarAtencion();
        caja3.avanzarAtencion();
        caja4.avanzarAtencion();
    }

    private void asignarClientesACajas() {
    if (!fila.hayClientes()) {
        return;
    }
    if (caja1.estaLibre()) {
        caja1.asignar(fila.quitarCliente());
    } else if (caja2.estaLibre()) {
        caja2.asignar(fila.quitarCliente());
    } else if (caja3.estaLibre()) {
        caja3.asignar(fila.quitarCliente());
    } else if (caja4.estaLibre()) {
        caja4.asignar(fila.quitarCliente());
    }
}

    private void procesarLlegadaCliente() {
        haLlegadoCliente = Math.random() <= PROB_LLEG_CLIENT;
        if (haLlegadoCliente) {
            Cliente cliente = new Cliente();
            fila.añadirCliente(cliente);
        }
    }
}