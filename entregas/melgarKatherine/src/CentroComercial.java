public class CentroComercial {
    private Fila fila;
    private int clientesAtendidos;
    private int minutoActual;

    final private int MINUTOS_TOTALES = 120; 
    final private double PROB_LLEGADA = 0.6;
    final private double PROB_CAJA = 0.4;

    public CentroComercial() {
        fila = new Fila();
        clientesAtendidos = 0;
        minutoActual = 0;
    }

    public void simular() {
        do {
            minutoActual++;
    
            boolean llegaCliente = Math.random() < PROB_LLEGADA;
            int agregar = llegaCliente ? fila.recibir(new Persona()) : 0;
            boolean abreCaja = Math.random() < PROB_CAJA;
            boolean puedeAtender = abreCaja && fila.tieneGente();
            clientesAtendidos = clientesAtendidos + (puedeAtender ? fila.despachar() : 0);
            fila.actualizarTiempos();
            boolean tocaRevisarAburridos = minutoActual >= 20 && minutoActual % 5 == 0;
            int abandonos = tocaRevisarAburridos ? fila.purgarAburridos() : 0;

            this.imprimirEstadoMinuto();

        } while (minutoActual < MINUTOS_TOTALES);

        this.mostrarReporte();
    }

    private void imprimirEstadoMinuto() {
        String dibujoFila = "-".repeat(fila.obtenerLongitud());
        System.out.println(String.format("[%03d] Fila (%02dm): %s", minutoActual, fila.obtenerLongitud(), dibujoFila));
    }

    private void mostrarReporte() {
        System.out.println("=".repeat(40));
        System.out.println("REPORTE FINAL - RETO EXTENDIDO (2 HORAS)");
        System.out.println("=".repeat(40));
        System.out.println("Clientes atendidos: " + clientesAtendidos);
        System.out.println("Clientes que quedaron en fila: " + fila.obtenerLongitud());
        System.out.println("=".repeat(40));
    }
}