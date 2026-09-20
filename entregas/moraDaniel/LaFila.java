package entregas.moraDaniel;

public class LaFila {
    public static void main(String[] argumentosDeLineaDeComandos) {
        System.out.println(" Reto base: 4 horas, sin reglas extendidas ");
        Simulador simuladorDelRetoBase = new Simulador();
        simuladorDelRetoBase.simular(240, false);

        System.out.println();
        System.out.println(" Reto completo: 2 horas, reglas extendidas desde el minuto 20 ");
        Simulador simuladorDelRetoCompleto = new Simulador();
        simuladorDelRetoCompleto.simular(120, true);
    }
}