public class LaFilaExtendido {

    private static final int TOTAL_MINUTOS           = 120;
    private static final int MINUTO_INICIO_REGLAS    = 20;

    private static final double PROB_LLEGADA         = 0.6;
    private static final double PROB_APERTURA        = 0.4;
    private static final double PROB_PREFERENTE      = 0.10;
    private static final double PROB_COLADO          = 0.15;
    private static final double PROB_ENTREGA         = 0.05;
    private static final double PROB_DESISTIR        = 0.50;
    private static final double PROB_ABURRIRSE       = 0.30;

    private static final int MINUTOS_ABURRIDO        = 8;
    private static final int INTERVALO_ABURRIDO      = 5;
    private static final int INTERVALO_PARLANTE      = 15;
    private static final int FILA_AVISO_PARLANTE     = 25;
    private static final int FILA_MAXIMA             = 30;
    private static final int CAPACIDAD_MAXIMA        = 200;

    private static int[]     filaMinutoLlegada = new int[CAPACIDAD_MAXIMA];
    private static boolean[] filaEsPreferente  = new boolean[CAPACIDAD_MAXIMA];
    private static int indiceInicio = 0;
    private static int indiceFin    = 0;

    private static int personasAtendidas   = 0;
    private static int personasAburridas   = 0;
    private static int personasDesistieron = 0;
    private static int coladosLicito       = 0;
    private static int preferentesInsertados = 0;

    public static void main(String[] args) {

        System.out.println("=== SIMULACIÓN EXTENDIDA (120 minutos) ===");
        System.out.printf("%3s | %4s | %s%n", "Min", "Long", "Eventos");
        System.out.println("----+------+-------------------------------------------");

        for (int minuto = 1; minuto <= TOTAL_MINUTOS; minuto++) {

            StringBuilder eventos = new StringBuilder();

            atenderSiSeAbreCaja(eventos);

            boolean reglasActivas = minuto >= MINUTO_INICIO_REGLAS;

            if (reglasActivas) {
                procesarAburrimiento(minuto, eventos);
                procesarEntregaDeCompras(eventos);
                procesarParlante(minuto, eventos);
            }

            if (llegaPersona()) {
                procesarLlegada(minuto, reglasActivas, eventos);
            }

            System.out.printf("%3d | %4d | %s%n", minuto, tamanoFila(), eventos.toString());
        }

        reportarResultadosFinales();
    }

    private static boolean llegaPersona() {
        return Math.random() < PROB_LLEGADA;
    }

    private static void procesarLlegada(int minuto, boolean reglasActivas, StringBuilder eventos) {
        double dado = Math.random();

        if (reglasActivas && dado < PROB_COLADO) {
            procesarColado(minuto, eventos);
        } else if (reglasActivas && dado < PROB_COLADO + PROB_PREFERENTE) {
            procesarPreferente(minuto, eventos);
        } else {
            procesarLlegadaNormal(minuto, reglasActivas, eventos);
        }
    }

    private static void procesarColado(int minuto, StringBuilder eventos) {
        if (!hayPersonasEnFila()) {
            encolarAlFinal(minuto, false);
            return;
        }
        int posicionConocido = indiceInicio + (int) (Math.random() * tamanoFila());
        insertarEn(posicionConocido + 1, minuto, false);
        coladosLicito++;
        eventos.append("Colado lícito. ");
    }

    private static void procesarPreferente(int minuto, StringBuilder eventos) {
        int posicionInsercion = calcularPosicionPreferente();
        insertarEn(posicionInsercion, minuto, true);
        preferentesInsertados++;
        eventos.append("Preferente. ");
    }

    private static void procesarLlegadaNormal(int minuto, boolean reglasActivas, StringBuilder eventos) {
        if (reglasActivas && filaSuperaLimite()) {
            if (Math.random() < PROB_DESISTIR) {
                personasDesistieron++;
                eventos.append("Desiste. ");
                return;
            }
        }
        encolarAlFinal(minuto, false);
    }

