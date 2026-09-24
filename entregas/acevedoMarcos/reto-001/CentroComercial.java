public class CentroComercial {

    private Fila fila;
    private Tiempo tiempo;

    private Caja caja1;
    private Caja caja2;
    private Caja caja3;
    private Caja caja4;

    private Console console;

    private boolean haLlegadoCliente;

    private final double PROBABILIDAD_LLEGADA_CLIENTES = 0.6;
    private final double PROBABILIDAD_CAJA_LIBRE = 0.4;

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

        do {

            tiempo.avanzar();

            procesarLlegadaCliente();

            procesarAburrimiento();

            procesarAperturaCaja();

            atenderClientes();

            asignarClientesACajas();

            procesarAviso();

            mostrarEstado();

            pausar();

        } while (!tiempo.haFinalizado());

        mostrarResumen();
    }

    private void procesarLlegadaCliente() {

        haLlegadoCliente =
            Math.random() < PROBABILIDAD_LLEGADA_CLIENTES;

        if (haLlegadoCliente) {

            boolean preferente = Math.random() < 0.2;

            Cliente cliente =
                new Cliente(
                    tiempo.obtenerMinuto(),
                    preferente
                );

            if (preferente) {
                fila.añadirPreferente(cliente);
            } else {
                fila.añadirCliente(cliente);
            }
        }
    }

    private void procesarAburrimiento() {

        fila.comprobarAburrimiento(
            tiempo.obtenerMinuto()
        );
    }

    private void procesarAperturaCaja() {

        if (Math.random() < PROBABILIDAD_CAJA_LIBRE) {

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

    private void asignarClientesACajas() {

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

    private void procesarAviso() {

        int minuto = tiempo.obtenerMinuto();

        if (minuto % 15 == 0 &&
            fila.obtenerNumero() > 25) {

            console.writeln(
                "AVISO: pasen por esta caja en orden de fila"
            );
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

        int numeroClientesAtendidos =
            caja1.clientesAtendidos()
            + caja2.clientesAtendidos()
            + caja3.clientesAtendidos()
            + caja4.clientesAtendidos();

        console.writeln();
        console.writeln("========== RESUMEN ==========");

        console.writeln(
            "Numero de clientes atendidos: "
            + numeroClientesAtendidos
        );

        console.writeln(
            "Personas en fila: "
            + fila.obtenerNumero()
        );
    }

    private void pausar() {
        console.pause(1);
    }
}