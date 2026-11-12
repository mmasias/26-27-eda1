public class PruebasCola {
    private static int fallos = 0;

    public static void main(String[] args) {
        Cola cola = new Cola();

        comprobar("new Cola() -> vacia", cola.estaVacia(), true);

        cola.encolar(1);
        comprobar("encolar(1) -> no vacia", cola.estaVacia(), false);

        cola.encolar(2);
        cola.encolar(3);

        comprobar("desencolar() devuelve 1", cola.desencolar(), 1);

        cola.encolar(4);

        comprobar("desencolar() devuelve 2", cola.desencolar(), 2);
        comprobar("desencolar() devuelve 3", cola.desencolar(), 3);

        comprobar("queda el 4 -> no vacia", cola.estaVacia(), false);
        comprobar("desencolar() devuelve 4", cola.desencolar(), 4);
        comprobar("al final -> vacia", cola.estaVacia(), true);

        System.out.println();
        if (fallos == 0) {
            System.out.println("Todas las pruebas superadas.");
        } else {
            System.out.println(fallos + " prueba(s) fallida(s).");
        }
    }

    private static void comprobar(String descripcion, int obtenido, int esperado) {
        registrar(descripcion, obtenido == esperado, "" + obtenido, "" + esperado);
    }

    private static void comprobar(String descripcion, boolean obtenido, boolean esperado) {
        registrar(descripcion, obtenido == esperado, "" + obtenido, "" + esperado);
    }

    private static void registrar(String descripcion, boolean ok, String obtenido, String esperado) {
        if (ok) {
            System.out.println("OK    " + descripcion);
        } else {
            fallos++;
            System.out.println("FALLO " + descripcion + " (obtenido " + obtenido + ", esperado " + esperado + ")");
        }
    }
}
