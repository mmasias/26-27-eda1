public class Cliente {

    private final int minutoLlegada;
    private final boolean preferente;

    public Cliente(int minutoLlegada, boolean preferente) {
        this.minutoLlegada = minutoLlegada;
        this.preferente = preferente;
    }

    public boolean esPreferente() {
        return preferente;
    }

    public int minutosEsperando(int minutoActual) {
        return minutoActual - minutoLlegada;
    }
}