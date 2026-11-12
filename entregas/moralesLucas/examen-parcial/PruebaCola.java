public class PruebaCola {

    public static void main(String[] args) {

        Cola cola = new Cola();

        assert cola.estaVacia();

        cola.encolar(1);
        cola.encolar(2);
        cola.encolar(3);

        assert !cola.estaVacia();

        int primero = cola.desencolar();
        assert primero == 1;

        cola.encolar(4);

        int segundo = cola.desencolar();
        assert segundo == 2;

        int tercero = cola.desencolar();
        assert tercero == 3;

        int cuarto = cola.desencolar();
        assert cuarto == 4;

        assert cola.estaVacia();

        System.out.println("Todas las pruebas han pasado.");
    }
}