public class Tiempo {
    private int minuto;

    public Tiempo() {
        minuto = 0;
    }

    public void avanzar() {
        minuto = minuto + 1;
    }

    public boolean haFinalizado() {
        final int DURACION = 240;

        return minuto >= DURACION;
    }

    public int minuto() {
        return minuto;
    }
}
