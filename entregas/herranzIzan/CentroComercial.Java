import java.util.Random;

public class CentroComercial {

    public static final int MAX_POSICIONES = 30;
    public static final int UMBRAL_AVISO = 25;
    public static final double P_DESISTE = 0.5;
 
    private final String nombre;
    private final Fila fila;
    private final Caja caja;
 
    public CentroComercial(String nombre) {
        this.nombre = nombre;
        this.fila = new Fila();
        this.caja = new Caja(1);
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public Fila getFila() {
        return fila;
    }
 
    public Caja getCaja() {
        return caja;
    }

    public boolean filaLlena() {
        return fila.tamano() >= MAX_POSICIONES;
    }

    public boolean personaDesiste(Random rnd) {
        return filaLlena() && rnd.nextDouble() < P_DESISTE;
    }
    
    public boolean debeAvisar() {
        return fila.tamano() > UMBRAL_AVISO;
    }
 
    public String anuncio(int minuto) {
        return "[min " + minuto + "] " + nombre
                + " - Parlantes: \"pasen por esta caja en orden de fila\"";
    }
}