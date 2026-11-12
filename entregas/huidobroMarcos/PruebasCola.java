
public class PruebasCola {

    private static int fallos = 0;

    public static void main(String[] args) {

        Cola cola = new Cola();

        comprobar("Cola inicialmente vacia",
                cola.estaVacia(), true);

        cola.encolar(1);
        comprobar("Cola despues de encolar 1",
                cola.estaVacia(), false);

        cola.encolar(2);
        cola.encolar(3);

        comprobar("Desencolar devuelve 1",
                cola.desencolar(), 1);

        cola.encolar(4);

        comprobar("Desencolar devuelve 2",
                cola.desencolar(), 2);

        comprobar("Desencolar devuelve 3",
                cola.desencolar(), 3);

        comprobar("La cola todavia tiene elementos",
                cola.estaVacia(), false);

        comprobar("Desencolar devuelve 4",
                cola.desencolar(), 4);

        comprobar("La cola finalmente esta vacia",
                cola.estaVacia(), true);

        System.out.println();

        if (fallos == 0) {
            System.out.println("Todas las pruebas superadas.");
        } else {
            System.out.println(fallos + " prueba(s) fallida(s).");
        }
    }

    private static void comprobar(
            String descripcion, int obtenido, int esperado) {

        registrar(descripcion, obtenido == esperado);
    }

    private static void comprobar(
            String descripcion, boolean obtenido, boolean esperado) {

        registrar(descripcion, obtenido == esperado);
    }

    private static void registrar(
            String descripcion, boolean correcto) {

        if (correcto) {
            System.out.println("OK: " + descripcion);
        } else {
            fallos++;
            System.out.println("FALLO: " + descripcion);
        }
    }
}
