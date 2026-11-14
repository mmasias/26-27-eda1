public class Pruebas {

    private static int fallos = 0;

    private static void comprobar(int esperado, int obtenido) {
        if (esperado != obtenido) {
            System.out.println("FALLA: esperaba " + esperado + " y sale " + obtenido);
            fallos = fallos + 1;
        }
    }

    private static void comprobar(boolean cumple) {
        if (!cumple) {
            System.out.println("FALLA: la estructura tenia que quedar vacia");
            fallos = fallos + 1;
        }
    }

    public static void main(String[] args) {

        Cola cola = new Cola();
        cola.encolar(1);
        cola.encolar(2);
        cola.encolar(3);
        comprobar(1, cola.desencolar());
        cola.encolar(4);
        comprobar(2, cola.desencolar());
        comprobar(3, cola.desencolar());
        comprobar(4, cola.desencolar());
        comprobar(cola.estaVacia());

        Pila pila = new Pila();
        pila.apilar(1);
        pila.apilar(2);
        pila.apilar(3);
        comprobar(3, pila.desapilar());
        pila.apilar(4);
        comprobar(4, pila.desapilar());
        comprobar(2, pila.desapilar());
        comprobar(1, pila.desapilar());
        comprobar(pila.estaVacia());

        if (fallos == 0) {
            System.out.println("Todo correcto");
        } else {
            System.out.println(fallos + " pruebas fallan");
        }
    }
}
