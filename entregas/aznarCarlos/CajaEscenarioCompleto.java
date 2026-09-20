public class CajaEscenarioCompleto {

    public static void main(String[] args) {
        int minutosTotales = 2 * 60;
        int enFila = 0;
        int atendidos = 0;

        for (int min = 1; min <= minutosTotales; min++) {
            double probLlegada = 0.6;
            double probAtencion = 0.4;

            if (min >= 20) {
                probLlegada = 0.6;
                probAtencion = 0.8;
            }

            if (Math.random() < probLlegada) {
                enFila++;
            }

            if (Math.random() < probAtencion) {
                if (enFila > 0) {
                    enFila--;
                    atendidos++;
                }
            }

            System.out.println("Minuto " + min + ": " + enFila + " metros");
        }

        System.out.println("----------------------------------------------------");
        System.out.println("Resultados al cierre:");
        System.out.println("Personas atendidas: " + atendidos);
        System.out.println("Personas en fila: " + enFila + " (" + enFila + " metros)");
    }
}