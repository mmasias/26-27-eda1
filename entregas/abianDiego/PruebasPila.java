public class PruebasPila {

    private static int fallos = 0;

    private static void comprobar(String que, int esperado, int obtenido) {
        if (esperado == obtenido) {
            System.out.println("OK    " + que + " -> " + obtenido);
        } else {
            System.out.println("FALLA " + que + " -> esperaba " + esperado + " y sale " + obtenido);
            fallos++;
        }
    }

    private static void comprobar(String que, boolean cumple) {
        if (cumple) {
            System.out.println("OK    " + que);
        } else {
            System.out.println("FALLA " + que);
            fallos++;
        }
    }

    public static void main(String[] args) {
        Pila pila = new Pila();

        pila.apilar(1);
        pila.apilar(2);
        pila.apilar(3);

        comprobar("1er desapilar() saca el ultimo que entro", 3, pila.desapilar());

        pila.apilar(4);

        comprobar("2o desapilar() saca el 4", 4, pila.desapilar());
        comprobar("3er desapilar() saca el 2", 2, pila.desapilar());

        comprobar("al acabar la secuencia la pila no esta vacia", !pila.estaVacia());
        comprobar("lo que queda es el 1", 1, pila.desapilar());
        comprobar("y era lo unico que quedaba", pila.estaVacia());

        if (fallos == 0) {
            System.out.println("Todas las pruebas OK");
        } else {
            System.out.println(fallos + " pruebas fallan");
            System.exit(1);
        }
    }
}
