import java.util.Random;

public class Simulacion {

    static final double P_LLEGADA = 0.6;
    static final int UMBRAL_ABURRIMIENTO = 8;   
    static final double P_ABURRIRSE = 0.3;
 
    static final double P_PREFERENTE = 0.10;
    static final double P_COLADO = 0.05;
    static final double P_ENTREGA = 0.05;
 
    private static final int LLEGADA = 0;
    private static final int CAJA = 1;
    private static final int PREFERENTE = 2;
    private static final int COLADO = 3;
    private static final int ENTREGA = 4;
    private static final int ABURRIMIENTO = 5;
 
    private final Random rnd;
    private final Tiempo tiempo;
    private final CentroComercial cccf;
    private int siguienteId = 1;

    private int aburridos, desistieron, preferentes, colados, entregas, avisos;
 
    public Simulacion(int duracionMinutos, boolean extendido, long semilla) {
        this.rnd = new Random(semilla);
        this.tiempo = new Tiempo(duracionMinutos, extendido);
        this.cccf = new CentroComercial("CCCF");
    }

    public int[] ejecutar() {
        int[] longitudes = new int[tiempo.getDuracion()];
 
        while (!tiempo.haTerminado()) {
            tiempo.avanzar();
            int minuto = tiempo.getMinuto();
            boolean reglasNuevas = tiempo.reglasNuevasActivas();
 
            for (int accion : accionesDelMinuto(reglasNuevas)) {
                ejecutarAccion(accion, minuto, reglasNuevas);
            }
 
            if (reglasNuevas && tiempo.tocaAviso() && cccf.debeAvisar()) {
                avisos++;
                System.out.println(cccf.anuncio(minuto));
            }
 
            longitudes[minuto - 1] = cccf.getFila().longitudMetros();
        }
        return longitudes;
    }

    private int[] accionesDelMinuto(boolean reglasNuevas) {
        if (!reglasNuevas) {
            return new int[] {LLEGADA, CAJA};
        }
        int[] acciones = tiempo.tocaAburrimiento()
                ? new int[] {LLEGADA, CAJA, PREFERENTE, COLADO, ENTREGA, ABURRIMIENTO}
                : new int[] {LLEGADA, CAJA, PREFERENTE, COLADO, ENTREGA};
        barajar(acciones); // "en cualquier orden"
        return acciones;
    }
 
    private void ejecutarAccion(int accion, int minuto, boolean reglasNuevas) {
        Fila fila = cccf.getFila();
        switch (accion) {
            case LLEGADA:
                if (rnd.nextDouble() < P_LLEGADA && puedeIncorporarse(reglasNuevas)) {
                    fila.agregarAlFinal(nuevaPersona(minuto, false));
                }
                break;
            case CAJA:
                if (cccf.getCaja().seAbre(rnd)) {
                    cccf.getCaja().atender(fila);
                }
                break;
            case PREFERENTE:
                if (rnd.nextDouble() < P_PREFERENTE && puedeIncorporarse(reglasNuevas)) {
                    fila.agregarPreferente(nuevaPersona(minuto, true));
                    preferentes++;
                }
                break;
            case COLADO:
                if (!fila.estaVacia() && rnd.nextDouble() < P_COLADO && puedeIncorporarse(reglasNuevas)) {
                    int conocido = rnd.nextInt(fila.tamano());
                    fila.colarDetras(nuevaPersona(minuto, false), conocido);
                    colados++;
                }
                break;
            case ENTREGA:
                if (fila.tamano() >= 2 && rnd.nextDouble() < P_ENTREGA) {
                    int dador = rnd.nextInt(fila.tamano());
                    int receptor = rnd.nextInt(fila.tamano() - 1);
                    if (receptor >= dador) {
                        receptor++; // garantiza receptor != dador
                    }
                    fila.entregarCompras(dador, receptor);
                    entregas++;
                }
                break;
            case ABURRIMIENTO:
                aburridos += fila.retirarAburridos(minuto, UMBRAL_ABURRIMIENTO, P_ABURRIRSE, rnd);
                break;
            default:
                throw new IllegalArgumentException("Acción desconocida: " + accion);
        }
    }

    private boolean puedeIncorporarse(boolean reglasNuevas) {
        if (reglasNuevas && cccf.personaDesiste(rnd)) {
            desistieron++;
            return false;
        }
        return true;
    }
 
    private Persona nuevaPersona(int minuto, boolean preferente) {
        return new Persona(siguienteId++, minuto, preferente);
    }
 
    /** Fisher-Yates. */
    private void barajar(int[] v) {
        for (int i = v.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            int tmp = v[i];
            v[i] = v[j];
            v[j] = tmp;
        }
    }
 
    public void imprimirResumen(boolean extendido) {
        System.out.println("---- Resumen ----");
        System.out.println("Atendidas:     " + cccf.getCaja().getAtendidas());
        System.out.println("En fila:       " + cccf.getFila().tamano());
        if (extendido) {
            System.out.println("Se aburrieron: " + aburridos);
            System.out.println("Desistieron:   " + desistieron);
            System.out.println("Preferentes:   " + preferentes);
            System.out.println("Colados:       " + colados);
            System.out.println("Entregas:      " + entregas);
            System.out.println("Avisos:        " + avisos);
        }
    }
}