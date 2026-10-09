public class Ejemplo {

    public static void main(String[] args) {

        System.out.println("---- eliminarRepetidos() ----");

        probarConDummy(new int[]{1, 1, 2, 3, 3, 4});
        probarConDummy(new int[]{1, 1, 1});
        probarConDummy(new int[]{1, 2, 2});
        probarConDummy(new int[]{1, 2, 3});
        probarConDummy(new int[]{5, 5, 6, 6});

        System.out.println();
        System.out.println("---- eliminarRepetidosSinDummy() ----");

        probarSinDummy(new int[]{1, 1, 2, 3, 3, 4});
        probarSinDummy(new int[]{1, 1, 1});
        probarSinDummy(new int[]{1, 2, 2});
        probarSinDummy(new int[]{1, 2, 3});
        probarSinDummy(new int[]{5, 5, 6, 6});

        System.out.println();
        System.out.println("---- Lista vacia ----");

        probarConDummy(new int[]{});
        probarSinDummy(new int[]{});

        System.out.println();
        System.out.println("---- fusionar() ----");

        probarFusion(
                new int[]{1, 4, 7},
                new int[]{2, 3, 8, 9});

        probarFusion(
                new int[]{},
                new int[]{2, 3});

        probarFusion(
                new int[]{},
                new int[]{});

        probarFusion(
                new int[]{1, 1},
                new int[]{1});
    }

    private static void probarConDummy(int[] valores) {
        ListaEnlazada lista = crearLista(valores);

        System.out.print("Entrada: ");
        lista.imprimirLista();

        lista.eliminarRepetidos();

        System.out.print("Salida:  ");
        lista.imprimirLista();

        System.out.println();
    }

    private static void probarSinDummy(int[] valores) {
        ListaEnlazada lista = crearLista(valores);

        System.out.print("Entrada: ");
        lista.imprimirLista();

        lista.eliminarRepetidosSinDummy();

        System.out.print("Salida:  ");
        lista.imprimirLista();

        System.out.println();
    }

    private static void probarFusion(int[] valoresA, int[] valoresB) {
        ListaEnlazada a = crearLista(valoresA);
        ListaEnlazada b = crearLista(valoresB);

        System.out.print("Lista A: ");
        a.imprimirLista();

        System.out.print("Lista B: ");
        b.imprimirLista();

        ListaEnlazada resultado = ListaEnlazada.fusionar(a, b);

        System.out.print("Resultado: ");
        resultado.imprimirLista();

        System.out.print("A despues: ");
        a.imprimirLista();

        System.out.print("B despues: ");
        b.imprimirLista();

        System.out.println();
    }

    private static ListaEnlazada crearLista(int[] valores) {
        ListaEnlazada lista = new ListaEnlazada();

        for (int i = 0; i < valores.length; i++) {
            lista.insertarFinal(valores[i]);
        }

        return lista;
    }
}