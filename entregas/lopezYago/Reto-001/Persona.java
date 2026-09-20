package entregas.lopezYago.Reto-001;

public abstract class Persona {

    private String nombre;
    private int minutoLlegada;

    public Persona(String nombre, int minutoLlegada) {
        this.nombre = nombre;
        this.minutoLlegada = minutoLlegada;
    }

    public String getNombre() {
        return nombre;
    }

    public int getMinutoLlegada() {
        return minutoLlegada;
    }

    public int minutosEsperando(int minutoActual) {
        return minutoActual - minutoLlegada;
    }

    public abstract boolean esPreferente();

    @Override
    public String toString() {
        return nombre + (esPreferente() ? " [preferente]" : "");
    }
}