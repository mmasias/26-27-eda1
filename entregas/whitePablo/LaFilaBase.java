public class LaFilaBase {

    private static final int TOTAL_MINUTOS      = 240;
    private static final double PROB_LLEGADA    = 0.6;
    private static final double PROB_APERTURA   = 0.4;
    private static final int CAPACIDAD_MAXIMA   = 240;

    private static int[] fila = new int[CAPACIDAD_MAXIMA];
    private static int indiceInicio = 0;
    private static int indiceFin    = 0;

    private static int personasAtendidas = 0;

    public static void main(String[] args) {

        System.out.println("=== SIMULACIÓN RETO BASE (240 minutos) ===");

        for (int minuto = 1; minuto <= TOTAL_MINUTOS; minuto++) {

            if (llegaPersona()) {
                encolarAlFinal(minuto);
            }

            if (seAbreCaja() && hayPersonasEnFila()) {
                atenderSiguiente();
            }
        }

        System.out.println("----------------------------------------");
        System.out.println("Personas atendidas : " + personasAtendidas);
        System.out.println("Personas en fila   : " + tamanoFila());
        System.out.println("----------------------------------------");
    }

    private static boolean llegaPersona() {
        return Math.random() < PROB_LLEGADA;
    }

    private static boolean seAbreCaja() {
        return Math.random() < PROB_APERTURA;
    }

    private static boolean hayPersonasEnFila() {
        return indiceInicio < indiceFin;
    }

    private static int tamanoFila() {
        return indiceFin - indiceInicio;
    }

    private static void encolarAlFinal(int minuto) {
        if (!hayEspacio()) {
            return;
        }
        fila[indiceFin] = minuto;
        indiceFin++;
    }

    private static void atenderSiguiente() {
        indiceInicio++;
        personasAtendidas++;
    }

    private static boolean hayEspacio() {
        return indiceFin < CAPACIDAD_MAXIMA;
    }
}