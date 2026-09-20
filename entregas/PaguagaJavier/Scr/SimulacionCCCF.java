package entregas.PaguagaJavier.Scr;

public class SimulacionCCCF {
    public static void main(String[] args) {

        final int TOTAL_MINUTOS = 2 * 60;
        final double PROBABILIDAD_LLEGADA = 0.6;
        final double PROBABILIDAD_APERTURA_CAJA = 0.4;
        final int LIMITE_MAXIMO_FILA = 30;

        Persona[] fila = new Persona[LIMITE_MAXIMO_FILA];
        int cantidadEnFila = 0;
        int idPersona = 1;
        int personasAtendidas = 0;

        System.out.println("--- INICIO DE LA SIMULACIÓN ---");
        System.out.println("Minuto | Longitud Actual (Metros)");

        for (int minutoActual = 1; minutoActual <= TOTAL_MINUTOS; minutoActual++) {

            for (int indicePersona = 0; indicePersona < cantidadEnFila; indicePersona++) {
                fila[indicePersona].minutosTranscurridosEnFila++;
            }

            if (minutoActual >= 20) {

                if (minutoActual % 5 == 0) {
                    for (int indiceClienteRevisado = 0; indiceClienteRevisado < cantidadEnFila; indiceClienteRevisado++) {
                        Persona clienteEvaluado = fila[indiceClienteRevisado];

                        boolean superaTiempoLimite = clienteEvaluado.minutosTranscurridosEnFila > 8;
                        boolean decideAburrirse = Math.random() < 0.3;

                        if (superaTiempoLimite && decideAburrirse) {
                            for (int indiceDesplazamiento = indiceClienteRevisado; indiceDesplazamiento < cantidadEnFila
                                    - 1; indiceDesplazamiento++) {
                                fila[indiceDesplazamiento] = fila[indiceDesplazamiento + 1];
                            }
                            fila[cantidadEnFila - 1] = null;
                            cantidadEnFila--;
                            indiceClienteRevisado--;
                        }
                    }
                }
            }

            if (Math.random() < PROBABILIDAD_LLEGADA) {
                boolean decideEntrarAlCentro = true;
                boolean filaSuperaCapacidad = cantidadEnFila >= LIMITE_MAXIMO_FILA;
                boolean decideDesistirPorFilaLarga = Math.random() < 0.5;

                if (filaSuperaCapacidad && decideDesistirPorFilaLarga) {
                    decideEntrarAlCentro = false;
                }

                if (decideEntrarAlCentro && cantidadEnFila < LIMITE_MAXIMO_FILA) {
                    boolean esClientePreferente = minutoActual >= 20 && Math.random() < 0.20;
                    Persona nuevaPersona;

                    if (esClientePreferente) {
                        nuevaPersona = new ClientePreferente(idPersona);
                    } else {
                        nuevaPersona = new ClienteNormal(idPersona);
                    }
                    idPersona++;

                    if (esClientePreferente) {
                        int ultimoIndicePreferenteEncontrado = -1;
                        for (int indiceBusqueda = 0; indiceBusqueda < cantidadEnFila; indiceBusqueda++) {
                            if (fila[indiceBusqueda].tieneDerechoPreferente) {
                                ultimoIndicePreferenteEncontrado = indiceBusqueda;
                            }
                        }

                        if (ultimoIndicePreferenteEncontrado != -1) {
                            int posicionInsercionPreferente = ultimoIndicePreferenteEncontrado + 1;
                            for (int indiceDesplazamiento = cantidadEnFila; indiceDesplazamiento > posicionInsercionPreferente; indiceDesplazamiento--) {
                                fila[indiceDesplazamiento] = fila[indiceDesplazamiento - 1];
                            }
                            fila[posicionInsercionPreferente] = nuevaPersona;
                            cantidadEnFila++;
                        } else {
                            for (int indiceDesplazamiento = cantidadEnFila; indiceDesplazamiento > 0; indiceDesplazamiento--) {
                                fila[indiceDesplazamiento] = fila[indiceDesplazamiento - 1];
                            }
                            fila[0] = nuevaPersona;
                            cantidadEnFila++;
                        }
                    } else {
                        fila[cantidadEnFila] = nuevaPersona;
                        cantidadEnFila++;
                    }
                }
            }

            if (Math.random() < PROBABILIDAD_APERTURA_CAJA) {
                if (cantidadEnFila > 0) {
                    personasAtendidas++;

                    for (int indiceDesplazamiento = 0; indiceDesplazamiento < cantidadEnFila
                            - 1; indiceDesplazamiento++) {
                        fila[indiceDesplazamiento] = fila[indiceDesplazamiento + 1];
                    }
                    fila[cantidadEnFila - 1] = null;
                    cantidadEnFila--;
                }
            }

            System.out.println("Min " + minutoActual + " \t| " + cantidadEnFila + " metros");
        }

        System.out.println("----------------------------------------");
        System.out.println("FIN DE LA SIMULACION. Atendidos: " + personasAtendidas);
    }
}