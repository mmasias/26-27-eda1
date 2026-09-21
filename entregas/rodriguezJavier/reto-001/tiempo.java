public class Tiempo {
    private int minutoActual;
    private final int MINUTOS_TOTALES = 120;
    private final int HORA_INICIO = 9;
    private Console console;

    public Tiempo() {
        this.minutoActual = 0;
        this.console = new Console();
    }

    public void avanzarMinuto() {
        this.minutoActual = this.minutoActual + 1;
    }

    public boolean haTerminado() {
        return this.minutoActual >= MINUTOS_TOTALES;
    }

    public int obtenerMinutoActual() {
        return this.minutoActual;
    }

    private String formatearHora() {
        int hora = HORA_INICIO + (this.minutoActual / 60);
        int minutos = this.minutoActual % 60;
        return hora + ":" + minutos;
    }

    public void imprimir(boolean llegaClienteEsteMinuto) {
        console.write(formatearHora() + " ");
        if (llegaClienteEsteMinuto) {
            console.writeln("llego un cliente");
        } else {
            console.writeln("no llego un cliente");
        }
    }
}