import java.util.Random;

public class Persona {

    public boolean esPreferencial;
    public int minutoDeLlegada;
    private static final Random random = new Random();
    public int id;

    public Persona(int minutoDeLlegada, int id) {
        this.esPreferencial = random.nextDouble() < 0.15;
        this.minutoDeLlegada = minutoDeLlegada;
        this.id = id;
    } 

    public boolean seAburre (int minutoActual) {
        int tiempoDeEspera = minutoActual - minutoDeLlegada;
        if (tiempoDeEspera >= 8 && minutoActual % 5 == 0){
            return random.nextDouble() < 0.30;
        }
        return false;
    }

    public String perfil() {
        return (esPreferencial ? "🏥" : "👤") + id;
    }




}