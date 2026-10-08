package listas.nodoDummy;

public class Ejemplo {

    private static ListaEnlazada construir(int[] valores) {
        ListaEnlazada lista = new ListaEnlazada();
        for (int v : valores) {
            lista.insertarAlFinal(v);
        }
        return lista;
    }

    public static void main(String[] args) {
        System.out.println("=== RETO BASE: eliminarRepetidos() ===");
        int[][] casosBase = {
            {1, 1, 2, 3, 3, 4},
            {1, 1, 1},
            {1, 2, 2},
            {1, 2, 3},
            {5, 5, 6, 6},
            {}
        };

        for (int[] arr : casosBase) {
            ListaEnlazada l1 = construir(arr);
            ListaEnlazada l2 = construir(arr);

            System.out.print("Entrada:           ");
            l1.imprimirLista();

            l1.eliminarRepetidos();
            System.out.print("Con dummy:         ");
            l1.imprimirLista();

            l2.eliminarRepetidosSinDummy();
            System.out.print("Sin dummy:         ");
            l2.imprimirLista();
            System.out.println("------------------------------------------");
        }

        System.out.println("\n=== RETO EXTENDIDO: fusionar(a, b) ===");
        int[][][] casosFusion = {
            {{1, 4, 7}, {2, 3, 8, 9}},
            {{}, {2, 3}},
            {{}, {}},
            {{1, 1}, {1}}
        };

        for (int[][] par : casosFusion) {
            ListaEnlazada a = construir(par[0]);
            ListaEnlazada b = construir(par[1]);

            System.out.print("a:         ");
            a.imprimirLista();
            System.out.print("b:         ");
            b.imprimirLista();

            ListaEnlazada res = ListaEnlazada.fusionar(a, b);

            System.out.print("Resultado: ");
            res.imprimirLista();
            System.out.print("a final:   ");
            a.imprimirLista();
            System.out.print("b final:   ");
            b.imprimirLista();
            System.out.println("------------------------------------------");
        }
    }
}