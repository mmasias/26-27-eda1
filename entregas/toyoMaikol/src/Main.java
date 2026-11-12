public class Main {
    public static void main(String[] args) {
        Pila pila = new Pila();

        System.out.println("Secuencia de operaciones:");
        
        pila.apilar(1);
        System.out.println("apilar(1)");
        
        pila.apilar(2);
        System.out.println("apilar(2)");
        
        pila.apilar(3);
        System.out.println("apilar(3)");

        System.out.println("desapilar() -> Devuelve: " + pila.desapilar() + " (Esperado: 3)");

        pila.apilar(4);
        System.out.println("apilar(4)");

        System.out.println("desapilar() -> Devuelve: " + pila.desapilar() + " (Esperado: 4)");
        
        System.out.println("desapilar() -> Devuelve: " + pila.desapilar() + " (Esperado: 2)");
        
        System.out.println("desapilar() -> Devuelve: " + pila.desapilar() + " (Esperado: 1)");
    }
}