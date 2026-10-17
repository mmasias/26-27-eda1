
public class Ejemplo {

    public static void main(String[] args) {
        System.out.println("Eliminar repetidos");

        probarEliminar(new int[]{1, 1, 2, 3, 3, 4});
        probarEliminar(new int[]{1, 1, 1});
        probarEliminar(new int[]{1, 2, 3});

        System.out.println("\nFusionar listas");

        probarFusionar(
            new int[]{1, 4, 7},
            new int[]{2, 3, 8, 9}
        );
    }

    static ListaEnlazada crear(int[] datos) {
        ListaEnlazada lista = new ListaEnlazada();

        for (int i = 0; i < datos.length; i++) {
            lista.insertarEnPosicion(i, datos[i]);
        }

        return lista;
    }

    static void probarEliminar(int[] datos) {
        ListaEnlazada lista = crear(datos);
        lista.imprimirLista();
        lista.eliminarRepetidos();
        System.out.print("Resultado: ");
        lista.imprimirLista();
        System.out.println();
    }

    static void probarFusionar(int[] a, int[] b) {
        ListaEnlazada listaA = crear(a);
        ListaEnlazada listaB = crear(b);

        ListaEnlazada resultado =
            ListaEnlazada.fusionar(listaA, listaB);

        resultado.imprimirLista();
    }
}
