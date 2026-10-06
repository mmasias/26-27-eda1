public class CajaExpress extends Caja {

    private Console console;

    public CajaExpress(int numero) {
        super(numero);
        console = new Console();
    }

    @Override
    public void mostrar() {
        console.write("CajaE[" + numero + "] ");
        console.writeln("[:]");
    }

    public boolean puedeAtender(Cliente cliente) {
        return true;
    }

}