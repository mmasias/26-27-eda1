import java.util.Random;

public class Caja {
    public static final double P_APERTURA = 0.4;
 
    private final int id;
    private int atendidas;
 
    public Caja(int id) {
        this.id = id;
        this.atendidas = 0;
    }
 
    public int getId() {
        return id;
    }
 
    public int getAtendidas() {
        return atendidas;
    }
    
    public boolean seAbre(Random rnd) {
        return rnd.nextDouble() < P_APERTURA;
    }

    public Persona atender(Fila fila) {
        Persona p = fila.atenderFrente();
        if (p != null) {
            atendidas++;
        }
        return p;
    }

}