public class ColaTest {

    private static void verificar(String paso, int esperado, int obtenido) {
        if (esperado != obtenido) {
            throw new RuntimeException(paso + ": esperado " + esperado + " pero se obtuvo " + obtenido);
        }
        System.out.println("OK  " + paso + " -> " + obtenido);
    }

    private static void verificar(String paso, boolean esperado, boolean obtenido) {
        if (esperado != obtenido) {
            throw new RuntimeException(paso + ": esperado " + esperado + " pero se obtuvo " + obtenido);
        }
        System.out.println("OK  " + paso + " -> " + obtenido);
    }

    public static void main(String[] args) {
        Cola c = new Cola();
        verificar("cola nueva estaVacia()", true, c.estaVacia());

        c.encolar(1);
        c.encolar(2);
        c.encolar(3);
        verificar("tras encolar(1,2,3) estaVacia()", false, c.estaVacia());

        verificar("desencolar() #1", 1, c.desencolar());
        c.encolar(4);
        verificar("desencolar() #2", 2, c.desencolar());
        verificar("desencolar() #3", 3, c.desencolar());
        verificar("estaVacia() con el 4 pendiente", false, c.estaVacia());

        verificar("desencolar() #4 (extra)", 4, c.desencolar());
        verificar("cola vacía al final", true, c.estaVacia());

        System.out.println("Todas las pruebas pasaron.");
    }
}