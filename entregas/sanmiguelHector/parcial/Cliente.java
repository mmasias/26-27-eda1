public class Cliente {
    public static void main(String[] args) {
        Cola cola = new Cola();
        assert cola.estaVacia() : "La cola debería empezar vacía";

        cola.encolar(1);
        assert !cola.estaVacia() : "La cola no debería estar vacía";

        cola.encolar(2);

        cola.encolar(3);

        int salida1 = cola.desencolar();
        assert salida1 == 1 : "Error en 1er desencolar: esperado 1, obtenido " + salida1;

        cola.encolar(4);

        int salida2 = cola.desencolar();
        assert salida2 == 2 : "Error en 2do desencolar: esperado 2, obtenido " + salida2;

        int salida3 = cola.desencolar();
        assert salida3 == 3 : "Error en 3er desencolar: esperado 3, obtenido " + salida3;
        assert !cola.estaVacia() : "Todavía debería quedar el 4 en la cola";

        int salida4 = cola.desencolar();
        assert salida4 == 4 : "Error en 4to desencolar: esperado 4, obtenido " + salida4;
        assert cola.estaVacia() : "La cola debería quedar vacía";

        System.out.println("Todas las pruebas de la secuencia han pasado correctamente.");
    }
}