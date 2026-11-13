
public class Main {

    public static void main(String[] args) {

        System.out.println("==================================");
        System.out.println("       PRUEBAS DE ESTRUCTURAS");
        System.out.println("==================================\n");

        probarCola();
        probarPila();

        System.out.println("==================================");
        System.out.println("       FIN DE LAS PRUEBAS");
        System.out.println("==================================");
    }

    private static void probarCola() {

        Cola cola = new Cola();

        System.out.println("\n==================================");
        System.out.println("   COLA IMPLEMENTADA CON PILAS");
        System.out.println("==================================\n");

        System.out.println(">>> OPERACION 1: ENCOLAR(1)");
        cola.encolar(1);
        imprimirEstadoCola(cola);

        System.out.println(">>> OPERACION 2: ENCOLAR(2)");
        cola.encolar(2);
        imprimirEstadoCola(cola);

        System.out.println(">>> OPERACION 3: ENCOLAR(3)");
        cola.encolar(3);
        imprimirEstadoCola(cola);

        System.out.println(">>> OPERACION 4: DESENCOLAR()");
        if (!cola.estaVacia()) {
            int extraido = cola.desencolar();
            System.out.println("Elemento extraido: " + extraido);
        }
        imprimirEstadoCola(cola);

        System.out.println(">>> OPERACION 5: ENCOLAR(4)");
        cola.encolar(4);
        imprimirEstadoCola(cola);

        System.out.println(">>> OPERACION 6: DESENCOLAR()");
        if (!cola.estaVacia()) {
            int extraido = cola.desencolar();
            System.out.println("Elemento extraido: " + extraido);
        }
        imprimirEstadoCola(cola);

        System.out.println(">>> OPERACION 7: DESENCOLAR()");
        if (!cola.estaVacia()) {
            int extraido = cola.desencolar();
            System.out.println("Elemento extraido: " + extraido);
        }
        imprimirEstadoCola(cola);
    }

    private static void probarPila() {

        Pila pila = new Pila();

        System.out.println("\n==================================");
        System.out.println("    PILA IMPLEMENTADA CON COLA");
        System.out.println("==================================\n");

        System.out.println(">>> OPERACION 1: APILAR(10)");
        pila.apilar(10);
        imprimirEstadoPila(pila);

        System.out.println(">>> OPERACION 2: APILAR(20)");
        pila.apilar(20);
        imprimirEstadoPila(pila);

        System.out.println(">>> OPERACION 3: APILAR(30)");
        pila.apilar(30);
        imprimirEstadoPila(pila);

        System.out.println(">>> OPERACION 4: DESAPILAR()");
        if (!pila.estaVacia()) {
            int extraido = pila.desapilar();
            System.out.println("Elemento extraido: " + extraido);
        }
        imprimirEstadoPila(pila);

        System.out.println(">>> OPERACION 5: APILAR(40)");
        pila.apilar(40);
        imprimirEstadoPila(pila);

        System.out.println(">>> OPERACION 6: DESAPILAR()");
        if (!pila.estaVacia()) {
            int extraido = pila.desapilar();
            System.out.println("Elemento extraido: " + extraido);
        }
        imprimirEstadoPila(pila);

        System.out.println(">>> OPERACION 7: DESAPILAR()");
        if (!pila.estaVacia()) {
            int extraido = pila.desapilar();
            System.out.println("Elemento extraido: " + extraido);
        }
        imprimirEstadoPila(pila);
    }

    private static void imprimirEstadoCola(Cola cola) {

        if (cola.estaVacia()) {
            System.out.println("Cola: [ VACIA ]\n");
            return;
        }

        int[] temporal = new int[100];
        int cantidad = 0;

        while (!cola.estaVacia()) {
            temporal[cantidad] = cola.desencolar();
            cantidad++;
        }

        System.out.println("\nEstado actual de la cola:");
        System.out.print("FRENTE -> ");

        for (int i = 0; i < cantidad; i++) {
            System.out.print("[" + temporal[i] + "]");

            if (i < cantidad - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println(" <- FINAL\n");

        for (int i = 0; i < cantidad; i++) {
            cola.encolar(temporal[i]);
        }
    }

    private static void imprimirEstadoPila(Pila pila) {

        if (pila.estaVacia()) {
            System.out.println("Pila: [ VACIA ]\n");
            return;
        }

        int[] temporal = new int[100];
        int cantidad = 0;

        while (!pila.estaVacia()) {
            temporal[cantidad] = pila.desapilar();
            cantidad++;
        }

        System.out.println("\nEstado actual de la pila:");
        System.out.println("       TOP");

        for (int i = 0; i < cantidad; i++) {
            System.out.println("      [" + temporal[i] + "]");
        }

        System.out.println("       ---\n");

        for (int i = cantidad - 1; i >= 0; i--) {
            pila.apilar(temporal[i]);
        }
    }
}
