public class PruebaPila {

    public static void main(String[] args) {

        Pila pila = new Pila();

        System.out.println("Vacia? " + pila.estaVacia());

        pila.apilar(1);
        pila.apilar(2);
        pila.apilar(3);
        System.out.println("desapilar: " + pila.desapilar() + " (esperado 3)");

        pila.apilar(4);
        System.out.println("desapilar: " + pila.desapilar() + " (esperado 4)");
        System.out.println("desapilar: " + pila.desapilar() + " (esperado 2)");

        System.out.println("Vacia? " + pila.estaVacia());
        System.out.println("desapilar: " + pila.desapilar() + " (esperado 1)");
        System.out.println("Vacia? " + pila.estaVacia());
    }
}