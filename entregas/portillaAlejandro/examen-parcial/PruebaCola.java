
public class PruebaCola {

    public static void main(String[] args) {

        Cola cola = new Cola();

        assert cola.estaVacia();

        cola.encolar(1);
        cola.encolar(2);
        cola.encolar(3);

        assert !cola.estaVacia();

        assert cola.desencolar() == 1;

        cola.encolar(4);

        assert cola.desencolar() == 2;

        assert cola.desencolar() == 3;

        assert cola.desencolar() == 4;

        assert cola.estaVacia();

        System.out.println("Todas las pruebas se han superado.");
    }
}
