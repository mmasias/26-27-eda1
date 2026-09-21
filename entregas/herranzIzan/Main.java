public class Main {
 
    public static void main(String[] args) {
        String modo = args.length > 0 ? args[0] : "completo";
        long semilla = args.length > 1 ? Long.parseLong(args[1]) : System.nanoTime();
 
        if (modo.equals("base")) {
            Simulacion sim = new Simulacion(240, false, semilla);
            sim.ejecutar();
            sim.imprimirResumen(false);
        } else if (modo.equals("completo")) {
            Simulacion sim = new Simulacion(120, true, semilla);
            int[] longitudes = sim.ejecutar();
            System.out.println("Minuto | Longitud de la fila (m)");
            for (int i = 0; i < longitudes.length; i++) {
                System.out.printf("%6d | %d%n", i + 1, longitudes[i]);
            }
            sim.imprimirResumen(true);
        } else {
            System.out.println("Modo no válido. Usa: base | completo [semilla]");
        }
    }
}