public class Cliente {
    public static void main(String[] args) {
        Cola cola = new Cola();
        System.out.println("Cola creada -> ¿Está vacía?: " + cola.estaVacia());
        assert cola.estaVacia() : "La cola debería empezar vacía";

        cola.encolar(1);
        System.out.println("encolar(1) -> Estado lógico: [1] | ¿Vacía?: " + cola.estaVacia());
        assert !cola.estaVacia() : "La cola no debería estar vacía";

        cola.encolar(2);
        System.out.println("encolar(2) -> Estado lógico: [1, 2]");

        cola.encolar(3);
        System.out.println("encolar(3) -> Estado lógico: [1, 2, 3]");

        int salida1 = cola.desencolar();
        System.out.println("desencolar() -> devuelve: " + salida1 + " | Estado lógico: [2, 3]");
        assert salida1 == 1 : "Error en 1er desencolar: esperado 1, obtenido " + salida1;

        cola.encolar(4);
        System.out.println("encolar(4) -> Estado lógico: [2, 3, 4]");

        int salida2 = cola.desencolar();
        System.out.println("desencolar() -> devuelve: " + salida2 + " | Estado lógico: [3, 4]");
        assert salida2 == 2 : "Error en 2do desencolar: esperado 2, obtenido " + salida2;

        int salida3 = cola.desencolar();
        System.out.println("desencolar() -> devuelve: " + salida3 + " | Estado lógico: [4]");
        assert salida3 == 3 : "Error en 3er desencolar: esperado 3, obtenido " + salida3;
        assert !cola.estaVacia() : "Todavía debería quedar el 4 en la cola";

        int salida4 = cola.desencolar();
        System.out.println("desencolar() -> devuelve: " + salida4 + " | Estado lógico: [] | ¿Vacía?: " + cola.estaVacia());
        assert salida4 == 4 : "Error en 4to desencolar: esperado 4, obtenido " + salida4;
        assert cola.estaVacia() : "La cola debería quedar vacía";

        System.out.println("\nTodas las pruebas de la secuencia han pasado correctamente.");
    }
}