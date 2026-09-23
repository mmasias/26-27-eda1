import java.util.Random;

public class SimulacionCola {
    public static void main(String[] args) {
        Random rand = new Random();
        Lista cola = new Lista();
        Tiempo tiempo = new Tiempo();

        int personasAtendidas = 0;
        int contadorClientes = 0;
        int minutosColaEnCero = 0;
        int desistidos = 0;
        int aburridos = 0;

        for (int m = 1; m <= 120; m++) {
            int minuto = tiempo.obtenerMinutoActual();
            boolean reglasActivas = (minuto >= 20);
            boolean llegaPersona = rand.nextDouble() < 0.6;

            if (llegaPersona) {
                boolean desiste = false;
                if (cola.obtenerTamano() > 30) {
                    double probabilidadDeDesistir = (cola.obtenerTamano() - 30) * 0.10;
                    if (rand.nextDouble() < probabilidadDeDesistir) {
                        desiste = true;
                        desistidos++;
                    }
                }

                if (!desiste) {
                    contadorClientes++;
                    boolean esPreferente = reglasActivas && (rand.nextDouble() < 0.15);
                    Cliente nuevo = new Cliente(contadorClientes, minuto, esPreferente);

                    if (esPreferente) {
                        cola.insertarClientePreferente(nuevo);
                    } else {
                        cola.encolar(nuevo);
                    }
                }
            }

            if (reglasActivas && !cola.estaVacia()) {
                if (rand.nextDouble() < 0.08) {
                    int posicionConocido = rand.nextInt(cola.obtenerTamano());
                    if (cola.obtenerTamano() <= 30 || rand.nextDouble() >= 0.5) {
                        contadorClientes++;
                        cola.insertarCliente(posicionConocido + 1, new Cliente(contadorClientes, minuto, false));
                    } else {
                        desistidos++;
                    }
                }

                if (rand.nextDouble() < 0.03 && cola.obtenerTamano() > 1) {
                    cola.eliminarCliente(rand.nextInt(cola.obtenerTamano()));
                }
            }

            if (reglasActivas && minuto % 5 == 0) {
                int i = 0;
                while (i < cola.obtenerTamano()) {
                    Cliente c = cola.obtener(i);
                    if (c != null && (minuto - c.obtenerMinutoLlegada()) > 8) {
                        if (rand.nextDouble() < 0.30) {
                            cola.eliminarCliente(i);
                            aburridos++;
                            continue;
                        }
                    }
                    i++;
                }
            }

            if (reglasActivas && minuto % 15 == 0 && cola.obtenerTamano() > 25) {
                cola.desencolar();
                personasAtendidas++;
            }

            if (rand.nextDouble() < 0.4 && !cola.estaVacia()) {
                cola.desencolar();
                personasAtendidas++;
            }

            if (cola.estaVacia()) {
                minutosColaEnCero++;
            }

            String estadoLlegada = llegaPersona ? "Llega 1 persona" : "No llega nadie ";
            int longitudMetros = cola.obtenerTamano();
            System.out.printf("MINUTO %d - %s - En Cola: %d (Longitud: %d m)\n", 
                minuto, estadoLlegada, cola.obtenerTamano(), longitudMetros);
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

            tiempo.avanzar();
        }

        System.out.println("\nRESUMEN");
        System.out.println("============================================================");
        System.out.println("Minutos con cola en cero       : " + minutosColaEnCero);
        System.out.println("Personas en la cola al cierre  : " + cola.obtenerTamano() + " (" + cola.obtenerTamano() + " m)");
        System.out.println("Personas atendidas en el dia   : " + personasAtendidas);
        System.out.println("Personas que desistieron       : " + desistidos);
        System.out.println("Personas que se aburrieron     : " + aburridos);
        System.out.println("============================================================");
    }
}