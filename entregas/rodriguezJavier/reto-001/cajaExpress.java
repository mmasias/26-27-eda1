public class CajaExpress extends Caja {

    public CajaExpress(int numero) {
        super(numero);
    }

    @Override
    public boolean puedeAtender(Cliente cliente) {
        return cliente.obtenerItems() <= 10;
    }

    @Override
    public void mostrar() {
        obtenerConsole().write("CajaE [" + obtenerNumero() + "] ");
        for (int i = 0; i < obtenerItemsRestantes(); i++) {
            obtenerConsole().write("[:]");
        }
        obtenerConsole().writeln();
    }
}