public class ClienteNormal extends Cliente {

    public ClienteNormal(int numProductos) {
        super(numProductos);
    }

    @Override
    protected boolean esPreferente() {
        return false;
    }
}