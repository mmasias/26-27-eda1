import java.util.Random;

public class SimuladorDeCola {
    public static void main(String[] args) {
        Random rand = new Random();
        Cola fila = new Cola(50);
        int personasAtendidas = 0;
        int contadorPersonas = 0;
        int desistidos = 0;
        int aburridos = 0;

        System.out.println("Minuto | Longitud Cola (m) | Personas en Cola");
        System.out.println("----------------------------------------------");

        for (int minuto = 1; minuto <= 120; minuto++) {
            boolean reglasActivas = (minuto >= 20);

            if (rand.nextDouble() < 0.6) {
                boolean desistir = false;

                if (fila.getTamano() > 30) {
                    double probDesistir = (fila.getTamano() - 30) * 0.10;
                    if (rand.nextDouble() < probDesistir) {
                        desistir = true;
                        desistidos++;
                    }
                }

                if (!desistir) {
                    contadorPersonas++;
                    boolean esPref = reglasActivas && (rand.nextDouble() < 0.15);
                    Persona nueva = new Persona(contadorPersonas, minuto, esPref);

                    if (esPref) {
                        fila.insertarPreferente(nueva);
                    } else {
                        fila.encolar(nueva);
                    }
                }
            }

            if (reglasActivas && fila.getTamano() > 0) {
                if (rand.nextDouble() < 0.08) { 
                    int posConocido = rand.nextInt(fila.getTamano());
                    if (fila.getTamano() <= 30 || rand.nextDouble() >= 0.5) {
                        contadorPersonas++;
                        fila.insertarEn(posConocido + 1, new Persona(contadorPersonas, minuto, false));
                    }
                }

                if (rand.nextDouble() < 0.03 && fila.getTamano() > 1) {
                    int posSale = rand.nextInt(fila.getTamano());
                    fila.removerEn(posSale);
                }
            }

            if (reglasActivas && minuto % 5 == 0) {
                for (int i = fila.getTamano() - 1; i >= 0; i--) {
                    Persona p = fila.get(i);
                    if ((minuto - p.getMinutoLlegada()) > 8) {
                        if (rand.nextDouble() < 0.30) {
                            fila.removerEn(i);
                            aburridos++;
                        }
                    }
                }
            }

            if (reglasActivas && minuto % 15 == 0 && fila.getTamano() > 25) {
                fila.desencolar();
                personasAtendidas++;
            }

            if (rand.nextDouble() < 0.4) {
                if (fila.getTamano() > 0) {
                    fila.desencolar();
                    personasAtendidas++;
                }
            }

            int longitudMetros = fila.getTamano(); 
            System.out.printf("Min %3d | %15d m | %16d personas\n", minuto, longitudMetros, fila.getTamano());
        }

        System.out.println("\n=== RESUMEN FINAL DE LA SIMULACIÓN ===");
        System.out.println("Personas atendidas exitosamente: " + personasAtendidas);
        System.out.println("Personas restantes en la fila: " + fila.getTamano());
        System.out.println("Personas que desistieron por cola larga: " + desistidos);
        System.out.println("Personas que se aburrieron y se fueron: " + aburridos);
    }
}