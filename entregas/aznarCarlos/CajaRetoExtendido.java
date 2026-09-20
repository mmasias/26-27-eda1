public class CajaRetoExtendido {

    static class Persona {
        int minutoLlegada;
        boolean esPreferente;

        Persona(int minutoLlegada, boolean esPreferente) {
            this.minutoLlegada = minutoLlegada;
            this.esPreferente = esPreferente;
        }
    }

    public static void main(String[] args) {
        int minutosTotales = 2 * 60;
        Persona[] fila = new Persona[500];
        int numPersonas = 0;
        int atendidos = 0;
        int aburridos = 0;

        for (int min = 1; min <= minutosTotales; min++) {

            if (min >= 20 && min % 5 == 0) {
                for (int i = numPersonas - 1; i >= 0; i--) {
                    if (min - fila[i].minutoLlegada > 8) {
                        if (Math.random() < 0.30) {
                            numPersonas = eliminarPersonaPorAburrimiento(fila, numPersonas, i);
                            aburridos++;
                        }
                    }
                }
            }

            if (Math.random() < 0.6) {
                if (puedeEntrarEnLaFila(numPersonas)) {
                    numPersonas = agregarPersona(fila, numPersonas, numPersonas, new Persona(min, false));
                }
            }

            if (min >= 20) {
                if (Math.random() < 0.15) {
                    if (puedeEntrarEnLaFila(numPersonas)) {
                        int posicion = -1;
                        for (int i = numPersonas - 1; i >= 0; i--) {
                            if (fila[i].esPreferente) {
                                posicion = i;
                                break;
                            }
                        }
                        if (posicion != -1) {
                            numPersonas = agregarPersona(fila, numPersonas, posicion + 1, new Persona(min, true));
                        } else {
                            numPersonas = agregarPersona(fila, numPersonas, 0, new Persona(min, true));
                        }
                    }
                }

                if (Math.random() < 0.10) {
                    if (numPersonas > 0 && puedeEntrarEnLaFila(numPersonas)) {
                        int posConocido = (int) (Math.random() * numPersonas);
                        numPersonas = agregarPersona(fila, numPersonas, posConocido + 1, new Persona(min, false));
                    }
                }

                if (Math.random() < 0.05) {
                }
            }

            if (Math.random() < 0.4) {
                if (numPersonas > 0) {
                    numPersonas = eliminarPersonaPorAburrimiento(fila, numPersonas, 0);
                    atendidos++;
                }
            }

            if (min % 15 == 0 && numPersonas > 14) {
                System.out.println("Porfavor vayan pasando por esta caja en orden de fila.");
                int extraAtendidos = Math.min(5, numPersonas);
                for (int i = 0; i < extraAtendidos; i++) {
                    numPersonas = eliminarPersonaPorAburrimiento(fila, numPersonas, 0);
                    atendidos++;
                }
            }

            System.out.println("Minuto " + min + ": " + numPersonas + " metros");
        }

        System.out.println("----------------------------------------------------");
        System.out.println("Resultados al cierre:");
        System.out.println("Personas atendidas: " + atendidos);
        System.out.println("Personas aburridas que se fueron: " + aburridos);
        System.out.println("Personas en fila: " + numPersonas + " (" + numPersonas + " metros)");
    }

    private static boolean puedeEntrarEnLaFila(int numPersonas) {
        if (numPersonas > 30) {
            return Math.random() >= 0.5;
        }
        return true;
    }

    private static int agregarPersona(Persona[] fila, int numPersonas, int posicion, Persona p) {
        for (int i = numPersonas; i > posicion; i--) {
            fila[i] = fila[i - 1];
        }
        fila[posicion] = p;
        return numPersonas + 1;
    }

    private static int eliminarPersonaPorAburrimiento(Persona[] fila, int numPersonas, int posicion) {
        for (int i = posicion; i < numPersonas - 1; i++) {
            fila[i] = fila[i + 1];
        }
        fila[numPersonas - 1] = null;
        return numPersonas - 1;
    }
}