public class PruebaCola {

    public static void main(String[] args) {
        Cola cola = new Cola();

        cola.encolar(1);
        cola.encolar(2);
        cola.encolar(3);
        int primero = cola.desencolar();
        System.out.println("desencolar() = " + primero + " (esperado 1)");
        assert primero == 1;

        cola.encolar(4);
        int segundo = cola.desencolar();
        System.out.println("desencolar() = " + segundo + " (esperado 2)");
        assert segundo == 2;

        int tercero = cola.desencolar();
        System.out.println("desencolar() = " + tercero + " (esperado 3)");
        assert tercero == 3;

        System.out.println("estaVacia() = " + cola.estaVacia() + " (esperado false, queda el 4)");
        assert !cola.estaVacia();
    }
}
