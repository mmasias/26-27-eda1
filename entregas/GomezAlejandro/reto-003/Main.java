package entregas.GomezAlejandro.reto_003;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        System.out.print("De cuantos elementos quieres el array: ");
        int tamaño = entrada.nextInt();

        if (tamaño < 0) {
            System.out.println("El tamaño no puede ser negativo.");
            entrada.close();
            return;
        }

        Array array = new Array(tamaño);
        int opcion;

        do {
            mostrarMenu();
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1:
                    guardarValor(entrada, array);
                    break;

                case 2:
                    consultarValor(entrada, array);
                    break;

                case 3:
                    array.mostrar();
                    break;

                case 4:
                    rellenarArray(entrada, array);
                    break;

                case 5:
                    System.out.println("Fin del programa.");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 5);

        entrada.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Guardar o modificar un valor");
        System.out.println("2. Consultar un valor");
        System.out.println("3. Mostrar el array");
        System.out.println("4. Rellenar el array");
        System.out.println("5. Salir");
        System.out.print("Opcion: ");
    }

    private static void guardarValor(Scanner entrada, Array array) {
        System.out.print("Posicion: ");
        int posicion = entrada.nextInt();

        if (posicion >= 0 && posicion < array.longitud()) {
            System.out.print("Valor: ");
            int valor = entrada.nextInt();

            array.asignar(posicion, valor);
            System.out.println("Valor guardado.");
        } else {
            System.out.println("Posicion no valida.");
        }
    }

    private static void consultarValor(Scanner entrada, Array array) {
        System.out.print("Posicion: ");
        int posicion = entrada.nextInt();

        if (posicion >= 0 && posicion < array.longitud()) {
            System.out.println("Valor: " + array.obtener(posicion));
        } else {
            System.out.println("Posicion no valida.");
        }
    }

    private static void rellenarArray(Scanner entrada, Array array) {
        for (int i = 0; i < array.longitud(); i++) {
            System.out.print("Valor para la posicion " + i + ": ");
            array.asignar(i, entrada.nextInt());
        }

        System.out.println("Array completado.");
    }
}