package entregas.retoEstructuradeDatos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("=== LISTA CON ALMA DE ARRAY ===");
        System.out.print("¿De cuantos elementos quieres tu array? ");
        int tamaño = entrada.nextInt();
        ArraySimulado array = new ArraySimulado(tamaño);

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
                    rellenarArray(entrada, array, tamaño);
                    break;
                case 5:
                    System.out.println("La lista se despide. Sus nodos quedan en paz.");
                    break;
                default:
                    System.out.println("Opcion no reconocida.");
            }
        } while (opcion != 5);

        entrada.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- MENU DE OPERACIONES ---");
        System.out.println("1. Guardar o modificar un valor");
        System.out.println("2. Consultar un valor por posicion");
        System.out.println("3. Mostrar la lista completa");
        System.out.println("4. Rellenar todos los huecos");
        System.out.println("5. Salir");
        System.out.print("Elige una opcion: ");
    }

    private static void guardarValor(Scanner entrada, ArraySimulado array) {
        System.out.print("Posicion: ");
        int posicion = entrada.nextInt();
        System.out.print("Valor: ");
        String valor = entrada.next();
        array.guardar(posicion, valor);
        System.out.println("Valor guardado.");
    }

    private static void consultarValor(Scanner entrada, ArraySimulado array) {
        System.out.print("Que posicion quieres consultar: ");
        int posicion = entrada.nextInt();
        String valor = array.obtener(posicion);
        if (valor != null) {
            System.out.println("En la posicion " + posicion + " esta: " + valor);
        }
    }

    private static void rellenarArray(Scanner entrada, ArraySimulado array, int tamaño) {
        System.out.println("Vamos a recorrer los nodos como si fueran casillas de un array.");
        for (int posicion = 0; posicion < tamaño; posicion++) {
            System.out.print("Valor para la posicion " + posicion + ": ");
            array.guardar(posicion, entrada.next());
        }
        System.out.println("Array completado.");
    }
}
