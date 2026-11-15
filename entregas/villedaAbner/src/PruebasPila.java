public class PruebasPila {
    public static void main(String[] args) {
        PilaDeCola miPila = new PilaDeCola();
        
        
        System.out.println("Apilando: 1, 2, 3...");
        miPila.apilar(1);
        miPila.apilar(2);
        miPila.apilar(3);
        
        System.out.println("Primer desapilar (Esperamos un 3):");
        System.out.println("Resultado -> " + miPila.desapilar()); 
        
        System.out.println("Apilando un 4 para probar las referencias temporales...");
        miPila.apilar(4);
        
        // 4. Vaciamos la pila por completo
        System.out.println("\nVaciando el resto de la pila (Esperamos 4, luego 2, luego 1):");
        System.out.println("Resultado -> " + miPila.desapilar()); 
        System.out.println("Resultado -> " + miPila.desapilar()); 
        System.out.println("Resultado -> " + miPila.desapilar()); 
        
        System.out.println("¿Está vacía al final? " + miPila.estaVacia());
    }
}