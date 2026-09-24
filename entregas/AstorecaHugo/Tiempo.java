
public class Tiempo {

    private final double HORA_APERTURA = 10.0;
    private final double HORA_CIERRE = 14.0;
    private final double MINUTO = 0.0167;
    private double horaActual;
    private Console console;

    public Tiempo(){
        horaActual = HORA_APERTURA;
        console = new Console();
    }

    public void AvanzarTiempo(){
        horaActual = horaActual + MINUTO;
    }

    public boolean haFinalizado(){
        return horaActual >= HORA_CIERRE;
    }

    private String horaHumana() {
        int hora = (int) horaActual;
        int minutos =(int) ((horaActual - hora)*60);
        return hora + ":" + minutos;
}
