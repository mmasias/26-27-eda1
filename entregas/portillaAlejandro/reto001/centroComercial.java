public class CentroComercial {

    private Fila fila;
    private Tiempo tiempo;
    private Caja[] cajas;
    private Console console;

    private boolean haLlegadoCliente;

    private final double PROBABILIDAD_LLEGADA = 0.6;
    private final double PROBABILIDAD_APERTURA = 0.4;
    private final int NUMERO_CAJAS = 4;

    public CentroComercial() {
        fila = new Fila();
        tiempo = new Tiempo();
        cajas = new Caja[NUMERO_CAJAS];
        console = new Console();

        for (int i = 0; i < cajas.length; i++) {
            cajas[i] = new Caja();
        }
    }

    public void ejecutar() {

        while (!tiempo.haFinalizado()) {

            tiempo.avanzar();

            procesarLlegadaCliente();
            procesarAperturaCaja();
            atenderClientes();
            asignarClientes();
            fila.registrarEstado();

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

            for (int i = 0; i < cajas.length; i++) {

                if (!cajas[i].estaAbierta()) {
                    cajas[i].abrir();
                }
            }
        }
    }

    private void atenderClientes() {

        for (int i = 0; i < cajas.length; i++) {
            cajas[i].procesarAtencion();
        }
    }

    private void asignarClientes() {

        for (int i = 0; i < cajas.length; i++) {

            if (cajas[i].puedeAtender() && fila.hayGente()) {
                Cliente cliente = fila.sacar();
                cajas[i].añadirCliente(cliente);
            }
        }
    }

    private void mostrarEstado() {

        console.cleanScreen();

        tiempo.mostrar(haLlegadoCliente);
        fila.mostrar();
        mostrarCajas();
    }

    private void mostrarCajas() {

        for (int i = 0; i < cajas.length; i++) {

            console.write("Caja[" + (i + 1) + "]");
            cajas[i].mostrar();
        }
    }

    private void mostrarResumen() {

        int atendidos = 0;

        for (int i = 0; i < cajas.length; i++) {
            atendidos = atendidos + cajas[i].clientesAtendidos();
        }

        console.writeln("Numero de clientes atendidos: " + atendidos);
        console.writeln("Personas en fila: " + fila.obtenerNumero());
    }

    private void pausar() {
        console.pause(2);
    }
}
