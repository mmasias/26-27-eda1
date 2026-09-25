public class Tiempo {
    private final double HORA_APERTURA = 10.0;
    private final double HORA_CIERRE = 11.0;
    private final double MINUTO = 0.0167;
    private double horaActual;
    private int minutoActual;
    private Console console;

    public Tiempo() {
        horaActual = HORA_APERTURA;
        minutoActual = 0;
        console = new Console();
    }

    public void avanzar() {
        horaActual = horaActual + MINUTO;
        minutoActual++;
    }

    public int obtenerMinuto() {
        return minutoActual;
    }

    public boolean haFinalizado() {
        return horaActual >= HORA_CIERRE;
    }

    private String horaHumana() {
        int hora = (int) horaActual;
        int minutos = (int) ((horaActual - hora) * 60);
        return hora + ":" + minutos;
    }

    public void mostrar(boolean haLlegadoCliente) {
        console.write("Hora: " + horaHumana());

        if (haLlegadoCliente) {
            console.writeln(" Ha llegado un cliente");
        } else {
            console.writeln(" No ha llegado ningún cliente");
        }
    }
}
