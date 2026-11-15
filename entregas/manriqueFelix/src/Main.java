public class Main {
    public static void main(String[] args) {
        PilaEnCola pila = new PilaEnCola();
        
        pila.apilar(1);
        pila.apilar(2);
        pila.apilar(3);

        int r1 = pila.desapilar();
        System.out.println("Desapilamos: " + r1 + " (Esperado: 3)");
        assert r1 == 3;
        
        pila.apilar(4);

        int r2 = pila.desapilar();
        System.out.println("Desapilamos: " + r2 + " (Esperado: 4)");
        assert r2 == 4;
        
        int r3 = pila.desapilar();
        System.out.println("Desapilamos: " + r3 + " (Esperado: 2)");
        assert r3 == 2;
    }
}