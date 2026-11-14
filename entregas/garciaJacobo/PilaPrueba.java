public class PilaPrueba {

    public static void main(String[] args) {

        Pila pila = new Pila();

        pila.apilar(1);
        System.out.println("Apilar(1) -> Pila: [1]");

        pila.apilar(2);
        System.out.println("Apilar(2) -> Pila: [2, 1]");

        pila.apilar(3);
        System.out.println("Apilar(3) -> Pila: [3, 2, 1]");

        int valor = pila.desapilar();
        System.out.println("Desapilar() -> Sale: " + valor
                + " | Pila: [2, 1]");
        assert valor == 3;

        pila.apilar(4);
        System.out.println("Apilar(4) -> Pila: [4, 2, 1]");

        valor = pila.desapilar();
        System.out.println("Desapilar() -> Sale: " + valor
                + " | Pila: [2, 1]");
        assert valor == 4;

        valor = pila.desapilar();
        System.out.println("Desapilar() -> Sale: " + valor
                + " | Pila: [1]");
        assert valor == 2;

        System.out.println("Elemento que queda en la pila: 1");
    }
}