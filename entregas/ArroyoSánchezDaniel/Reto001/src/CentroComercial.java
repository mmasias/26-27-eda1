
public class CentroComercial {

    public boolean llegaCliente() {
        return Math.random() < 0.8;
    }

    public static void main(String[] args) throws Exception {

        CentroComercial centro = new CentroComercial();
        Console console = new Console();

        Fila fila = new Fila();
        Cajas cajas = new Cajas();

        int atendidos = 0;

        for (int minuto = 1; minuto <= 120; minuto++) {

            console.writeln();
            console.writeln("MINUTO " + minuto);
            console.writeln("---------------------------------------------");

            cajas.abrirCaja();
            cajas.cerrarCaja(fila);

            if (centro.llegaCliente()) {

                int productos = (int) (Math.random() * 10) + 1;

                Cliente cliente;

                if (Math.random() < 0.2) {
                    cliente = new ClientePreferente(productos);
                    fila.encolarPreferente(cliente);
                } else {
                    cliente = new ClienteNormal(productos);
                    fila.encolar(cliente);
                }
            }

            while (cajas.hayCajaLibre() && fila.getNumClientes() > 0) {

                Cliente cliente = fila.desencolar();

                cajas.meterCliente(cliente);
            }

            int nuevosAtendidos = cajas.atender();

            atendidos += nuevosAtendidos;

            console.writeln(fila);
            console.writeln(cajas);
            console.pause(0.5);
            console.cleanScreen();
        }

        console.writeln();
        console.writeln("---------------------------------------------");
        console.writeln("FIN DE LA SIMULACIÓN");
        console.writeln("Personas atendidas: " + atendidos);
        console.writeln("Personas en la fila: " + fila.getNumClientes());
    }
}
