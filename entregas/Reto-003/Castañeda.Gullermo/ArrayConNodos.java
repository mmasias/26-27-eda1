import java.util.Scanner;

public class ArrayConNodos {

    static class Nodo {
        String valor;
        Nodo siguiente;

        Nodo(String valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }

    private Nodo cabeza;
    private final int tamano;

    public ArrayConNodos(int tamano) {
        this.tamano = tamano;
        this.cabeza = new Nodo(null);
        Nodo actual = this.cabeza;

        for (int i = 1; i < tamano; i++) {
            actual.siguiente = new Nodo(null);
            actual = actual.siguiente;
        }
    }

    public void set(int indice, String valor) {
        validarIndice(indice);
        Nodo actual = obtenerNodo(indice);
        actual.valor = valor;
    }

    public String get(int indice) {
        validarIndice(indice);
        Nodo actual = obtenerNodo(indice);
        return actual.valor;
    }

    public int length() {
        return tamano;
    }

    private Nodo obtenerNodo(int indice) {
        Nodo actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual;
    }

    private void validarIndice(int indice) {
        if (indice < 0 || indice >= tamano) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + indice);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce el tamaño del array: ");
        int tamano = scanner.nextInt();
        scanner.nextLine();

        ArrayConNodos array = new ArrayConNodos(tamano);

        System.out.println("\n--- AÑADIR ELEMENTOS INICIALES ---");
        System.out.println("(Si quieres dejar la casilla vacía, simplemente pulsa ENTER)");
        for (int i = 0; i < array.length(); i++) {
            System.out.print("Introduce el valor para el índice " + i + ": ");
            String entrada = scanner.nextLine().trim();
            if (!entrada.isEmpty()) {
                array.set(i, entrada);
            }
        }

        boolean salir = false;
        while (!salir) {
            System.out.println("\n==================================");
            System.out.println("--- ESTADO ACTUAL DEL ARRAY ---");
            for (int i = 0; i < array.length(); i++) {
                String val = array.get(i);
                System.out.println("Índice " + i + ": " + (val == null ? "[VACÍO]" : val));
            }
            System.out.println("==================================");
            System.out.println("1. Rellenar / Modificar una posición específica");
            System.out.println("2. Salir");
            System.out.print("Selecciona una opción: ");

            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    System.out.print("Introduce el índice que quieres rellenar/editar (0 a " + (array.length() - 1) + "): ");
                    int idx = scanner.nextInt();
                    scanner.nextLine();

                    if (idx >= 0 && idx < array.length()) {
                        System.out.print("Introduce el nuevo valor (deja vacío para dejarlo en [VACÍO]): ");
                        String nuevoVal = scanner.nextLine().trim();
                        array.set(idx, nuevoVal.isEmpty() ? null : nuevoVal);
                    } else {
                        System.out.println("¡Índice no válido!");
                    }
                    break;

                case "2":
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        System.out.println("\n--- CONTENIDO FINAL DEL ARRAY ---");
        for (int i = 0; i < array.length(); i++) {
            String val = array.get(i);
            System.out.println("Índice " + i + ": " + (val == null ? "[VACÍO]" : val));
        }

        scanner.close();
    }
}