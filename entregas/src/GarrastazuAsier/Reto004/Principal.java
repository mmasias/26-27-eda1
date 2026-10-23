package GarrastazuAsier.Reto004;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        Eleccion eleccion = new Eleccion();

        int opcion;

        do {
            System.out.println("\n===== SISTEMA ELECTORAL =====");
            System.out.println("1. Añadir alumno al censo");
            System.out.println("2. Mostrar censo");
            System.out.println("3. Añadir candidato");
            System.out.println("4. Mostrar candidatos");
            System.out.println("5. Abrir votación");
            System.out.println("6. Votar");
            System.out.println("7. Cerrar votación y contar votos");
            System.out.println("8. Mostrar resultados");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            while (!teclado.hasNextInt()) {
                System.out.println("Introduce un número válido.");
                teclado.next();
                System.out.print("Elige una opción: ");
            }

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Introduce el identificador y nombre del alumno: ");
                    String alumno = teclado.nextLine();
                    eleccion.agregarAlumno(alumno);
                    break;

                case 2:
                    eleccion.mostrarCenso();
                    break;

                case 3:
                    System.out.print("Nombre del candidato: ");
                    String nombre = teclado.nextLine();

                    System.out.print("Partido: ");
                    String partido = teclado.nextLine();

                    eleccion.agregarCandidato(nombre, partido);
                    break;

                case 4:
                    eleccion.mostrarCandidatos();
                    break;

                case 5:
                    eleccion.abrirVotacion();
                    break;

                case 6:
                    System.out.print("Identificador del alumno: ");
                    String identificador = teclado.nextLine();

                    System.out.println("Introduce el nombre del candidato elegido.");
                    System.out.println("Deja el campo vacío para votar en blanco.");
                    System.out.print("Voto: ");
                    String voto = teclado.nextLine();

                    eleccion.votar(identificador, voto);
                    break;

                case 7:
                    eleccion.cerrarVotacionYContar();
                    break;

                case 8:
                    eleccion.mostrarResultados();
                    break;

                case 0:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        teclado.close();
    }
}
