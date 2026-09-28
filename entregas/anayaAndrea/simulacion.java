import java.util.Random;

public class Simulacion {

    private final double probabilidadLlegada;
    private final double probabilidadAburrirse;
    private final int minutoInicioReglas;
    private final int esperaMaxima;
    private final int periodoAburrimiento;
    private final int maximoPersonas;

    private final double probabilidadPreferente;
    private final double probabilidadColado;
    private final double probabilidadEntrega;
    private final double probabilidadDesistir;

    private final Random azar;
    private final Caja caja;
    private final Parlante parlante;

    private Fila fila;
    private Estadisticas estadisticas;

    public Simulacion() {
        this.probabilidadLlegada = 0.6;
        this.probabilidadAburrirse = 0.30;
        this.minutoInicioReglas = 20;
        this.esperaMaxima = 8;
        this.periodoAburrimiento = 5;
        this.maximoPersonas = 30;

        this.probabilidadPreferente = 0.10;
        this.probabilidadColado = 0.05;
        this.probabilidadEntrega = 0.05;
        this.probabilidadDesistir = 0.5;

        this.azar = new Random();
        this.caja = new Caja(0.4, azar);
        this.parlante = new Parlante(25, 15);
    }

    public void ejecutar(int minutos, boolean extendido) {
        fila = new Fila(maximoPersonas);
        estadisticas = new Estadisticas();

        for (int minuto = 1; minuto <= minutos; minuto++) {
            boolean reglas = extendido && minuto >= minutoInicioReglas;

            llegadaNormal(minuto, reglas);
            if (reglas) {
                llegadaPreferente(minuto);
                colarse(minuto);
                entregarCompras();
                if (minuto % periodoAburrimiento == 0) {
                    aburrirse(minuto);
                }
            }
            abrirCaja();
            if (reglas && parlante.anunciarSiCorresponde(minuto, fila.tamano())) {
                estadisticas.registrarAviso();
            }

            estadisticas.registrarLongitud(fila.tamano());
            if (extendido) {
                System.out.println("Minuto " + minuto + ": " + fila.tamano() + " m");
            }
        }
        estadisticas.imprimirResumen(extendido, fila.tamano());
    }

    private boolean seIncorpora(boolean reglas) {
        if (reglas && fila.estaLlena() && azar.nextDouble() < probabilidadDesistir) {
            estadisticas.registrarDesistio();
            return false;
        }
        return true;
    }

    private void llegadaNormal(int minuto, boolean reglas) {
        if (azar.nextDouble() < probabilidadLlegada && seIncorpora(reglas)) {
            fila.agregarAlFinal(new Cliente(minuto, false));
            estadisticas.registrarLlegada();
        }
    }

    private void llegadaPreferente(int minuto) {
        if (azar.nextDouble() < probabilidadPreferente && seIncorpora(true)) {
            fila.agregarPreferente(new Cliente(minuto, true));
            estadisticas.registrarPreferente();
        }
    }

    private void colarse(int minuto) {
        if (!fila.estaVacia() && azar.nextDouble() < probabilidadColado && seIncorpora(true)) {
            int posicionConocido = azar.nextInt(fila.tamano());
            fila.insertarEn(posicionConocido + 1, new Cliente(minuto, false));
            estadisticas.registrarColado();
        }
    }

    private void entregarCompras() {
        if (!fila.estaVacia() && azar.nextDouble() < probabilidadEntrega) {
            estadisticas.registrarEntrega();
        }
    }

    private void aburrirse(int minuto) {
        for (int i = fila.tamano() - 1; i >= 0; i--) {
            boolean llevaMucho = fila.clienteEn(i).minutosEsperando(minuto) > esperaMaxima;
            if (llevaMucho && azar.nextDouble() < probabilidadAburrirse) {
                fila.retirarEn(i);
                estadisticas.registrarAburrida();
            }
        }
    }

    private void abrirCaja() {
        if (caja.seAbre() && !fila.estaVacia()) {
            fila.atenderPrimero();
            estadisticas.registrarAtendida();
        }
    }
}