class CajaRetoBase{
public static void main(String[] args) {
        int minutosTotales = 4 * 60;
        int enFila = 0;
        int atendidos = 0;

        for (int min = 1; min <= minutosTotales; min++) {
            if (Math.random() < 0.6) {
                enFila++;
            }

            if (Math.random() < 0.4) {
                if (enFila > 0) {
                    enFila--;
                    atendidos++;
                }
            }
        }

        System.out.println("Resultados al cierre:");
        System.out.println("Personas atendidas: " + atendidos);
        System.out.println("Personas en fila: " + enFila);
    }
}   


