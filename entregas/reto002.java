public class reto002 {

    public static void main(String[] args) {
        probarEliminarRepetidos();
        probarFusionar();
    }

    private static ListaEnlazada crearLista(int[] valores) {
        ListaEnlazada lista = new ListaEnlazada();

        int i = 0;

        while (i < valores.length) {
            lista.insertarAlFinal(valores[i]);
            i++;
        }

        return lista;
    }

    private static void probarEliminarRepetidos() {
        System.out.println("ELIMINAR REPETIDOS CON DUMMY");

        probarEliminacion(new int[]{1, 1, 2, 3, 3, 4}, true);
        probarEliminacion(new int[]{1, 1, 1}, true);
        probarEliminacion(new int[]{1, 2, 2}, true);
        probarEliminacion(new int[]{1, 2, 3}, true);
        probarEliminacion(new int[]{5, 5, 6, 6}, true);
        probarEliminacion(new int[]{}, true);

        System.out.println();
        System.out.println("ELIMINAR REPETIDOS SIN DUMMY");

        probarEliminacion(new int[]{1, 1, 2, 3, 3, 4}, false);
        probarEliminacion(new int[]{1, 1, 1}, false);
        probarEliminacion(new int[]{1, 2, 2}, false);
        probarEliminacion(new int[]{1, 2, 3}, false);
        probarEliminacion(new int[]{5, 5, 6, 6}, false);
        probarEliminacion(new int[]{}, false);
    }

    private static void probarEliminacion(
            int[] valores, boolean usarDummy) {

        ListaEnlazada lista = crearLista(valores);

        System.out.print("Entrada: ");
        lista.mostrar();

        if (usarDummy) {
            lista.eliminarRepetidos();
        } else {
            lista.eliminarRepetidosSinDummy();
        }

        System.out.print("Salida:  ");
        lista.mostrar();

        System.out.println();
    }

    private static void probarFusionar() {
        System.out.println("FUSIONAR LISTAS");

        probarFusion(
            new int[]{1, 4, 7},
            new int[]{2, 3, 8, 9}
        );

        probarFusion(
            new int[]{},
            new int[]{2, 3}
        );

        probarFusion(
            new int[]{},
            new int[]{}
        );

        probarFusion(
            new int[]{1, 1},
            new int[]{1}
        );
    }

    private static void probarFusion(
            int[] valoresA, int[] valoresB) {

        ListaEnlazada a = crearLista(valoresA);
        ListaEnlazada b = crearLista(valoresB);

        System.out.print("Lista a: ");
        a.mostrar();

        System.out.print("Lista b: ");
        b.mostrar();

        ListaEnlazada resultado = ListaEnlazada.fusionar(a, b);

        System.out.print("Resultado: ");
        resultado.mostrar();

        System.out.print("Lista a después: ");
        a.mostrar();

        System.out.print("Lista b después: ");
        b.mostrar();

        System.out.println();
    }
}