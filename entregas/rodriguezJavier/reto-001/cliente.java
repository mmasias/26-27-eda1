public class Cliente {
    private static int contadorGlobal = 1;
    
    private int id;
    private int tiempoEnFila;
    private int items;
    private boolean esPreferente;
    private int idConocido;

    public Cliente(int items, boolean esPreferente, int idConocido) {
        this.id = contadorGlobal;
        contadorGlobal++;
        this.tiempoEnFila = 0;
        this.items = items;
        this.esPreferente = esPreferente;
        this.idConocido = idConocido;
    }

    public int getId() {
        return id;
    }

    public int getTiempoEnFila() {
        return tiempoEnFila;
    }

    public void aumentarTiempo() {
        this.tiempoEnFila = this.tiempoEnFila + 1;
    }

    public int obtenerItems() {
        return items;
    }

    public boolean esPreferente() {
        return esPreferente;
    }

    public int getIdConocido() {
        return idConocido;
    }

    public boolean estaAburrido() {
        return this.tiempoEnFila > 8;
    }

    public String toString() {
        return "Cliente[" + id + "]";
    }
}