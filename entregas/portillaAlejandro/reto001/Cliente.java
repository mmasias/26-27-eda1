public class Cliente {

    private int minutoLlegada;

    public Cliente(int minuto) {
        minutoLlegada = minuto;
    }

    public int minutosEnCola(int minutoActual) {
        int minutos = minutoActual - minutoLlegada;
        return minutos;
    }
}