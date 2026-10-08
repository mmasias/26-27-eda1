package entregas.nicolasCrespo;

public class EjemploListaEnlazada {
    public static void main(String[] args) {
        System.out.println("Eliminar repetidos con nodo dummy:");
        probarEliminacion(true, 1, 1, 2, 3, 3, 4);
        probarEliminacion(true, 1, 1, 1);
        probarEliminacion(true, 1, 2, 2);
        probarEliminacion(true, 1, 2, 3);
        probarEliminacion(true, 5, 5, 6, 6);
        probarEliminacion(true);

        System.out.println("\nEliminar repetidos sin nodo dummy:");
        probarEliminacion(false, 1, 1, 2, 3, 3, 4);
        probarEliminacion(false, 1, 1, 1);
        probarEliminacion(false, 1, 2, 2);
        probarEliminacion(false, 1, 2, 3);
        probarEliminacion(false, 5, 5, 6, 6);
        probarEliminacion(false);

        System.out.println("\nFusionar listas:");
        probarFusion(new int[] {1, 4, 7}, new int[] {2, 3, 8, 9});
        probarFusion(new int[] {}, new int[] {2, 3});
        probarFusion(new int[] {}, new int[] {});
        probarFusion(new int[] {1, 1}, new int[] {1});

        System.out.println("a y b quedan vacias para que no compartan nodos con el resultado.");
    }

    private static void probarEliminacion(boolean conDummy, int... valores) {
        ListaEnlazada lista = crearLista(valores);

        System.out.print("Entrada: ");
        lista.imprimirLista();

        if (conDummy) {
            lista.eliminarRepetidos();
        } else {
            lista.eliminarRepetidosSinDummy();
        }

        System.out.print("Salida:  ");
        lista.imprimirLista();
    }

    private static void probarFusion(int[] valoresA, int[] valoresB) {
        ListaEnlazada a = crearLista(valoresA);
        ListaEnlazada b = crearLista(valoresB);

        System.out.print("a: ");
        a.imprimirLista();
        System.out.print("b: ");
        b.imprimirLista();

        ListaEnlazada resultado = ListaEnlazada.fusionar(a, b);

        System.out.print("Resultado: ");
        resultado.imprimirLista();
        System.out.print("a despues: ");
        a.imprimirLista();
        System.out.print("b despues: ");
        b.imprimirLista();
    }

    private static ListaEnlazada crearLista(int... valores) {
        ListaEnlazada lista = new ListaEnlazada();
        for (int i = valores.length - 1; i >= 0; i--) {
            lista.insertarAlPrincipio(valores[i]);
        }
        return lista;
    }
}
