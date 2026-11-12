public class PruebaCola {
    public static void main(String[] args) {
        Cola cola = new Cola();
        assert cola.estaVacia();

        cola.encolar(1);
        cola.encolar(2);
        cola.encolar(3);
        assert !cola.estaVacia();

        int primero = cola.desencolar();
        System.out.println("1er desencolar() -> " + primero + " (esperado: 1)");
        assert primero == 1;

        cola.encolar(4);

        int segundo = cola.desencolar();
        System.out.println("2do desencolar() -> " + segundo + " (esperado: 2)");
        assert segundo == 2;

        int tercero = cola.desencolar();
        System.out.println("3er desencolar() -> " + tercero + " (esperado: 3)");
        assert tercero == 3;

        assert !cola.estaVacia();

        int cuarto = cola.desencolar();
        System.out.println("Comprobacion estado final restante -> " + cuarto + " (esperado: 4)");
        assert cuarto == 4;
        assert cola.estaVacia();
    }
}
