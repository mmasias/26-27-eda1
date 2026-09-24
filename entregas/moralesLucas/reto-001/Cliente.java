public class Cliente {

    private int minutoLlegada;
    private boolean atencionPreferente;
    private boolean tieneCompras;

    public Cliente(int minutoLlegada, boolean atencionPreferente) {
        this.minutoLlegada = minutoLlegada;
        this.atencionPreferente = atencionPreferente;
        this.tieneCompras = true;
    }

    public int minutosEnFila(int minutoActual) {
        return minutoActual - minutoLlegada;
    }

    public boolean tieneAtencionPreferente() {
        return atencionPreferente;
    }

    public boolean tieneCompras() {
        return tieneCompras;
    }

    public void entregarCompras() {
        tieneCompras = false;
    }
}