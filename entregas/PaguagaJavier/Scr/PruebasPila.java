public class PruebasPila {

    public static void main(String[] args) {
        System.out.println("=== REPRODUCIENDO SECUENCIA DE LA PARTE 1 ===");
        
        Pila pila = new Pila();

        pila.apilar(1);
        System.out.println("apilar(1)    -> Estado esperado en cola: [1]");

        pila.apilar(2);
        System.out.println("apilar(2)    -> Estado esperado en cola: [2, 1]");

        pila.apilar(3);
        System.out.println("apilar(3)    -> Estado esperado en cola: [3, 2, 1]");

        int valorDesapilado1 = pila.desapilar();
        System.out.println("desapilar()  -> Devuelto: " + valorDesapilado1 + " (esperado: 3) | Estado en cola: [2, 1]");
        assert valorDesapilado1 == 3 : "Error: se esperaba 3 pero se obtuvo " + valorDesapilado1;

        pila.apilar(4);
        System.out.println("apilar(4)    -> Estado esperado en cola: [4, 2, 1]");

        int valorDesapilado2 = pila.desapilar();
        System.out.println("desapilar()  -> Devuelto: " + valorDesapilado2 + " (esperado: 4) | Estado en cola: [2, 1]");
        assert valorDesapilado2 == 4 : "Error: se esperaba 4 pero se obtuvo " + valorDesapilado2;

        int valorDesapilado3 = pila.desapilar();
        System.out.println("desapilar()  -> Devuelto: " + valorDesapilado3 + " (esperado: 2) | Estado en cola: [1]");
        assert valorDesapilado3 == 2 : "Error: se esperaba 2 pero se obtuvo " + valorDesapilado3;

        assert pila.estaVacia() : "Error: la pila deberia haber quedado vacia";

        System.out.println("\nTodas las operaciones y aserciones se completaron con exito.");
    }
}