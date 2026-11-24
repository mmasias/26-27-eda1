public class Main {

    public static void main(String[] args) {

        probarCola();
        probarPila();
        probarValoresEspeciales();
        probarPrecondiciones();

        System.out.println("\nTodas las pruebas superadas.");
    }

    private static void probarCola() {

        System.out.println("=== PRUEBA DE LA COLA ===");

        Cola cola = new Cola();

        verificar(cola.estaVacia(), "Cola inicialmente vacia");

        cola.encolar(1);
        cola.encolar(2);
        cola.encolar(3);

        verificar(!cola.estaVacia(), "Cola con elementos");

        verificarValor(1, cola.desencolar());

        cola.encolar(4);

        verificarValor(2, cola.desencolar());
        verificarValor(3, cola.desencolar());
        verificarValor(4, cola.desencolar());

        verificar(cola.estaVacia(), "Cola finalmente vacia");
    }

    private static void probarPila() {

        System.out.println("\n=== PRUEBA DE LA PILA ===");

        PilaConCola pila = new PilaConCola();

        verificar(pila.estaVacia(), "Pila inicialmente vacia");

        pila.apilar(1);
        pila.apilar(2);
        pila.apilar(3);

        verificar(!pila.estaVacia(), "Pila con elementos");

        verificarValor(3, pila.desapilar());

        pila.apilar(4);

        verificarValor(4, pila.desapilar());
        verificarValor(2, pila.desapilar());
        verificarValor(1, pila.desapilar());

        verificar(pila.estaVacia(), "Pila finalmente vacia");
    }

    private static void probarValoresEspeciales() {

        System.out.println("\n=== VALORES ESPECIALES ===");

        PilaConCola pila = new PilaConCola();

        pila.apilar(-5);
        pila.apilar(0);
        pila.apilar(10);
        pila.apilar(10);

        verificarValor(10, pila.desapilar());
        verificarValor(10, pila.desapilar());
        verificarValor(0, pila.desapilar());
        verificarValor(-5, pila.desapilar());

        verificar(pila.estaVacia(), "Pila vacia");
        pila.apilar(100);

        verificarValor(100, pila.desapilar());
        verificar(pila.estaVacia(), "Pila reutilizada");
    }

    private static void probarPrecondiciones() {

        System.out.println("\n=== PRECONDICIONES ===");

        Cola cola = new Cola();
        PilaConCola pila = new PilaConCola();

        boolean errorCola = false;
        boolean errorPila = false;

        try {
            cola.desencolar();
        } catch (AssertionError e) {
            errorCola = true;
        }

        try {
            pila.desapilar();
        } catch (AssertionError e) {
            errorPila = true;
        }

        verificar(errorCola, "Precondicion de la cola");
        verificar(errorPila, "Precondicion de la pila");
    }

    private static void verificarValor(int esperado, int obtenido) {

        verificar(
            esperado == obtenido,
            "Esperado: " + esperado + " | Obtenido: " + obtenido
        );
    }

    private static void verificar(boolean condicion, String mensaje) {

        if (!condicion) {
            throw new AssertionError("ERROR: " + mensaje);
        }

        System.out.println("OK - " + mensaje);
    }
}