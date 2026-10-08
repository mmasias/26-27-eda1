public class Ejemplo {

    static ListaEnlazada crear(int[] valores) {
        ListaEnlazada l = new ListaEnlazada();
        for (int i = 0; i < valores.length; i++) {
            l.insertarAlFinal(valores[i]);
        }
        return l;
    }

    static void probarEliminar(int[] valores) {
        ListaEnlazada conDummy = crear(valores);
        ListaEnlazada sinDummy = crear(valores);

        System.out.println("Entrada:    " + conDummy);
        conDummy.eliminarRepetidos();
        sinDummy.eliminarRepetidosSinDummy();
        System.out.println("Con dummy:  " + conDummy);
        System.out.println("Sin dummy:  " + sinDummy);
        System.out.println();
    }

    static void probarFusionar(int[] x, int[] y) {
        ListaEnlazada a = crear(x);
        ListaEnlazada b = crear(y);

        System.out.println("a: " + a);
        System.out.println("b: " + b);
        ListaEnlazada r = ListaEnlazada.fusionar(a, b);
        System.out.println("Resultado: " + r);
        System.out.println("a despues: " + a + " | b despues: " + b);
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("===== ELIMINAR REPETIDOS =====");
        System.out.println();
        probarEliminar(new int[]{1, 1, 2, 3, 3, 4});
        probarEliminar(new int[]{1, 1, 1});
        probarEliminar(new int[]{1, 2, 2});
        probarEliminar(new int[]{1, 2, 3});
        probarEliminar(new int[]{5, 5, 6, 6});
        probarEliminar(new int[]{});

        System.out.println("===== FUSIONAR =====");
        System.out.println();
        probarFusionar(new int[]{1, 4, 7}, new int[]{2, 3, 8, 9});
        probarFusionar(new int[]{}, new int[]{2, 3});
        probarFusionar(new int[]{}, new int[]{});
        probarFusionar(new int[]{1, 1}, new int[]{1});
    }
}