public class Cliente {

    private int minutoLlegada;
    private boolean preferente;
    private Cliente siguiente;

    public Cliente(int minutoLlegada, boolean preferente) {
        this.minutoLlegada = minutoLlegada;
        this.preferente = preferente;
        this.siguiente = null;
    }

    public int minutosEnCola(int minutoActual) {
        return minutoActual - minutoLlegada;
    }

    public boolean esPreferente() {
        return preferente;
    }

    public Cliente obtenerSiguiente() {
        return siguiente;
    }

    public void establecerSiguiente(Cliente siguiente) {
        this.siguiente = siguiente;
    }
}