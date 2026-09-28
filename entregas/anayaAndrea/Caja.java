import java.util.Random;

public class Caja {

    private final double probabilidadApertura;
    private final Random azar;

    public Caja(double probabilidadApertura, Random azar) {
        this.probabilidadApertura = probabilidadApertura;
        this.azar = azar;
    }

    public boolean seAbre() {
        return azar.nextDouble() < probabilidadApertura;
    }
}