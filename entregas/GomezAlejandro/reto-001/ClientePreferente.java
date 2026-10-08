public class ClientePreferente extends Cliente {

    public ClientePreferente(int numProductos) {
        super(numProductos);
    }

    @Override
    protected boolean esPreferente() {
        return true;
    }
}