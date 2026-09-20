import java.util.Random;
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

    public boolean seAburre(int minutoActual) {
        int tiempoDeEspera = minutoActual - minutoDeLlegada;
        if (tiempoDeEspera >= 8 && minutoActual % 5 == 0) {
            return random.nextDouble() < 0.30;
        }
        return false;
    }

    public String perfil() {
        return (esPreferencial ? "🏥" : "👤") + id;
    }

    public boolean colarse(Fila fila) {
        if (fila.obtenerTamaño() >= 30 || fila.obtenerTamaño() == 0) {
            return false;
        }
        int personaConocida = random.nextInt(fila.obtenerTamaño());
        fila.insertarEn(personaConocida + 1, this);
        return true;
    }

    public boolean transferirCompras(Fila fila) {
        if (fila.obtenerTamaño() >= 2) {
            int posicion = random.nextInt(fila.obtenerTamaño());
            fila.eliminarEn(posicion);
            return true;
        }
        return false;
    }
}