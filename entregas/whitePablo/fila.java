import java.util.Scanner;

public class fila {
    public static void main(String[] args) {
        final int TOTAL_MINUTOS = 240;
        final double PROB_LLEGADA = 0.6;
        final double PROB_APERTURA = 0.4;
        final int MAX_FILA = 240;

        int[] fila = new int[MAX_FILA];
        int inicio = 0;
        int fin = 0;
        int atendidas = 0;
        int personasEnFila = 0;

        System.out.println("--- Iniciando simulacion de 4 horas (240 minutos) ---");

        for (int minuto = 1; minuto <= TOTAL_MINUTOS; minuto++) {
            
            double chanceLlegada = Math.random();
            if (chanceLlegada < PROB_LLEGADA) {
                if (fin < MAX_FILA) {
                    fila[fin] = minuto;
                    fin++;
                    personasEnFila++;
                }
            }

            double chanceApertura = Math.random();
            if (chanceApertura < PROB_APERTURA) {
                if (inicio < fin) {
                    inicio++;
                    personasEnFila--;
                    atendidas++;
                }
            }
        }

        System.out.println("----------------------------------------");
        System.out.println("Cierre del centro comercial.");
        System.out.println("Personas atendidas: " + atendidas);
        System.out.println("Personas que quedaron en fila: " + personasEnFila);
        System.out.println("----------------------------------------");
    }
}