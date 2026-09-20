package Reto-001;

import java.util.Random;

public class CentroComercial {

    private static final double probLlegada = 0.6;
    private static final double probCajaLibre = 0.4;
    private static final int minutoInicioExtra = 20;
    private static final int minutosAburrimiento = 8;
    private static final int periodoAburrimiento = 5;
    private static final double probAburrirse = 0.30;
    private static final int capacidadFila = 30;
    private static final int periodoAnuncio = 15;
    private static final int umbralAnuncio = 25;
    private static final double probPreferente = 0.10;
    private static final double probColado = 0.10;
    private static final double probEntrega = 0.05;
    private static final double probDesistir = 0.5;

    private Fila fila;
    private Random random;
    private int atendidas;
    private int contador;
    private boolean reglasExtendidas;
    private int[] longitudes;

    public CentroComercial(int minutos, boolean reglasExtendidas) {
        this.fila = new Fila();
        this.random = new Random();
        this.atendidas = 0;
        this.contador = 0;
        this.reglasExtendidas = reglasExtendidas;
        this.longitudes = new int[minutos + 1];
    }

    public void simular(int minutos) {
        for (int minuto = 1; minuto <= minutos; minuto++) {
            procesarLlegada(minuto);
            procesarAtencion();

            if (hayExtraEn(minuto)) {
                procesarAburrimiento(minuto);
                procesarEntrega();
                procesarAnuncio(minuto);
            }

            registrarLongitud(minuto);
        }
        mostrarInforme(minutos);
    }

    private boolean hayExtraEn(int minuto) {
        return reglasExtendidas && minuto >= minutoInicioExtra;
    }

    private void procesarLlegada(int minuto) {
        if (!ocurre(probLlegada)) return;

        String nombre = nuevoNombre();

        if (hayExtraEn(minuto)) {
            double dado = random.nextDouble();
            if (dado < probPreferente) {
                incorporarPreferente(new PersonaPreferente(nombre, minuto));
                return;
            }
            if (dado < probPreferente + probColado) {
                incorporarColado(new PersonaNormal(nombre, minuto));
                return;
            }
        }

        incorporarAlFinal(new PersonaNormal(nombre, minuto));
    }

    private String nuevoNombre() {
        contador++;
        return "Persona " + contador;
    }

    private void incorporarAlFinal(Persona persona) {
        if (desiste()) return;
        fila.agregarAlFinal(persona);
    }

    private void incorporarPreferente(Persona persona) {
        if (desiste()) return;
        fila.insertarEn(fila.posicionTrasUltimoPreferente(), persona);
    }

    private void incorporarColado(Persona persona) {
        if (fila.estaVacia()) {
            incorporarAlFinal(persona);
            return;
        }
        if (desiste()) return;
        int posicion = random.nextInt(fila.getTamanio());
        fila.insertarEn(posicion + 1, persona);
    }

    private boolean desiste() {
        if (fila.getTamanio() < capacidadFila) return false;
        return ocurre(probDesistir);
    }

    private void procesarAtencion() {
        if (!ocurre(probCajaLibre)) return;
        if (fila.estaVacia()) return;
        fila.atenderPrimero();
        atendidas++;
    }

    private void procesarAburrimiento(int minuto) {
        if (minuto % periodoAburrimiento != 0) return;

        for (int posicion = fila.getTamanio() - 1; posicion >= 0; posicion--) {
            Persona persona = fila.obtener(posicion);
            if (esperaDemasiado(persona, minuto) && ocurre(probAburrirse)) {
                fila.quitarDe(posicion);
            }
        }
    }

    private boolean esperaDemasiado(Persona persona, int minuto) {
        return persona.minutosEsperando(minuto) > minutosAburrimiento;
    }

    private void procesarEntrega() {
        if (fila.getTamanio() < 2) return;
        if (!ocurre(probEntrega)) return;
        fila.quitarDe(random.nextInt(fila.getTamanio()));
    }

    private void procesarAnuncio(int minuto) {
        if (minuto % periodoAnuncio != 0) return;
        if (fila.getTamanio() > umbralAnuncio) {
            System.out.println("Minuto " + minuto + ": \"Pasen por esta caja en orden de fila\"");
        }
    }

    private void registrarLongitud(int minuto) {
        longitudes[minuto] = fila.getTamanio();
    }

    private boolean ocurre(double probabilidad) {
        return random.nextDouble() < probabilidad;
    }

    private void mostrarInforme(int minutos) {
        System.out.println("=== Cierre de la simulacion ===");
        System.out.println("Personas atendidas: " + atendidas);
        System.out.println("Personas que quedan en fila: " + fila.getTamanio());

        if (reglasExtendidas) {
            mostrarLongitudes(minutos);
        }
    }

    private void mostrarLongitudes(int minutos) {
        System.out.println();
        System.out.println("=== Longitud de la fila por minuto (1 persona = 1 metro) ===");
        for (int minuto = 1; minuto <= minutos; minuto++) {
            System.out.println("Minuto " + minuto + ": " + longitudes[minuto] + " m");
        }
    }
}