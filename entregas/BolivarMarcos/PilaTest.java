public class PilaTest {
    private static int fallos = 0;

    private static void comprobar(String paso, int esperado, int obtenido) {
        if (esperado != obtenido) fallos++;
        System.out.println((esperado == obtenido ? "OK    " : "FALLO ") + paso + " -> esperado " + esperado + ", obtenido " + obtenido);
    }

    private static void comprobar(String paso, boolean esperado, boolean obtenido) {
        if (esperado != obtenido) fallos++;
        System.out.println((esperado == obtenido ? "OK    " : "FALLO ") + paso + " -> esperado " + esperado + ", obtenido " + obtenido);
    }

    public static void main(String[] args) {
        Pila p = new Pila();
        comprobar("pila nueva estaVacia()", true, p.estaVacia());
        p.apilar(1);
        p.apilar(2);
        p.apilar(3);
        comprobar("tras apilar 1, 2, 3 estaVacia()", false, p.estaVacia());
        comprobar("desapilar() 1", 3, p.desapilar());
        p.apilar(4);
        comprobar("desapilar() 2", 4, p.desapilar());
        comprobar("desapilar() 3", 2, p.desapilar());
        comprobar("queda un elemento estaVacia()", false, p.estaVacia());
        comprobar("el elemento que queda", 1, p.desapilar());
        comprobar("al final estaVacia()", true, p.estaVacia());

        boolean saltaAssert = false;
        try {
            new Pila().desapilar();
        } catch (AssertionError e) {
            saltaAssert = true;
        }
        comprobar("desapilar() con la pila vacia salta el assert", true, saltaAssert);

        System.out.println(fallos == 0 ? "\nTODAS LAS PRUEBAS PASAN" : "\nFALLOS: " + fallos);
        if (fallos != 0) System.exit(1);
    }
}