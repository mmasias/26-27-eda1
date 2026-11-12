public class Main {
    public static void main(String[] args) {
        Cola cola = new Cola();

        cola.encolar(1);
        cola.encolar(2);
        cola.encolar(3);

        int resultado1 = cola.desencolar();
        System.out.println("Se esperaba 1, se obtuvo: " + resultado1);
        assert resultado1 == 1 : "Fallo en el primer desencolar";

        cola.encolar(4);

        int resultado2 = cola.desencolar();
        System.out.println("Se esperaba 2, se obtuvo: " + resultado2);
        assert resultado2 == 2 : "Fallo en el segundo desencolar";

        int resultado3 = cola.desencolar();
        System.out.println("Se esperaba 3, se obtuvo: " + resultado3);
        assert resultado3 == 3 : "Fallo en el tercer desencolar";
        
        System.out.println("Todas las pruebas pasaron correctamente.");
    }
}