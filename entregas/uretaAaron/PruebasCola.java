public class PruebasCola {
    public static void main(String[] args) {
        Cola cola = new Cola();

        cola.encolar(1);
        cola.encolar(2);
        cola.encolar(3);

        int primero = cola.desencolar();
        System.out.println("Sale el primero: " + primero);
        assert primero == 1;

        cola.encolar(4);

        int segundo = cola.desencolar();
        System.out.println("Sale el segundo: " + segundo);
        assert segundo == 2;

        int tercero = cola.desencolar();
        System.out.println("Sale el tercero: " + tercero);
        assert tercero == 3;

        assert !cola.estaVacia();

        int cuarto = cola.desencolar();
        System.out.println("Sale el cuarto: " + cuarto);
        assert cuarto == 4;

        assert cola.estaVacia();
        System.out.println("Pruebas correctas, la cola queda vacia.");
    }
}