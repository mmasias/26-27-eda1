public class filaExtended {

    static final int TOTAL_MINUTOS    = 120;
    static final double PROB_LLEGADA  = 0.6;
    static final double PROB_APERTURA = 0.4;
    static final int MAX_FILA         = 200;

    static final int MINUTO_INICIO_REGLAS = 20;
    static final int MINUTOS_ABURRIDO     = 8;
    static final double PROB_ABURRIRSE    = 0.30;
    static final int INTERVALO_ABURRIDO   = 5;

    static final int FILA_MAXIMA          = 30;
    static final int FILA_AVISO_PARLANTE  = 25;
    static final int INTERVALO_PARLANTE   = 15;

    static final double PROB_PREFERENTE   = 0.10;
    static final double PROB_COLADO       = 0.15;
    static final double PROB_ENTREGA      = 0.05;
    static final double PROB_DESISTIR     = 0.50;

    static int[] filaMinutoLlegada = new int[MAX_FILA];
    static boolean[] filaPreferente = new boolean[MAX_FILA];

    static int inicio = 0;
    static int fin    = 0;

    static int atendidas      = 0;
    static int aburridos      = 0;
    static int desistieron    = 0;
    static int colados        = 0;
    static int preferentes    = 0;

    static boolean encolarAlFinal(int minuto, boolean esPreferente) {
        if (fin >= MAX_FILA) return false;
        filaMinutoLlegada[fin] = minuto;
        filaPreferente[fin]    = esPreferente;
        fin++;
        return true;
    }

    static boolean desencolarFrente() {
        if (inicio >= fin) return false;
        inicio++;
        return true;
    }

    static boolean insertarEn(int pos, int minuto, boolean esPreferente) {
        if (fin >= MAX_FILA) return false;
        if (pos < inicio) pos = inicio;
        if (pos > fin)    pos = fin;

        for (int i = fin; i > pos; i--) {
            filaMinutoLlegada[i] = filaMinutoLlegada[i - 1];
            filaPreferente[i]    = filaPreferente[i - 1];
        }
        filaMinutoLlegada[pos] = minuto;
        filaPreferente[pos]    = esPreferente;
        fin++;
        return true;
    }

    static boolean eliminarEn(int pos) {
        if (pos < inicio || pos >= fin) return false;
        for (int i = pos; i < fin - 1; i++) {
            filaMinutoLlegada[i] = filaMinutoLlegada[i + 1];
            filaPreferente[i]    = filaPreferente[i + 1];
        }
        fin--;
        return true;
    }

    static int tamanoFila() {
        return fin - inicio;
    }

    public static void main(String[] args) {

        System.out.println("=== SIMULACIÓN EXTENDIDA (120 minutos) ===");
        System.out.println("Min | Long | Eventos");
        System.out.println("----+------+-------------------------------------------");

        for (int minuto = 1; minuto <= TOTAL_MINUTOS; minuto++) {

            StringBuilder eventos = new StringBuilder();

            if (Math.random() < PROB_APERTURA) {
                if (desencolarFrente()) {
                    atendidas++;
                    eventos.append("Atendida. ");
                }
            }

            boolean reglasActivas = (minuto >= MINUTO_INICIO_REGLAS);

            if (reglasActivas) {

                if (minuto % INTERVALO_ABURRIDO == 0) {
                    for (int i = fin - 1; i >= inicio; i--) {
                        int tiempoEnFila = minuto - filaMinutoLlegada[i];
                        if (tiempoEnFila > MINUTOS_ABURRIDO && Math.random() < PROB_ABURRIRSE) {
                            eliminarEn(i);
                            aburridos++;
                            eventos.append("Aburrido en pos ").append(i - inicio).append(". ");
                        }
                    }
                }

                if (tamanoFila() > 0 && Math.random() < PROB_ENTREGA) {
                    eventos.append("Entrega de compras. ");
                }

                if (minuto % INTERVALO_PARLANTE == 0 && tamanoFila() > FILA_AVISO_PARLANTE) {
                    eventos.append("PARLANTE: pasen por esta caja en orden de fila. ");
                }
            }

            if (Math.random() < PROB_LLEGADA) {

                double dado = Math.random();

                if (reglasActivas && dado < PROB_COLADO) {
                    if (tamanoFila() > 0) {
                        int posConocido = inicio + (int)(Math.random() * tamanoFila());
                        insertarEn(posConocido + 1, minuto, false);
                        colados++;
                        eventos.append("Colado lícito en pos ").append(posConocido + 1 - inicio).append(". ");
                    } else {
                        encolarAlFinal(minuto, false);
                    }

                } else if (reglasActivas && dado < PROB_COLADO + PROB_PREFERENTE) {
                    int posInsercion = inicio;
                    for (int i = fin - 1; i >= inicio; i--) {
                        if (filaPreferente[i]) {
                            posInsercion = i + 1;
                            break;
                        }
                    }
                    insertarEn(posInsercion, minuto, true);
                    preferentes++;
                    eventos.append("Preferente insertado. ");

                } else {
                    if (tamanoFila() >= FILA_MAXIMA && reglasActivas) {
                        if (Math.random() < PROB_DESISTIR) {
                            desistieron++;
                            eventos.append("Desiste (fila llena). ");
                        } else {
                            encolarAlFinal(minuto, false);
                        }
                    } else {
                        encolarAlFinal(minuto, false);
                    }
                }
            }

            System.out.printf("%3d | %4d | %s%n", minuto, tamanoFila(), eventos.toString());
        }

        System.out.println("----------------------------------------");
        System.out.println("Personas atendidas     : " + atendidas);
        System.out.println("Personas en fila       : " + tamanoFila());
        System.out.println("Personas aburridas     : " + aburridos);
        System.out.println("Personas que desistieron: " + desistieron);
        System.out.println("Colados lícitos        : " + colados);
        System.out.println("Preferentes insertados : " + preferentes);
        System.out.println("----------------------------------------");
    }
}