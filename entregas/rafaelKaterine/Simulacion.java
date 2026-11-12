
public class Simulacion {

    public static void main(String[] args) {

        Pila pila = new Pila();

        int[] visual = new int[100];
        int cantidad = 0;

        System.out.println("\n");
        System.out.println("==================================");
        System.out.println("         SIMULACION DE PILA");
        System.out.println("==================================");

        pila.apilar(1);
        visual[cantidad++] = 1;

        System.out.println("\n >>> APILAR(1)");
        System.out.println(" Elemento introducido: 1");
        System.out.println(" Pila actual:");

        for (int i = cantidad - 1; i >= 0; i--) {
            if (i == cantidad - 1) {
                System.out.println("       TOPE");
            }
            System.out.println("      +-----+");
            System.out.printf("      | %3d |%n", visual[i]);
        }
        System.out.println("      +-----+");

        pila.apilar(2);
        visual[cantidad++] = 2;

        System.out.println("\n >>> APILAR(2)");
        System.out.println(" Elemento introducido: 2");
        System.out.println(" Pila actual:");

        for (int i = cantidad - 1; i >= 0; i--) {
            if (i == cantidad - 1) {
                System.out.println("       TOPE");
            }
            System.out.println("      +-----+");
            System.out.printf("      | %3d |%n", visual[i]);
        }
        System.out.println("      +-----+");

        pila.apilar(3);
        visual[cantidad++] = 3;

        System.out.println("\n >>> APILAR(3)");
        System.out.println(" Elemento introducido: 3");
        System.out.println(" Pila actual:");

        for (int i = cantidad - 1; i >= 0; i--) {
            if (i == cantidad - 1) {
                System.out.println("       TOPE");
            }
            System.out.println("      +-----+");
            System.out.printf("      | %3d |%n", visual[i]);
        }
        System.out.println("      +-----+");

        int valor = pila.desapilar();
        cantidad--;

        System.out.println("\n >>> DESAPILAR()");
        System.out.println(" Elemento extraido: " + valor);
        System.out.println(" Pila actual:");

        for (int i = cantidad - 1; i >= 0; i--) {
            if (i == cantidad - 1) {
                System.out.println("       TOPE");
            }
            System.out.println("      +-----+");
            System.out.printf("      | %3d |%n", visual[i]);
        }
        System.out.println("      +-----+");

        pila.apilar(4);
        visual[cantidad++] = 4;

        System.out.println("\n >>> APILAR(4)");
        System.out.println(" Elemento introducido: 4");
        System.out.println(" Pila actual:");

        for (int i = cantidad - 1; i >= 0; i--) {
            if (i == cantidad - 1) {
                System.out.println("       TOPE");
            }
            System.out.println("      +-----+");
            System.out.printf("      | %3d |%n", visual[i]);
        }
        System.out.println("      +-----+");

        valor = pila.desapilar();
        cantidad--;

        System.out.println("\n >>> DESAPILAR()");
        System.out.println(" Elemento extraido: " + valor);
        System.out.println(" Pila actual:");

        for (int i = cantidad - 1; i >= 0; i--) {
            if (i == cantidad - 1) {
                System.out.println("       TOPE");
            }
            System.out.println("      +-----+");
            System.out.printf("      | %3d |%n", visual[i]);
        }
        System.out.println("      +-----+");

        valor = pila.desapilar();
        cantidad--;

        System.out.println("\n >>> DESAPILAR()");
        System.out.println(" Elemento extraido: " + valor);
        System.out.println(" Pila actual:");

        for (int i = cantidad - 1; i >= 0; i--) {
            if (i == cantidad - 1) {
                System.out.println("       TOPE");
            }
            System.out.println("      +-----+");
            System.out.printf("      | %3d |%n", visual[i]);
        }

        if (cantidad == 0) {
            System.out.println("       VACIA");
        }

        System.out.println("      +-----+");

        System.out.println("\n==================================");
        System.out.println("           RESUMEN");
        System.out.println("==================================");
        System.out.println(" Valores extraidos: 3, 4, 2");
        System.out.println(" Elemento restante: 1");
        System.out.println(" ¿Pila vacia?: " + pila.estaVacia());
        System.out.println("==================================");
    }
}
