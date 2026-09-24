public class Simulador {
    private static final double PROB_LLEGADA = 0.60;
    private static final double PROB_ATENCION = 0.40;
    private static final double PROB_PREFERENTE = 0.15;
    private static final double PROB_COLADO = 0.20;

    private Fila fila;
    private int atendidosTotales;
    private int abandonosTotales;

    public Simulador() {
        fila = new Fila();
        atendidosTotales = 0;
        abandonosTotales = 0;
    }

    public void ejecutarRetoExtendido(int retardoMs) {
        reiniciar();
        System.out.println("===============================================================");
        System.out.println("          INICIANDO SIMULACION CCCF (RETO EXTENDIDO)          ");
        System.out.println("===============================================================\n");

        for (int minuto = 1; minuto <= 120; minuto++) {
            System.out.println("---------------------------------------------------------------");
            System.out.println("MINUTO " + minuto + " / 120");

            if (minuto >= 20) {
                if (minuto % 5 == 0) {
                    abandonosTotales += fila.procesarAburrimiento(minuto);
                }

                if (minuto % 15 == 0) {
                    atendidosTotales += fila.procesarAvisoCajaExtra();
                }

                fila.simularEntregaCompras();
            }

            if (Math.random() < PROB_LLEGADA) {
                boolean esPref = (minuto >= 20) && (Math.random() < PROB_PREFERENTE);
                boolean embarazada = esPref && Math.random() < 0.33;
                boolean terceraEdad = esPref && !embarazada && Math.random() < 0.50;
                boolean discapacidad = esPref && !embarazada && !terceraEdad;

                Persona conocido = null;
                if (minuto >= 20 && !esPref && Math.random() < PROB_COLADO) {
                    conocido = fila.obtenerPersonaAleatoria();
                }

                Persona nueva = new Persona(minuto, embarazada, terceraEdad, discapacidad, conocido);
                fila.agregarPersona(nueva);
            }

            if (Math.random() < PROB_ATENCION) {
                if (!fila.estaVacia()) {
                    Persona atendida = fila.atender();
                    if (atendida != null) {
                        System.out.println("[CAJA 1] Persona atendida.");
                        atendidosTotales++;
                    }
                }
            }

            fila.mostrarEstadoFila();

            try {
                Thread.sleep(retardoMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        imprimirResultados(120);
    }

    private void reiniciar() {
        fila = new Fila();
        atendidosTotales = 0;
        abandonosTotales = 0;
    }

    private void imprimirResultados(int minutosTotales) {
        System.out.println("\n=======================================================");
        System.out.println("            RESUMEN AL CIERRE DEL CENTRO               ");
        System.out.println("=======================================================");
        System.out.println(" - Personas atendidas con exito: " + atendidosTotales);
        System.out.println(" - Personas que permanecen en fila: " + fila.getTamano() + " m");
        System.out.println(" - Personas que abandonaron por aburrimiento: " + abandonosTotales);
        System.out.println("=======================================================\n");
    }
}
