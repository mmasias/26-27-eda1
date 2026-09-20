public abstract class Cliente {

    protected int numProductos;
    protected Console console;
    protected int tiempo = 0;

    public Cliente(int numProductos) {
        this.numProductos = numProductos;
        console = new Console();
    }

    public int numProductos() {
        return numProductos;
    }

    public void aumentarTiempo() {
        tiempo++;
    }

    public boolean clienteSeMarcha() {
        if (tiempo > 8 && tiempo % 5 == 3) {

            int seVa = (int) (Math.random() * 101);

            if (seVa <= 30) {
                return true;
            }
        }

        return false;
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