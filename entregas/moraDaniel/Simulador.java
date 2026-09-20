package entregas.moraDaniel;

import java.util.Random;

public class Simulador {

    private static final double PROBABILIDAD_DE_LLEGADA = 0.6;
    private static final double PROBABILIDAD_DE_APERTURA_DE_CAJA = 0.4;
    private static final int MINUTO_DE_INICIO_DE_REGLAS_EXTENDIDAS = 20;
    private static final int MAXIMO_DE_POSICIONES_SEGUN_POLITICA = 30;
    private static final int MINUTOS_DE_ESPERA_PARA_ABURRIRSE = 8;
    private static final double PROBABILIDAD_DE_ABURRIMIENTO = 0.30;
    private static final int INTERVALO_EN_MINUTOS_PARA_CHEQUEAR_ABURRIMIENTO = 5;
    private static final int UMBRAL_DE_PERSONAS_PARA_ANUNCIO = 25;
    private static final int INTERVALO_EN_MINUTOS_PARA_ANUNCIO = 15;

    private static final double PROBABILIDAD_DE_LLEGADA_PREFERENTE = 0.10;
    private static final double PROBABILIDAD_DE_COLARSE = 0.08;
    private static final double PROBABILIDAD_DE_ENTREGA_DE_COMPRAS = 0.04;
    private static final double PROBABILIDAD_DE_DESISTIR_SI_LA_FILA_ESTA_LLENA = 0.5;

    private final Fila fila = new Fila();
    private final Random generadorAleatorio = new Random();
    private int siguienteIdentificadorDePersona = 0;
    private int cantidadDePersonasAtendidas = 0;
    private int cantidadDePersonasQueDesistieron = 0;

    public void simular(int minutosTotales, boolean reglasExtendidasHabilitadas) {
        for (int minutoActual = 1; minutoActual <= minutosTotales; minutoActual++) {

            if (generadorAleatorio.nextDouble() < PROBABILIDAD_DE_LLEGADA) {
                intentarIncorporarAlFinal(minutoActual);
            }

            if (generadorAleatorio.nextDouble() < PROBABILIDAD_DE_APERTURA_DE_CAJA && !fila.estaVacia()) {
                fila.eliminarEnPosicion(0);
                cantidadDePersonasAtendidas++;
            }

            boolean reglasExtendidasActivasEsteMinuto = reglasExtendidasHabilitadas
                    && minutoActual >= MINUTO_DE_INICIO_DE_REGLAS_EXTENDIDAS;

            if (reglasExtendidasActivasEsteMinuto) {
                if (minutoActual % INTERVALO_EN_MINUTOS_PARA_CHEQUEAR_ABURRIMIENTO == 0) {
                    procesarAburrimiento(minutoActual);
                }
                if (generadorAleatorio.nextDouble() < PROBABILIDAD_DE_LLEGADA_PREFERENTE) {
                    intentarIncorporarPreferente(minutoActual);
                }
                if (generadorAleatorio.nextDouble() < PROBABILIDAD_DE_COLARSE && !fila.estaVacia()) {
                    intentarColarseDetrasDeConocido(minutoActual);
                }
                if (generadorAleatorio.nextDouble() < PROBABILIDAD_DE_ENTREGA_DE_COMPRAS
                        && fila.getCantidadDePersonasEnFila() >= 1) {
                    entregarCompras();
                }
            }

            if (minutoActual % INTERVALO_EN_MINUTOS_PARA_ANUNCIO == 0
                    && fila.getCantidadDePersonasEnFila() > UMBRAL_DE_PERSONAS_PARA_ANUNCIO) {
                System.out.println("[minuto " + minutoActual + "] \"Pasen por esta caja en orden de fila\"");
            }

            System.out.println("minuto " + minutoActual + " -> longitud fila: "
                    + fila.getCantidadDePersonasEnFila() + " m");
        }

        System.out.println();
        System.out.println("=== CIERRE ===");
        System.out.println("Personas atendidas:        " + cantidadDePersonasAtendidas);
        System.out.println("Personas en fila al cierre: " + fila.getCantidadDePersonasEnFila());
        System.out.println("Personas que desistieron:   " + cantidadDePersonasQueDesistieron);
    }

    private boolean laPersonaDesisteAntesDeEntrar() {
        return fila.getCantidadDePersonasEnFila() >= MAXIMO_DE_POSICIONES_SEGUN_POLITICA
                && generadorAleatorio.nextDouble() < PROBABILIDAD_DE_DESISTIR_SI_LA_FILA_ESTA_LLENA;
    }

    private void intentarIncorporarAlFinal(int minutoActual) {
        if (laPersonaDesisteAntesDeEntrar()) {
            cantidadDePersonasQueDesistieron++;
            return;
        }
        Persona personaNueva = new Persona(siguienteIdentificadorDePersona, Persona.SIN_PRIORIDAD, minutoActual);
        siguienteIdentificadorDePersona++;
        fila.insertarAlFinal(personaNueva);
    }

    private void intentarIncorporarPreferente(int minutoActual) {
        if (laPersonaDesisteAntesDeEntrar()) {
            cantidadDePersonasQueDesistieron++;
            return;
        }
        int posicionParaInsertar = fila.posicionTrasUltimoPreferente();
        Persona personaNueva = new Persona(
                siguienteIdentificadorDePersona, Persona.CON_PRIORIDAD_PREFERENTE, minutoActual);
        siguienteIdentificadorDePersona++;
        fila.insertarEnPosicion(posicionParaInsertar, personaNueva);
    }

    private void intentarColarseDetrasDeConocido(int minutoActual) {
        if (laPersonaDesisteAntesDeEntrar()) {
            cantidadDePersonasQueDesistieron++;
            return;
        }
        int indiceDePersonaConocida = generadorAleatorio.nextInt(fila.getCantidadDePersonasEnFila());
        Persona personaNueva = new Persona(siguienteIdentificadorDePersona, Persona.SIN_PRIORIDAD, minutoActual);
        siguienteIdentificadorDePersona++;
        fila.insertarEnPosicion(indiceDePersonaConocida + 1, personaNueva);
    }

    private void entregarCompras() {
        if (fila.getCantidadDePersonasEnFila() < 2) {
            return;
        }
        int indiceDePersonaQueEntregaCompras = generadorAleatorio.nextInt(fila.getCantidadDePersonasEnFila());
        fila.eliminarEnPosicion(indiceDePersonaQueEntregaCompras);
    }

    private void procesarAburrimiento(int minutoActual) {
        for (int indiceRecorrido = fila.getCantidadDePersonasEnFila() - 1; indiceRecorrido >= 0; indiceRecorrido--) {
            int minutosEsperandoEnFila = minutoActual - fila.getPersonaEnPosicion(indiceRecorrido).getMinutoDeLlegada();
            if (minutosEsperandoEnFila > MINUTOS_DE_ESPERA_PARA_ABURRIRSE
                    && generadorAleatorio.nextDouble() < PROBABILIDAD_DE_ABURRIMIENTO) {
                fila.eliminarEnPosicion(indiceRecorrido);
            }
        }
    }
}