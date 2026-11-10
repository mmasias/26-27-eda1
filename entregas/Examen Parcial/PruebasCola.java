public class PruebasCola {
    public static void main(String[] args) {
        Cola cola = new Cola();

        System.out.println("--- Iniciando secuencia de pruebas ---");

        cola.encolar(1);
        cola.encolar(2);
        cola.encolar(3);
        System.out.println("Elementos encolados: 1, 2, 3");

        int r1 = cola.desencolar();
        System.out.println("desencolar() obtenido: " + r1 + " (Esperado: 1)");
        assert r1 == 1 : "Error: Se esperaba 1";

        cola.encolar(4);
        System.out.println("Elemento encolado: 4");

        int r2 = cola.desencolar();
        System.out.println("desencolar() obtenido: " + r2 + " (Esperado: 2)");
        assert r2 == 2 : "Error: Se esperaba 2";

        int r3 = cola.desencolar();
        System.out.println("desencolar() obtenido: " + r3 + " (Esperado: 3)");
        assert r3 == 3 : "Error: Se esperaba 3";

        System.out.println("--- ¡Pruebas superadas con éxito! ---");
    }
}