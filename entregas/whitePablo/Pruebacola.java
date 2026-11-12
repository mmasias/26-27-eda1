public class PruebaCola {

    private static int fallos = 0;

    public static void main(String[] args) {
        Cola cola = new Cola();
        comprobar("cola nueva vacía", cola.estaVacia(), true);

        cola.encolar(1);
        comprobar("tras encolar(1) no vacía", cola.estaVacia(), false);

        cola.encolar(2);
        comprobar("tras encolar(2) no vacía", cola.estaVacia(), false);

        cola.encolar(3);
        comprobar("tras encolar(3) no vacía", cola.estaVacia(), false);

        comprobar("1er desencolar()", cola.desencolar(), 1);
        comprobar("tras 1er desencolar() no vacía", cola.estaVacia(), false);

        cola.encolar(4);
        comprobar("tras encolar(4) no vacía", cola.estaVacia(), false);

        comprobar("2º desencolar()", cola.desencolar(), 2);
        comprobar("tras 2º desencolar() no vacía", cola.estaVacia(), false);

        comprobar("3er desencolar()", cola.desencolar(), 3);
        comprobar("al final queda el 4: no vacía", cola.estaVacia(), false);

        comprobar("desencolar() restante", cola.desencolar(), 4);
        comprobar("cola vacía al final", cola.estaVacia(), true);

        boolean assertsActivos = false;
        assert assertsActivos = true;
        if (assertsActivos) {
            boolean saltoElAssert = false;
            try {
                cola.desencolar();
            } catch (AssertionError e) {
                saltoElAssert = true;
            }
            comprobar("desencolar() con cola vacía dispara el assert", saltoElAssert, true);
        } else {
            System.out.println("AVISO ejecute con -ea para comprobar la precondición");
        }

        System.out.println(fallos == 0 ? "TODAS LAS PRUEBAS OK" : fallos + " FALLO(S)");
        if (fallos > 0) {
            System.exit(1);
        }
    }

    private static void comprobar(String descripcion, int obtenido, int esperado) {
        informar(descripcion, obtenido == esperado, String.valueOf(obtenido), String.valueOf(esperado));
    }

    private static void comprobar(String descripcion, boolean obtenido, boolean esperado) {
        informar(descripcion, obtenido == esperado, String.valueOf(obtenido), String.valueOf(esperado));
    }

    private static void informar(String descripcion, boolean ok, String obtenido, String esperado) {
        if (ok) {
            System.out.println("OK    " + descripcion + " -> " + obtenido);
        } else {
            fallos++;
            System.out.println("FALLO " + descripcion + " -> " + obtenido + " (esperado " + esperado + ")");
        }
    }
}