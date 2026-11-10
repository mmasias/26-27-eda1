public class Main {
    public static void main(String[] args) {
        Cola cola = new Cola();

        System.out.println("Iniciando secuencia de pruebas\n");

        cola.encolar(1);
        imprimirEstadoCola("encolar(1)", cola);

        cola.encolar(2);
        imprimirEstadoCola("encolar(2)", cola);

        cola.encolar(3);
        imprimirEstadoCola("encolar(3)", cola);

        if (!cola.estaVacia()) {
            int extraido = cola.desencolar();
            imprimirEstadoCola("desencolar() -> Retornó: " + extraido, cola);
        }

        cola.encolar(4);
        imprimirEstadoCola("encolar(4)", cola);

        if (!cola.estaVacia()) {
            int extraido = cola.desencolar();
            imprimirEstadoCola("desencolar() -> Retornó: " + extraido, cola);
        }

        if (!cola.estaVacia()) {
            int extraido = cola.desencolar();
            imprimirEstadoCola("desencolar() -> Retornó: " + extraido, cola);
        }
    }

    private static void imprimirEstadoCola(String accion, Cola cola) {
        System.out.println("Acción: " + accion);
        
        if (cola.estaVacia()) {
            System.out.println("  [ Cola Vacía ]\n");
            return;
        }

        int[] temporal = new int[100];
        int cantidad = 0;

        while (!cola.estaVacia()) {
            temporal[cantidad] = cola.desencolar();
            cantidad++;
        }

        System.out.println("  ___");
        for (int i = cantidad - 1; i >= 0; i--) {
            System.out.println(" | " + temporal[i] + " |");
        }
        System.out.println("  ---");
        System.out.println();

        for (int i = 0; i < cantidad; i++) {
            cola.encolar(temporal[i]);
        }
    }
}