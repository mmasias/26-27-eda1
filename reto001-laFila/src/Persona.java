public class Persona {

    private final int id;
    private final int minutoLlegada;
    private final boolean preferente;

    public Persona(int id, int minutoLlegada, boolean preferente) {
        this.id = id;
        this.minutoLlegada = minutoLlegada;
        this.preferente = preferente;
    }

    public int getId() {
        return id;
    }

    public int getMinutoLlegada() {
        return minutoLlegada;
    }

    public boolean isPreferente() {
        return preferente;
    }

    public int tiempoEspera(int minutoActual) {
        return minutoActual - minutoLlegada;
    }

    @Override
    public String toString() {
        return "Persona#" + id + (preferente ? " (preferente)" : "");
    }
}