package entregas.yagoLopez;

public class ColaTest {

    private static int fallos = 0;

    private static void comprobar(String nombre, int esperado, int obtenido) {
        boolean ok = esperado == obtenido;
        System.out.println((ok ? "OK    " : "FALLO ") + nombre
                + " -> esperado " + esperado + ", obtenido " + obtenido);
        if (!ok) fallos++;
    }

    public static void main(String[] args) {
        Cola c = new Cola();

        c.encolar(1);
        c.encolar(2);
        c.encolar(3);
        comprobar("1er desencolar", 1, c.desencolar());

        c.encolar(4);
        comprobar("2o desencolar", 2, c.desencolar());
        comprobar("3er desencolar", 3, c.desencolar());

        boolean noVacia = !c.estaVacia();
        System.out.println((noVacia ? "OK    " : "FALLO ") + "queda el 4: la cola no esta vacia");
        if (!noVacia) fallos++;

        System.out.println(fallos == 0 ? "\nTodas las pruebas OK" : "\n" + fallos + " fallo(s)");
    }
}