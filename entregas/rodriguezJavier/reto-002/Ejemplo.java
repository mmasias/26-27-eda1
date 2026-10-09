class Ejemplo {

    public static void main(String[] args) {
        System.out.println("=== ELIMINAR REPETIDOS ===");
        probarEliminar(new int[] { 1, 1, 2, 3, 3, 4 });
        probarEliminar(new int[] { 1, 1, 1 });
        probarEliminar(new int[] { 1, 2, 2 });
        probarEliminar(new int[] { 1, 2, 3 });
        probarEliminar(new int[] { 5, 5, 6, 6 });
        probarEliminar(new int[] {});

        System.out.println("\n=== FUSIONAR LISTAS ===");
        probarFusion(new int[] { 1, 4, 7 }, new int[] { 2, 3, 8, 9 });
        probarFusion(new int[] {}, new int[] { 2, 3 });
        probarFusion(new int[] {}, new int[] {});
        probarFusion(new int[] { 1, 1 }, new int[] { 1 });
    }

    static void probarEliminar(int[] valores) {
        ListaEnlazada conDummy = construirLista(valores);
        ListaEnlazada sinDummy = construirLista(valores);

        System.out.print("Entrada:      ");
        conDummy.imprimirLista();

        conDummy.eliminarRepetidos();
        sinDummy.eliminarRepetidosSinDummy();

        System.out.print("Con dummy:    ");
        conDummy.imprimirLista();
        System.out.print("Sin dummy:    ");
        sinDummy.imprimirLista();
        System.out.println();
    }

    static void probarFusion(int[] valoresA, int[] valoresB) {
        ListaEnlazada listaA = construirLista(valoresA);
        ListaEnlazada listaB = construirLista(valoresB);

        System.out.print("Lista A:      ");
        listaA.imprimirLista();
        System.out.print("Lista B:      ");
        listaB.imprimirLista();

        ListaEnlazada fusionada = ListaEnlazada.fusionar(listaA, listaB);

        System.out.print("Fusionada:    ");
        fusionada.imprimirLista();
        System.out.println();
    }

    static ListaEnlazada construirLista(int[] valores) {
        ListaEnlazada lista = new ListaEnlazada();
        for (int i = 0; i < valores.length; i++) {
            lista.insertarEnPosicion(i, valores[i]);
        }
        return lista;
    }
}