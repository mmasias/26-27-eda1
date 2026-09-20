public class ClientePreferente extends Persona {
    private String motivoPrioridad;

    public ClientePreferente(String id, String motivoPrioridad) {
        super("Pref-" + id);
        this.motivoPrioridad = motivoPrioridad;
    }

    @Override
    public boolean tienePrioridad() {
        return true;
    }
    
    public String getMotivo() {
        return this.motivoPrioridad;
    }
}