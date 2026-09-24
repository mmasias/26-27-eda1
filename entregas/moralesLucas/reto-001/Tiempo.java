public class Tiempo {

    private final int DURACION_SIMULACION = 120;

    private int minutoActual;

    public Tiempo() {
        minutoActual = 0;
    }

    public void avanzar() {
        minutoActual++;
    }

    public int obtenerMinuto() {
        return minutoActual;
    }

    public boolean haFinalizado() {
        return minutoActual >= DURACION_SIMULACION;
    }
}