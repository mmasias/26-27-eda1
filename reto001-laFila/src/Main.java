public class Main {

    public static void main(String[] args) {
        System.out.println("=== Reto base (4 horas) ===");
        Simulador simuladorBase = new Simulador(Integer.MAX_VALUE);
        simuladorBase.correrBase(240);

        System.out.println();
        System.out.println("=== Reto extendido (2 horas) ===");
        Simulador simuladorExtendido = new Simulador(30);
        simuladorExtendido.correrExtendido(120);
    }
}