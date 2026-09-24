public class CentroComercial {
    private Fila fila;
    private Tiempo tiempo;

    private Caja caja1;
    private Caja caja2;
    private Caja caja3;
    private Caja caja4;

    private Console console;

    private boolean haLlegadoCliente;

    private final double PROBABILIDAD_LLEGADA = 0.6;
    private final double PROBABILIDAD_APERTURA = 0.4;

    public CentroComercial() {
        fila = new Fila();
        tiempo = new Tiempo();

        caja1 = new Caja();
        caja2 = new Caja();
        caja3 = new Caja();
        caja4 = new Caja();

        console = new Console();
    }

    public void ejecutar() {
        while (!tiempo.haFinalizado()) {
            tiempo.avanzar();

            procesarLlegadaCliente();
            procesarAperturaCaja();
            atenderClientes();
            asignarClientes();

            mostrarEstado();
            pausar();
        }

        mostrarResumen();
    }

    private void procesarLlegadaCliente() {
        haLlegadoCliente = Math.random() < PROBABILIDAD_LLEGADA;

        if (haLlegadoCliente) {
            Cliente nuevo = new Cliente(tiempo.obtenerMinuto());
            fila.añadirCliente(nuevo);
        }
    }

    private void procesarAperturaCaja() {
        if (Math.random() < PROBABILIDAD_APERTURA) {

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

    private void atenderClientes() {
        caja1.procesarAtencion();
        caja2.procesarAtencion();
        caja3.procesarAtencion();
        caja4.procesarAtencion();
    }

    private void asignarClientes() {

        if (caja1.puedeAtender() && fila.hayGente()) {
            caja1.añadirCliente(fila.sacar());
        }

        if (caja2.puedeAtender() && fila.hayGente()) {
            caja2.añadirCliente(fila.sacar());
        }

        if (caja3.puedeAtender() && fila.hayGente()) {
            caja3.añadirCliente(fila.sacar());
        }

        if (caja4.puedeAtender() && fila.hayGente()) {
            caja4.añadirCliente(fila.sacar());
        }
    }

    private void mostrarEstado() {
        console.cleanScreen();

        tiempo.mostrar(haLlegadoCliente);
        fila.mostrar();

        mostrarCajas();
    }

    private void mostrarCajas() {
        console.write("Caja[1]");
        caja1.mostrar();

        console.write("Caja[2]");
        caja2.mostrar();

        console.write("Caja[3]");
        caja3.mostrar();

        console.write("Caja[4]");
        caja4.mostrar();
    }

    private void mostrarResumen() {
        int atendidos = 0;

        atendidos = atendidos + caja1.clientesAtendidos();
        atendidos = atendidos + caja2.clientesAtendidos();
        atendidos = atendidos + caja3.clientesAtendidos();
        atendidos = atendidos + caja4.clientesAtendidos();

        console.writeln("Numero de clientes atendidos: " + atendidos);
        console.writeln("Personas en fila: " + fila.obtenerNumero());
    }

    private void pausar() {
        console.pause(2);
    }
}