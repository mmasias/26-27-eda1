public class Tiempo {

    public static final int MINUTO_INICIO_REGLAS = 20;
    public static final int PERIODO_ABURRIMIENTO = 5;
    public static final int PERIODO_AVISO = 15;

    private final int duracion;
    private final boolean extendido;
    private int minuto;

    public Tiempo(int duracion, boolean extendido) {
        this.duracion = duracion;
        this.extendido = extendido;
        this.minuto = 0;
    }
 
    public void avanzar() {
        minuto++;
    }
 
    public int getMinuto() {
        return minuto;
    }
 
    public int getDuracion() {
        return duracion;
    }
 
    public boolean haTerminado() {
        return minuto >= duracion;
    }

    public boolean reglasNuevasActivas() {
        return extendido && minuto >= MINUTO_INICIO_REGLAS;
    }

    public boolean tocaAburrimiento() {
        return minuto % PERIODO_ABURRIMIENTO == 0;
    }

    public boolean tocaAviso() {
        return minuto % PERIODO_AVISO == 0;
    }
}