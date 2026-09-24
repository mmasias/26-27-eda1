class Simulador {

    private final int DURACION_HORAS = 4;
    private final int MAXIMO_CAJAS = 7;

    private int horas;
    private int minutos;
    private int cajasActivas;
    private int personasAtendidas;

    private Fila[] cajas;

    public static void main(String[] args) throws InterruptedException {
        Simulador simulador = new Simulador();
        simulador.iniciarSimulacion();
    }

    public Simulador() {
        horas = 0;
        minutos = 0;
        cajasActivas = 1;
        personasAtendidas = 0;
        cajas = new Fila[MAXIMO_CAJAS];
        cajas[0] = new Fila();
    }

    private void iniciarSimulacion() {
        while (horas < DURACION_HORAS) {
            mostrarReloj();
            simular();
            dibujarSimulacion();
            avanzarTiempo();
        }
        System.out.println("Total de personas atendidas: " + personasAtendidas);
    }

    private void simular() {
        if (evaluarProbabilidad(0.6)) {
            int cajaAzar = (int) (Math.random() * cajasActivas);
            cajas[cajaAzar].aumentarFila(new Persona());
        }

        if (cajasActivas < MAXIMO_CAJAS && evaluarProbabilidad(0.4)) {
            cajas[cajasActivas] = new Fila();
            cajasActivas++;
        }

        for (int i = 0; i < cajasActivas; i++) {
            if (cajas[i].atenderPersona()) {
                personasAtendidas++;
            }
        }
    }

    private boolean evaluarProbabilidad(double probabilidad) {
        return Math.random() < probabilidad;
    }

    private void dibujarSimulacion() {
        System.out.println();
        for (int i = 0; i < cajasActivas; i++) {
            cajas[i].mostrarFila(i + 1);
        }
        System.out.println();
    }

    private void mostrarReloj() {
        System.out.printf("[%02d:%02d] ", horas, minutos);
    }

    private void avanzarTiempo() {
        final int MINUTOS_EN_HORA = 60;
        minutos = minutos + 1;

        if (minutos == MINUTOS_EN_HORA) {
            minutos = 0;
            horas = horas + 1;
        }
    }
}