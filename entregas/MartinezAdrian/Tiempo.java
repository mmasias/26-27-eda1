public class Tiempo {
    private final double HORA_APERTURA= 10.0;
    private final double HORA_CIERRE= 14.0;
    private final double MINUTO= 0.0167;
    private double horaActual;

    private Console console;

    public Tiempo() {
        this.horaActual = HORA_APERTURA;
        this.console = new Console();
    }

    public void avanzar() {
        horaActual += MINUTO;
    }

    public boolean haFinalizado() {
        return horaActual >= HORA_CIERRE;
    }

    public void mostrar(boolean haLlegadoCliente) {
        console.write(this.horaHumana());
        console.write(" ");
        console.writeln((haLlegadoCliente ? "" : "no ") + "llego un cliente");
    }

    private String horaHumana() {
        int hora = (int) horaActual;
        int minutos = (int) ((horaActual - hora) * 60);
        return hora + ":" + (minutos < 10 ? "0" : "") + minutos;
    }

    public String obtenerHora() {
        return this.horaHumana();
    }
}