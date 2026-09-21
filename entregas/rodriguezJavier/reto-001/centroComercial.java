public class CentroComercial {
    private Cola cola;
    private Caja[] cajas;
    private Tiempo tiempo;
    private Console console;
    private boolean llegaClienteEsteMinuto;
    private final double PROBABILIDAD_LLEGADA = 0.6;
    private final double PROBABILIDAD_APERTURA = 0.4;

    public CentroComercial() {
        this.console = new Console();
        this.cola = new Cola();
        this.cajas = new Caja[5];
        for (int i = 0; i < cajas.length; i++) {
            cajas[i] = new Caja(i + 1);
        }
        cajas[0] = new CajaExpress(1);
        cajas[4] = new CajaExpress(5);
        this.tiempo = new Tiempo();
    }

    public void simular() {
        console.writeln("--- INICIO DE LA SIMULACION ---");
        while (!tiempo.haTerminado()) {
            tiempo.avanzarMinuto();
            int minuto = tiempo.obtenerMinutoActual();
            cola.avanzarMinuto();
            this.procesarLlegadaCliente(minuto);
            this.asignarClientesACajas();
            this.procesarAtencionCajas();
            this.procesarReglasExtendidas(minuto);
            this.mostrarEstado(minuto);
        }
        this.mostrarResumen();
    }

    private void mostrarResumen() {
        int totalAtendidos = 0;
        int totalItems = 0;
        for (int i = 0; i < cajas.length; i++) {
            totalAtendidos = totalAtendidos + cajas[i].obtenerPersonasAtendidas();
            totalItems = totalItems + cajas[i].obtenerItemsVendidos();
        }
        console.writeln();
        console.writeln("--- RESUMEN FINAL ---");
        console.writeln("Personas atendidas: " + totalAtendidos);
        console.writeln("Personas en cola al cierre: " + cola.obtenerCantidadPersonasEnCola());
        console.writeln("Items vendidos: " + totalItems);
    }

    private void procesarReglasExtendidas(int minuto) {
        if (minuto >= 20) {
            if (minuto % 5 == 0) {
                cola.eliminarAburridos();
            }
            if (minuto % 15 == 0 && cola.obtenerCantidadPersonasEnCola() > 25) {
                console.writeln("Parlantes: Pasen por esta caja en orden de fila.");
            }
            if (Math.random() < 0.05) {
                console.writeln("Evento: alguien entrega sus compras a otra persona en la fila.");
            }
        }
    }

    private void mostrarEstado(int minuto) {
        console.writeln("Minuto " + minuto + ": Longitud de la fila = "
                + cola.obtenerCantidadPersonasEnCola() + " metros.");
    }

    private void procesarLlegadaCliente(int minuto) {
        llegaClienteEsteMinuto = Math.random() < PROBABILIDAD_LLEGADA;
        if (llegaClienteEsteMinuto) {
            boolean esPreferente = false;
            int idConocido = -1;

            if (minuto >= 20) {
                if (Math.random() < 0.1) {
                    esPreferente = true;
                } else if (Math.random() < 0.05) {
                    if (cola.hayClientes()) {
                        Cliente conocido = cola.primero();
                        idConocido = conocido.getId();
                    }
                }
            }

            Cliente nuevo = new Cliente(5, esPreferente, idConocido);
            cola.añadirCliente(nuevo);
        }
    }

    private void asignarClientesACajas() {
        for (int i = 0; i < cajas.length; i++) {
            if (cajas[i].estaLibre() && cola.hayClientes()
                    && cajas[i].puedeAtender(cola.primero())) {
                Cliente siguiente = cola.quitarCliente();
                cajas[i].asignar(siguiente);
            }
        }
    }

    private void procesarAtencionCajas() {
        for (int i = 0; i < cajas.length; i++) {
            cajas[i].avanzarAtencion();
        }
    }
}