public class Tiempo {
    private int minutoActual;

    public Tiempo() {
        this.minutoActual = 1;
    }

    public int obtenerMinutoActual() {
        return minutoActual;
    }

    public void avanzar() {
        minutoActual++;
    }
}