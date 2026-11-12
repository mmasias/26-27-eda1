public class Principal {

    public static void main(String[] args) {

        Pila pila = new Pila();

        assert pila.estaVacia();

        pila.apilar(1);
        pila.apilar(2);
        pila.apilar(3);

        assert pila.desapilar() == 3;

        pila.apilar(4);

        assert pila.desapilar() == 4;
        assert pila.desapilar() == 2;

        assert !pila.estaVacia();

        assert pila.desapilar() == 1;
        assert pila.estaVacia();

        System.out.println("Todas las pruebas correctas");
    }
}