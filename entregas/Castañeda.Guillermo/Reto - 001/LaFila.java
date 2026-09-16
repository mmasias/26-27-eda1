import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulacionFila {

    static class Persona {
        int minutoLlegada;
        boolean preferente;

        public Persona(int minutoLlegada, boolean preferente) {
            this.minutoLlegada = minutoLlegada;
            this.preferente = preferente;
        }
    }

    public static int size(List<Persona> lista) {
        return lista.size();
    }

    public static boolean estaVacia(List<Persona> lista) {
        return lista.isEmpty();
    }

    public static void main(String[] argumentos) {
        Random aleatorio = new Random();
        List<Persona> fila = new ArrayList<>();

        int personasAtendidas = 0;
        int personasDesistidas = 0;
        int personasAburridas = 0;

        for (int minuto = 1; minuto <= 120; minuto++) {

            if (aleatorio.nextDouble() < 0.6) {
                if (size(fila) >= 30 && aleatorio.nextBoolean()) {
                    personasDesistidas++;
                } else {
                    boolean esPreferente = aleatorio.nextDouble() < 0.15;
                    Persona nueva = new Persona(minuto, esPreferente);

                    if (minuto >= 20 && esPreferente) {
                        int posicion = 0;
                        for (int indice = 0; indice < size(fila); indice++) {
                            if (fila.get(indice).preferente) {
                                posicion = indice + 1;
                            }
                        }
                        fila.add(posicion, nueva);
                    } else {
                        fila.add(nueva);
                    }
                }
            }

            if (minuto >= 20) {

                if (!estaVacia(fila) && aleatorio.nextDouble() < 0.1) {
                    if (!(size(fila) >= 30 && aleatorio.nextBoolean())) {
                        int posicionConocido = aleatorio.nextInt(size(fila));
                        fila.add(posicionConocido + 1, new Persona(minuto, false));
                    } else {
                        personasDesistidas++;
                    }
                }

                if (size(fila) >= 2 && aleatorio.nextDouble() < 0.05) {
                    int posicion = aleatorio.nextInt(size(fila));
                    fila.remove(posicion);
                }

                for (int indice = 0; indice < size(fila); indice++) {
                    if (minuto - fila.get(indice).minutoLlegada > 8 && aleatorio.nextDouble() < 0.3) {
                        fila.remove(indice);
                        personasAburridas++;
                        indice--;
                    }
                }

                if (minuto % 15 == 0 && size(fila) > 25) {
                    System.out.println("  [ALTAVOZ Minuto " + minuto + "]: Pasen por esta caja en orden de fila.");
                }
            }

            if (aleatorio.nextDouble() < 0.4 && !estaVacia(fila)) {
                fila.remove(0);
                personasAtendidas++;
            }

            int longitudMetros = size(fila);
            System.out.println("Minuto " + minuto + " - Longitud de la fila: " + longitudMetros + " metros (" + size(fila) + " personas)");
        }

        System.out.println("\n--- RESUMEN DE LA SIMULACION ---");
        System.out.println("Personas atendidas: " + personasAtendidas);
        System.out.println("Personas en fila al cierre: " + size(fila));
        System.out.println("Personas que se aburrieron: " + personasAburridas);
        System.out.println("Personas que desistieron por cola larga: " + personasDesistidas);
    }
}