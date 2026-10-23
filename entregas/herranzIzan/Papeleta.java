public class Papeleta {

    private String opcion;

    public Papeleta(String opcion) {
        this.opcion = opcion;
    }

    public String getOpcion() {
    return opcion;
    }

    @Override
    public String toString() {
    return opcion;
    }

}
