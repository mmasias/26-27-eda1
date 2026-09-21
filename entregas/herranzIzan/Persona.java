public class Persona {
    private final int id;
    private final int minutoLlegada;
    private final boolean preferente;
    private int compras; // bolsas/compras que lleva (crece si alguien le entrega las suyas)
 
    public Persona(int id, int minutoLlegada, boolean preferente) {
        this.id = id;
        this.minutoLlegada = minutoLlegada;
        this.preferente = preferente;
        this.compras = 1;
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
 
    public int getCompras() {
        return compras;
    }
 
    public void recibirCompras(int cantidad) {
        this.compras += cantidad;
    }

    public int minutosEnFila(int minutoActual) {
        return minutoActual - minutoLlegada;
    }
 
    @Override
    public String toString() {
        return "P" + id + (preferente ? "*" : "");
    }
}
 
    