package entregas.aguilarAdriana;

public class Papeleta {
    private int id;
    private String nombreEscrito;

    public Papeleta(int id, String nombreEscrito) {
        this.id = id;
        this.nombreEscrito = nombreEscrito;
    }

    public int obtenerId() {
        return this.id;
    }

    public String obtenerNombreEscrito() {
        return this.nombreEscrito;
    }

    public boolean estaEnBlanco() {
        return this.nombreEscrito == null || this.nombreEscrito.isEmpty();
    }
}
