package listas.nodoDummy;

class Ejemplo {
    public static void main(String[] args) {
        System.out.println("== Eliminar por valor (1)");
        probarEliminar(new int[] { 1, 1, 2, 1, 3 }, 1);
        probarEliminar(new int[] { 1, 1, 1 }, 1);
        probarEliminar(new int[] {}, 1);

        System.out.println();
        System.out.println("== Insertar 9 en posición");
        probarInsertar(new int[] { 1, 2, 3 }, 0);
        probarInsertar(new int[] { 1, 2, 3 }, 2);
        probarInsertar(new int[] { 1, 2, 3 }, 10);
        probarInsertar(new int[] {}, 0);

        System.out.println();
        System.out.println("== Eliminar repetidos");
        probarRepetidos(new int[] { 1, 1, 2, 3, 3, 4 });
        probarRepetidos(new int[] { 1, 1, 1 });
        probarRepetidos(new int[] { 1, 2, 2 });
        probarRepetidos(new int[] { 1, 2, 3 });
        probarRepetidos(new int[] { 5, 5, 6, 6 });
        probarRepetidos(new int[] {});

        System.out.println();
        System.out.println("== Fusionar");
        probarFusionar(new int[] { 1, 4, 7 }, new int[] { 2, 3, 8, 9 });
        probarFusionar(new int[] {}, new int[] { 2, 3 });
        probarFusionar(new int[] {}, new int[] {});
        probarFusionar(new int[] { 1, 1 }, new int[] { 1 });
    }

    static void probarRepetidos(int[] datos) {
        ListaEnlazada conDummy = crear(datos);
        ListaEnlazada sinDummy = crear(datos);

        System.out.print("entrada:   ");
        conDummy.imprimirLista();

        conDummy.eliminarRepetidos();
        sinDummy.eliminarRepetidosSinDummy();

        System.out.print("con dummy: ");
        conDummy.imprimirLista();
        System.out.print("sin dummy: ");
        sinDummy.imprimirLista();
        System.out.println();
    }

    static void probarFusionar(int[] datosA, int[] datosB) {
        ListaEnlazada a = crear(datosA);
        ListaEnlazada b = crear(datosB);

        System.out.print("a:         ");
        a.imprimirLista();
        System.out.print("b:         ");
        b.imprimirLista();

        ListaEnlazada resultado = ListaEnlazada.fusionar(a, b);

        System.out.print("resultado: ");
        resultado.imprimirLista();
        System.out.print("a después: ");
        a.imprimirLista();
        System.out.print("b después: ");
        b.imprimirLista();
        System.out.println();
    }

    static void probarEliminar(int[] datos, int valor) {
        ListaEnlazada conDummy = crear(datos);
        ListaEnlazada sinDummy = crear(datos);

        System.out.print("original:  ");
        conDummy.imprimirLista();

        conDummy.eliminarPorValor(valor);
        sinDummy.eliminarPorValorSinDummy(valor);

        System.out.print("con dummy: ");
        conDummy.imprimirLista();
        System.out.print("sin dummy: ");
        sinDummy.imprimirLista();
        System.out.println();
    }

    static void probarInsertar(int[] datos, int posicion) {
        ListaEnlazada conDummy = crear(datos);
        ListaEnlazada sinDummy = crear(datos);

        System.out.print("original:  ");
        conDummy.imprimirLista();
        System.out.println("posición:  " + posicion);

        conDummy.insertarEnPosicion(posicion, 9);
        sinDummy.insertarEnPosicionSinDummy(posicion, 9);

        System.out.print("con dummy: ");
        conDummy.imprimirLista();
        System.out.print("sin dummy: ");
        sinDummy.imprimirLista();
        System.out.println();
    }

    static ListaEnlazada crear(int[] datos) {
        ListaEnlazada lista = new ListaEnlazada();
        for (int i = 0; i < datos.length; i++) {
            lista.insertarEnPosicion(i, datos[i]);
        }
        return lista;
    }
}