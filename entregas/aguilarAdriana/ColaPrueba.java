package aguilarAdriana;

public class ColaPrueba {
 
    public static void main(String[] args) {
        probarColaNueva();
        probarSecuenciaDelExamen();
        probarPrecondicionColaVacia();
    }
 
    private static void probarColaNueva() {
        Cola cola = new Cola();
        assert cola.estaVacia() : "Una cola nueva debe estar vacía";
        System.out.println("OK  Una cola nueva está vacía");
    }
 
    private static void probarSecuenciaDelExamen() {
        Cola cola = new Cola();
 
        cola.encolar(1);
        cola.encolar(2);
        cola.encolar(3);
        assert !cola.estaVacia() : "Tras encolar 1, 2, 3 la cola no debe estar vacía";
 
        comprobarDesencolar(cola, 1);
        cola.encolar(4);
        comprobarDesencolar(cola, 2);
        comprobarDesencolar(cola, 3);
 
        assert !cola.estaVacia() : "Al final de la secuencia debe quedar el 4";
        comprobarDesencolar(cola, 4);
        assert cola.estaVacia() : "Tras sacar el 4 la cola debe estar vacía";
 
        System.out.println("OK  Secuencia del examen: sale 1, 2, 3 y queda el 4");
    }
 
    private static void probarPrecondicionColaVacia() {
        Cola cola = new Cola();
        System.out.println("Ahora se desencola una cola vacía: debe saltar AssertionError.");
        cola.desencolar();
    }
 
    private static void comprobarDesencolar(Cola cola, int esperado) {
        int obtenido = cola.desencolar();
        assert obtenido == esperado : "Se esperaba " + esperado + " y se obtuvo " + obtenido;
        System.out.println("OK  desencolar() devuelve " + obtenido);
    }
}