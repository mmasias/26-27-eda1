public abstract class Cliente {

    private static final int TIEMPO_MINIMO_ABANDONO = 8;
    private static final int INTERVALO_ABANDONO = 5;
    private static final int PROBABILIDAD_ABANDONO = 30;
    private static final int PORCENTAJE_MAXIMO = 100;

    protected int numProductos;
    protected Consola console;
    protected int tiempo = 0;

    public Cliente(int numProductos) {
        this.numProductos = numProductos;
        console = new Consola();
    }

    public int numProductos() {
        return numProductos;
    }

    public void aumentarTiempo() {
        tiempo++;
    }

    public boolean clienteSeMarcha() {

        if (tiempo > TIEMPO_MINIMO_ABANDONO
                && tiempo % INTERVALO_ABANDONO == 3) {

            int seVa = (int) (Math.random() * (PORCENTAJE_MAXIMO + 1));

            if (seVa <= PROBABILIDAD_ABANDONO) {
                return true;
            }
        }

        return false;
    }

    public void entregarCompras(Cliente cliente) {
        cliente.numProductos += numProductos;
        numProductos = 0;
    }

    protected abstract boolean esPreferente();

    @Override
    public String toString() {

        if (esPreferente()) {
            return "[CP]";
        } else {
            return "[CN]";
        }
    }
}