public class CentroComercial {

    private static final double PROBABILIDAD_LLEGADA = 0.6;
    private static final double PROBABILIDAD_PREFERENTE = 0.2;

    private static final int DURACION_SIMULACION = 120;
    private static final int MINUTO_INICIO_NUEVAS_REGLAS = 20;

    private static final int MAX_PRODUCTOS = 10;

    private static final int INTERVALO_AVISO = 15;
    private static final int CLIENTES_AVISO = 25;

    public boolean llegaCliente() {
        return Math.random() < PROBABILIDAD_LLEGADA;
    }

    public static void main(String[] args) throws Exception {

        CentroComercial centro = new CentroComercial();
        Consola console = new Consola();

        Fila fila = new Fila();
        Cajas cajas = new Cajas();

        int atendidos = 0;

        for (int minuto = 1; minuto <= DURACION_SIMULACION; minuto++) {

            console.writeln();
            console.writeln("MINUTO " + minuto);
            console.writeln("---------------------------------------------");

            cajas.abrirCaja();
            cajas.cerrarCaja(fila);

            if (centro.llegaCliente()) {

                int productos = (int) (Math.random() * MAX_PRODUCTOS) + 1;

                Cliente cliente;

                if (Math.random() < PROBABILIDAD_PREFERENTE) {

                    cliente = new ClientePreferente(productos);

                    if (minuto >= MINUTO_INICIO_NUEVAS_REGLAS) {
                        fila.encolarPreferente(cliente);
                    } else {
                        fila.encolar(cliente);
                    }

                } else {

                    cliente = new ClienteNormal(productos);

                    fila.encolar(cliente);
                }
            }

            if (minuto >= MINUTO_INICIO_NUEVAS_REGLAS) {

                fila.aumentarTiempoClientes();
                fila.eliminarClientesAburridos();

                if (minuto % INTERVALO_AVISO == 0
                        && fila.getNumClientes() > CLIENTES_AVISO) {

                    console.writeln(
                            "pasen por esta caja en orden de fila");
                }
            }

            while (cajas.hayCajaLibre() && fila.getNumClientes() > 0) {

                Cliente cliente = fila.desencolar();

                cajas.meterCliente(cliente);
            }

            int nuevosAtendidos = cajas.atender();

            atendidos += nuevosAtendidos;

            console.writeln(
                    "Longitud de la fila: "
                    + fila.getNumClientes()
                    + " metros");

            console.writeln(fila);
            console.writeln(cajas);
        }

        console.writeln();
        console.writeln("---------------------------------------------");
        console.writeln("FIN DE LA SIMULACIÓN");
        console.writeln("Personas atendidas: " + atendidos);
        console.writeln(
                "Personas en la fila: " + fila.getNumClientes());
        console.writeln(
                "Longitud final de la fila: "
                + fila.getNumClientes()
                + " metros");
    }
}
