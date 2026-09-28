/** Punto de entrada: ejecuta el reto base y el reto extendido. */
public class Main {

    public static void main(String[] args) {
        Simulacion simulacion = new Simulacion();

        System.out.println("=== RETO BASE (240 min, 4 horas) ===");
        simulacion.ejecutar(240, false);

        System.out.println();
        System.out.println("=== RETO EXTENDIDO (120 min, 2 horas) ===");
        simulacion.ejecutar(120, true);
    }
}