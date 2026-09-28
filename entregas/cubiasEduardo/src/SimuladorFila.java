public class SimuladorFila {

    private static final int MINUTOS_TOTALES = 120;
    private static final double PROBABILIDAD_LLEGADA = 0.6;
    private static final double PROBABILIDAD_ATENCION = 0.4;
    private static final double PROBABILIDAD_PRIORIDAD = 0.2;

    private Cola colaEspera;
    private int personasAtendidas;
    private int clientesNoEntraron;
    private String tituloSimulacion;
    private Console console;

    public SimuladorFila(Cola colaEspera, String tituloSimulacion) {
        this.colaEspera = colaEspera;
        this.tituloSimulacion = tituloSimulacion;
        this.personasAtendidas = 0;
        this.clientesNoEntraron = 0;
        this.console = new Console();
    }

    public void ejecutar() {
        console.writeln("========================================");
        console.writeln(tituloSimulacion);
        console.writeln("========================================");
        for (int minutoActual = 1; minutoActual <= MINUTOS_TOTALES; minutoActual++) {
            evaluarLlegadaCliente(minutoActual);
            evaluarAperturaCaja();
            mostrarEstadoMinuto(minutoActual);
        }
        mostrarResultados();
    }

    private void evaluarLlegadaCliente(int minutoActual) {
        if (Math.random() <= PROBABILIDAD_LLEGADA) {
            boolean esPrioritario = Math.random() <= PROBABILIDAD_PRIORIDAD;
            Cliente nuevoCliente = new Cliente(esPrioritario, minutoActual);
            boolean encolado = colaEspera.encolar(nuevoCliente);
            if (!encolado) {
                clientesNoEntraron++;
            }
        }
    }

    private void evaluarAperturaCaja() {
        if (Math.random() <= PROBABILIDAD_ATENCION) {
            if (!colaEspera.estaVacia()) {
                colaEspera.desencolar();
                personasAtendidas++;
            }
        }
    }

    private void mostrarEstadoMinuto(int minutoActual) {
        console.write("Minuto " + minutoActual + " - Longitud: " + colaEspera.obtenerCantidadPersonasEnCola() + " m ");
        colaEspera.mostrar();
    }

    private void mostrarResultados() {
        console.writeln("----------------------------------------");
        console.writeln("Resultados de " + tituloSimulacion + " (" + MINUTOS_TOTALES + " minutos):");
        console.writeln("Personas atendidas: " + personasAtendidas);
        console.writeln("Personas en fila al cierre: " + colaEspera.obtenerCantidadPersonasEnCola());
        if (colaEspera instanceof ColaDinamica) {
            ColaDinamica colaDinamica = (ColaDinamica) colaEspera;
            console.writeln("Capacidad inicial del arreglo: " + colaDinamica.obtenerCapacidadInicial());
            console.writeln("Capacidad final del arreglo: " + colaDinamica.obtenerCapacidadActual());
            console.writeln("Veces que se duplico el arreglo: " + colaDinamica.obtenerVecesRedimensionada());
            console.writeln("Personas que desistieron: 0 (la cola nunca se llena)");
        } else {
            console.writeln("Capacidad maxima fija: " + colaEspera.obtenerCapacidadMaxima());
            console.writeln("Personas que desistieron por aforo lleno: " + clientesNoEntraron);
        }
        console.writeln("========================================\n");
    }
}
