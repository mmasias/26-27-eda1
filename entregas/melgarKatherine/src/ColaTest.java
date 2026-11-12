public class Main {
    public static void main(String[] args) {
        Cola miCola = new Cola();
        System.out.println("Ejecutando secuencia de prueba del diseño...");

        miCola.encolar(1);
        miCola.encolar(2);
        miCola.encolar(3);

        int resultado1 = miCola.desencolar();
        System.out.println("Primer desencolar: " + resultado1 + " (Esperado: 1)");
        miCola.encolar(4);

        int resultado2 = miCola.desencolar();
        System.out.println("Segundo desencolar: " + resultado2 + " (Esperado: 2)");
        
        int resultado3 = miCola.desencolar();
        System.out.println("Tercer desencolar: " + resultado3 + " (Esperado: 3)");
    }
}