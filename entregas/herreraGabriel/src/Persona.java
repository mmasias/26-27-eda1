public abstract class Persona {
    protected int tiempoEnFila;
    protected String id;

    public Persona(String id) {
        this.id = id;
        this.tiempoEnFila = 0;
    }

    public void esperarUnMinuto() {
        this.tiempoEnFila++;
    }

    public int getTiempoEnFila() {
        return this.tiempoEnFila;
    }
    
    public String getId() {
        return this.id;
    }

    public abstract boolean tienePrioridad();
}