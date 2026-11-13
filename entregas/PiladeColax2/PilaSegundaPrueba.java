package entregas.PiladeColax2;

public class PilaSegundaPrueba {
 
    public static void main(String[] args) {
        probarPilaNueva();
        probarSecuencia();
        probarPrecondicionPilaVacia();
    }
 
    private static void probarPilaNueva() {
        Pila pila = new Pila();
        assert pila.estaVacia() : "Una pila nueva debe estar vacía";
        System.out.println("OK  Una pila nueva está vacía");
    }
 
    private static void probarSecuencia() {
        Pila pila = new Pila();
 
        pila.apilar(1);
        pila.apilar(2);
        pila.apilar(3);
        assert !pila.estaVacia() : "Tras apilar 1, 2, 3 la pila no debe estar vacía";
 
        comprobarDesapilar(pila, 3);
        pila.apilar(4);
        comprobarDesapilar(pila, 4);
        comprobarDesapilar(pila, 2);
 
        assert !pila.estaVacia() : "Al final de la secuencia debe quedar el 1";
        comprobarDesapilar(pila, 1);
        assert pila.estaVacia() : "Tras sacar el 1 la pila debe estar vacía";
 
        System.out.println("OK  Secuencia: sale 3, 4, 2 y queda el 1");
    }
 
    private static void probarPrecondicionPilaVacia() {
        Pila pila = new Pila();
        System.out.println("Ahora se desapila una pila vacía: debe saltar AssertionError.");
        pila.desapilar();
    }
 
    private static void comprobarDesapilar(Pila pila, int esperado) {
        int obtenido = pila.desapilar();
        assert obtenido == esperado : "Se esperaba " + esperado + " y se obtuvo " + obtenido;
        System.out.println("OK  desapilar() devuelve " + obtenido);
    }
}