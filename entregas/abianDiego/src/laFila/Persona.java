package laFila;

class Persona {
    private final int MINUTOS_DE_PACIENCIA = 8;
    private final double PROBABILIDAD_DE_ABURRIRSE = 0.3;
    private final double PROBABILIDAD_DE_DESISTIR_ANTE_UNA_FILA_LARGA = 0.5;

    private int minutoDeLlegada;
    private Circunstancia circunstancia;

    public Persona(int minutoDeLlegada) {
        this(minutoDeLlegada, Circunstancia.NINGUNA);
    }

    public Persona(int minutoDeLlegada, Circunstancia circunstancia) {
        this.minutoDeLlegada = minutoDeLlegada;
        this.circunstancia = circunstancia;
    }

    public Circunstancia getCircunstancia() {
        return circunstancia;
    }

    public boolean seAburre(int minutoActual) {
        boolean llevaDemasiadoTiempoEsperando = minutoActual - minutoDeLlegada > MINUTOS_DE_PACIENCIA;
        return llevaDemasiadoTiempoEsperando && Math.random() < PROBABILIDAD_DE_ABURRIRSE;
    }

    public boolean desisteAlVerUnaFilaLarga() {
        return Math.random() < PROBABILIDAD_DE_DESISTIR_ANTE_UNA_FILA_LARGA;
    }
}
