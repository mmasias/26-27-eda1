package entregas.rodriguezJavier;


public class PruebasCola {

    public static void main(String[] args) {

        Cola cola = new Cola();

        cola.encolar(1);
        cola.encolar(2);
        cola.encolar(3);

        if (cola.desencolar() != 1) {
            throw new AssertionError("Se esperaba 1");
        }

        cola.encolar(4);

        if (cola.desencolar() != 2) {
            throw new AssertionError("Se esperaba 2");
        }

        if (cola.desencolar() != 3) {
            throw new AssertionError("Se esperaba 3");
        }

        System.out.println("Todas las pruebas han pasado.");
    }
}
