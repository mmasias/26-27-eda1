public class Cliente {
    private int id;
    private int minutoLlegada;
    private boolean tienePreferencia;

    public Cliente(int id, int minutoLlegada, boolean tienePreferencia) {
        this.id = id;
        this.minutoLlegada = minutoLlegada;
        this.tienePreferencia = tienePreferencia;
    }

    public int obtenerId() { return id; }
    public int obtenerMinutoLlegada() { return minutoLlegada; }
    public boolean tienePreferencia() { return tienePreferencia; }
}