    private static void procesarAburrimiento(int minuto, StringBuilder eventos) {
        if (minuto % INTERVALO_ABURRIDO != 0) {
            return;
        }
        for (int i = indiceFin - 1; i >= indiceInicio; i--) {
            int minutosEsperando = minuto - filaMinutoLlegada[i];
            if (minutosEsperando > MINUTOS_ABURRIDO && Math.random() < PROB_ABURRIRSE) {
                eliminarEn(i);
                personasAburridas++;
                eventos.append("Aburrido. ");
            }
        }
    }

    private static void procesarEntregaDeCompras(StringBuilder eventos) {
        if (hayPersonasEnFila() && Math.random() < PROB_ENTREGA) {
            eventos.append("Entrega compras. ");
        }
    }

    private static void procesarParlante(int minuto, StringBuilder eventos) {
        if (minuto % INTERVALO_PARLANTE == 0 && tamanoFila() > FILA_AVISO_PARLANTE) {
            eventos.append("PARLANTE. ");
        }
    }

    private static void atenderSiSeAbreCaja(StringBuilder eventos) {
        if (!seAbreCaja() || !hayPersonasEnFila()) {
            return;
        }
        atenderSiguiente();
        eventos.append("Atendida. ");
    }

    private static boolean seAbreCaja() {
        return Math.random() < PROB_APERTURA;
    }

    private static void atenderSiguiente() {
        indiceInicio++;
        personasAtendidas++;
    }

    private static void encolarAlFinal(int minuto, boolean esPreferente) {
        if (!hayEspacio()) {
            return;
        }
        filaMinutoLlegada[indiceFin] = minuto;
        filaEsPreferente[indiceFin]  = esPreferente;
        indiceFin++;
    }

    private static void insertarEn(int posicion, int minuto, boolean esPreferente) {
        if (!hayEspacio()) {
            return;
        }
        int posicionAjustada = ajustarPosicion(posicion);
        desplazarHaciaAtras(posicionAjustada);
        filaMinutoLlegada[posicionAjustada] = minuto;
        filaEsPreferente[posicionAjustada]  = esPreferente;
        indiceFin++;
    }

    private static void eliminarEn(int posicion) {
        if (posicion < indiceInicio || posicion >= indiceFin) {
            return;
        }
        desplazarHaciaAdelante(posicion);
        indiceFin--;
    }

    private static void desplazarHaciaAtras(int desde) {
        for (int i = indiceFin; i > desde; i--) {
            filaMinutoLlegada[i] = filaMinutoLlegada[i - 1];
            filaEsPreferente[i]  = filaEsPreferente[i - 1];
        }
    }

    private static void desplazarHaciaAdelante(int desde) {
        for (int i = desde; i < indiceFin - 1; i++) {
            filaMinutoLlegada[i] = filaMinutoLlegada[i + 1];
            filaEsPreferente[i]  = filaEsPreferente[i + 1];
        }
    }

    private static int ajustarPosicion(int posicion) {
        if (posicion < indiceInicio) return indiceInicio;
        if (posicion > indiceFin)    return indiceFin;
        return posicion;
    }

    private static int calcularPosicionPreferente() {
        for (int i = indiceFin - 1; i >= indiceInicio; i--) {
            if (filaEsPreferente[i]) {
                return i + 1;
            }
        }
        return indiceInicio;
    }

    private static boolean hayPersonasEnFila() {
        return indiceInicio < indiceFin;
    }

    private static boolean hayEspacio() {
        return indiceFin < CAPACIDAD_MAXIMA;
    }

    private static boolean filaSuperaLimite() {
        return tamanoFila() >= FILA_MAXIMA;
    }

    private static int tamanoFila() {
        return indiceFin - indiceInicio;
    }

    private static void reportarResultadosFinales() {
        System.out.println("----------------------------------------");
        System.out.println("Personas atendidas       : " + personasAtendidas);
        System.out.println("Personas en fila         : " + tamanoFila());
        System.out.println("Personas aburridas       : " + personasAburridas);
        System.out.println("Personas que desistieron : " + personasDesistieron);
        System.out.println("Colados lícitos          : " + coladosLicito);
        System.out.println("Preferentes insertados   : " + preferentesInsertados);
        System.out.println("----------------------------------------");
    }
}