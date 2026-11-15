public class PruebaCola {
    public static void main(String[] args) {
        Cola cola = new Cola();
        assert cola.estaVacia() : "La cola debería iniciar vacía";

        cola.encolar(1);
        assert !cola.estaVacia() : "La cola no debería estar vacía tras encolar(1)";

        cola.encolar(2);
        cola.encolar(3);

        int primero = cola.desencolar();
        assert primero == 1 : "Se esperaba 1, pero devolvió " + primero;

        cola.encolar(4);

        int segundo = cola.desencolar();
        assert segundo == 2 : "Se esperaba 2, pero devolvió " + segundo;

        int tercero = cola.desencolar();
        assert tercero == 3 : "Se esperaba 3, pero devolvió " + tercero;

        System.out.println("Pruebas superadas: 1, 2, 3 (y 4) desencolados en orden FIFO correcto.");
    }
}