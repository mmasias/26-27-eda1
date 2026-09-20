package entregas.PaguagaJavier.Scr;

public class Persona {
    int identificadorUnico;
    int minutosTranscurridosEnFila;
    boolean tieneDerechoPreferente;

    public Persona(int identificadorUnico, boolean tieneDerechoPreferente) {
        this.identificadorUnico = identificadorUnico;
        this.minutosTranscurridosEnFila = 0;
        this.tieneDerechoPreferente = tieneDerechoPreferente;
    }
}
