package entregas.garciaJacobo;

public class Ejemplo {

    public static void main(String[] args) {

        System.out.println("----eliminarRepetidos() ----");

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
        System.out.println("---- Lista vacía ----");

        probarConDummy(new int[]{});
        probarSinDummy(new int[]{});
    }

    private static void probarConDummy(int[] valores) {
        ListaEnlazada lista = crearLista(valores);

        System.out.print("->Entrada: ");
        lista.imprimirLista();

        lista.eliminarRepetidos();

        System.out.print("->Salida:  ");
        lista.imprimirLista();

        System.out.println();
    }

    private static void probarSinDummy(int[] valores) {
        ListaEnlazada lista = crearLista(valores);

        System.out.print("->Entrada: ");
        lista.imprimirLista();

        lista.eliminarRepetidosSinDummy();

        System.out.print("->Salida:  ");
        lista.imprimirLista();

        System.out.println();
    }

    private static ListaEnlazada crearLista(int[] valores) {
        ListaEnlazada lista = new ListaEnlazada();

        for (int i = 0; i < valores.length; i++) {
            lista.insertarEnPosicion(i, valores[i]);
        }

        return lista;
    }
}
