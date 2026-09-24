public class Persona {
    private boolean embarazada;
    private boolean terceraEdad;
    private boolean discapacidad;
    private int minutoLlegada;
    private Persona conocidoEnFila;

    public Persona(int minutoLlegada, boolean embarazada, boolean terceraEdad, boolean discapacidad) {
        this.minutoLlegada = minutoLlegada;
        this.embarazada = embarazada;
        this.terceraEdad = terceraEdad;
        this.discapacidad = discapacidad;
        conocidoEnFila = null;
    }

    public Persona(int minutoLlegada, boolean embarazada, boolean terceraEdad, boolean discapacidad, Persona conocidoEnFila) {
        this.minutoLlegada = minutoLlegada;
        this.embarazada = embarazada;
        this.terceraEdad = terceraEdad;
        this.discapacidad = discapacidad;
        this.conocidoEnFila = conocidoEnFila;
    }

    public boolean tieneDerechoPreferente() {
        return embarazada || terceraEdad || discapacidad;
    }

    public int getMinutoLlegada() {
        return minutoLlegada;
    }

    public Persona getConocidoEnFila() {
        return conocidoEnFila;
    }
}