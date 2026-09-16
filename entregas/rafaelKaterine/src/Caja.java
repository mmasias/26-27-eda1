import java.util.Random;

public class Caja {

    private static final Random random = new Random();  

    public boolean estaDisponible(){
        return random.nextDouble() < 0.40;
    }

}