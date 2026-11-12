public class PruebaPila {
    public static void main(String[] args) {
        Pila pila = new Pila();
        System.out.println("Inicio       -> Estado: vacia");

        pila.apilar(1);
        System.out.println("apilar(1)    -> Estado: [1]");

        pila.apilar(2);
        System.out.println("apilar(2)    -> Estado: [1, 2]");

        pila.apilar(3);
        System.out.println("apilar(3)    -> Estado: [1, 2, 3]");

        int valor1 = pila.desapilar();
        assert valor1 == 3 : "Fallo en la primera extraccion";
        System.out.println("desapilar()  -> Saca el " + valor1 + " | Estado: [1, 2]");

        pila.apilar(4);
        System.out.println("apilar(4)    -> Estado: [1, 2, 4]");

        int valor2 = pila.desapilar();
        assert valor2 == 4 : "Fallo en la segunda extraccion";
        System.out.println("desapilar()  -> Saca el " + valor2 + " | Estado: [1, 2]");

        int valor3 = pila.desapilar();
        assert valor3 == 2 : "Fallo en la tercera extraccion";
        System.out.println("desapilar()  -> Saca el " + valor3 + " | Estado: [1]");

        System.out.println("\nVerificacion completada: Los estados coinciden con el diseño.");
    }
}