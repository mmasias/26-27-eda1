package entregas.moraDaniel;

public class Persona {

    public static final int SIN_PRIORIDAD = 0;
    public static final int CON_PRIORIDAD_PREFERENTE = 1;

    private final int identificador;
    private final int nivelDePrioridad;
    private final int minutoDeLlegada;

    public Persona(int identificador, int nivelDePrioridad, int minutoDeLlegada) {
        this.identificador = identificador;
        this.nivelDePrioridad = nivelDePrioridad;
        this.minutoDeLlegada = minutoDeLlegada;
    }

    public int getIdentificador() {
        return identificador;
    }

    public int getNivelDePrioridad() {
        return nivelDePrioridad;
    }

    public int getMinutoDeLlegada() {
        return minutoDeLlegada;
    }

    public boolean tienePrioridadPreferente() {
        return nivelDePrioridad == CON_PRIORIDAD_PREFERENTE;
    }

    public String toString() {
        if (tienePrioridadPreferente()) {
            return "Persona" + identificador + "(preferente)";
        }
        return "Persona" + identificador;
    }
}