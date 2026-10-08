package entregas.moraDaniel;

public class Ejemplo {
    public static void main(String[] args) {
        System.out.println("== Eliminar repetidos");
        probarEliminarRepetidos(new int[] { 1, 1, 2, 3, 3, 4 });
        probarEliminarRepetidos(new int[] { 1, 1, 1 });
        probarEliminarRepetidos(new int[] { 1, 2, 2 });
        probarEliminarRepetidos(new int[] { 1, 2, 3 });
        probarEliminarRepetidos(new int[] { 5, 5, 6, 6 });
        probarEliminarRepetidos(new int[] {});

        System.out.println("== Fusionar");
        probarFusionar(new int[] { 1, 4, 7 }, new int[] { 2, 3, 8, 9 });
        probarFusionar(new int[] {}, new int[] { 2, 3 });
        probarFusionar(new int[] {}, new int[] {});
        probarFusionar(new int[] { 1, 1 }, new int[] { 1 });
    }

    static void probarEliminarRepetidos(int[] datos) {
        ListaEnlazada conDummy = crear(datos);
        ListaEnlazada sinDummy = crear(datos);

        System.out.print("original:  ");
        conDummy.imprimirLista();

        conDummy.eliminarRepetidos();
        sinDummy.eliminarRepetidosSinDummy();

        System.out.print("con dummy: ");
        conDummy.imprimirLista();
        System.out.print("sin dummy: ");
        sinDummy.imprimirLista();
        System.out.println();
    }

    static void probarFusionar(int[] datosDeA, int[] datosDeB) {
        ListaEnlazada listaA = crear(datosDeA);
        ListaEnlazada listaB = crear(datosDeB);

        System.out.print("listaA:           ");
        listaA.imprimirLista();
        System.out.print("listaB:           ");
        listaB.imprimirLista();

        ListaEnlazada resultado = ListaEnlazada.fusionar(listaA, listaB);

        System.out.print("resultado:        ");
        resultado.imprimirLista();
        System.out.print("listaA despues:   ");
        listaA.imprimirLista();
        System.out.print("listaB despues:   ");
        listaB.imprimirLista();
        System.out.println();
    }

    static ListaEnlazada crear(int[] datos) {
        ListaEnlazada lista = new ListaEnlazada();
        for (int posicion = 0; posicion < datos.length; posicion++) {
            lista.insertarEnPosicion(posicion, datos[posicion]);
        }
        return lista;
    }

}
