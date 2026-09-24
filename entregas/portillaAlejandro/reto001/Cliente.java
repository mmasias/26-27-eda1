public class Cliente {
    private int minutoLlegada;
    private Cliente siguiente;

    public Cliente(int minuto) {
        minutoLlegada = minuto;
        siguiente = null;
    }

    public int minutosEnCola(int minutoActual) {
        int minutos = minutoActual - minutoLlegada;
        return minutos;
    }

    public Cliente obtenerSiguiente() {
        return siguiente;
    }

    public void establecerSiguiente(Cliente cliente) {
        siguiente = cliente;
    }
}