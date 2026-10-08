public class Ejemplo {

    static ListaEnlazada crear(int... datos) {
        ListaEnlazada lista = new ListaEnlazada();
        for (int d : datos) {
            lista.agregarAlFinal(d);
        }
        return lista;
    }

    static void probarRepetidos(int... datos) {
        ListaEnlazada conDummy = crear(datos);
        ListaEnlazada sinDummy = crear(datos);

        System.out.println("Entrada:         " + conDummy);
        conDummy.eliminarRepetidos();
        sinDummy.eliminarRepetidosSinDummy();
        System.out.println("Salida (dummy):  " + conDummy);
        System.out.println("Salida (sin):    " + sinDummy);
        System.out.println();
    }

    static void probarFusion(int[] da, int[] db) {
        ListaEnlazada a = crear(da);
        ListaEnlazada b = crear(db);

        System.out.println("a: " + a + "   b: " + b);
        ListaEnlazada r = ListaEnlazada.fusionar(a, b);
        System.out.println("Resultado: " + r);
        System.out.println("Después -> a: " + a + "   b: " + b);
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== RETO BASE: eliminarRepetidos ===\n");
        probarRepetidos(1, 1, 2, 3, 3, 4);
        probarRepetidos(1, 1, 1);
        probarRepetidos(1, 2, 2);
        probarRepetidos(1, 2, 3);
        probarRepetidos(5, 5, 6, 6);
        probarRepetidos();

        System.out.println("=== RETO EXTENDIDO: fusionar ===\n");
        probarFusion(new int[]{1, 4, 7}, new int[]{2, 3, 8, 9});
        probarFusion(new int[]{},        new int[]{2, 3});
        probarFusion(new int[]{},        new int[]{});
        probarFusion(new int[]{1, 1},    new int[]{1});
    }
}