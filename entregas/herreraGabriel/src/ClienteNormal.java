public class ClienteNormal extends Persona {

    public ClienteNormal(String id) {
        super("Norm-" + id);
    }

    @Override
    public boolean tienePrioridad() {
        return false;
    }
}