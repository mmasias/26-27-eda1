public class Ejemplo {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("ELIMINAR REPETIDOS CON DUMMY");
        System.out.println("========================================");

        ListaEnlazada lista1 = crearLista(1, 1, 2, 3, 3, 4);
        probarEliminarDummy(lista1);

        ListaEnlazada lista2 = crearLista(1, 1, 1);
        probarEliminarDummy(lista2);

        ListaEnlazada lista3 = crearLista(1, 2, 2);
        probarEliminarDummy(lista3);

        ListaEnlazada lista4 = crearLista(1, 2, 3);
        probarEliminarDummy(lista4);

        ListaEnlazada lista5 = crearLista(5, 5, 6, 6);
        probarEliminarDummy(lista5);

        ListaEnlazada lista6 = new ListaEnlazada();
        probarEliminarDummy(lista6);

        System.out.println();
        System.out.println("========================================");
        System.out.println("ELIMINAR REPETIDOS SIN DUMMY");
        System.out.println("========================================");

        ListaEnlazada lista7 = crearLista(1, 1, 2, 3, 3, 4);
        probarEliminarSinDummy(lista7);

        ListaEnlazada lista8 = crearLista(1, 1, 1);
        probarEliminarSinDummy(lista8);

        ListaEnlazada lista9 = crearLista(1, 2, 2);
        probarEliminarSinDummy(lista9);

        ListaEnlazada lista10 = crearLista(1, 2, 3);
        probarEliminarSinDummy(lista10);

        ListaEnlazada lista11 = crearLista(5, 5, 6, 6);
        probarEliminarSinDummy(lista11);

        ListaEnlazada lista12 = new ListaEnlazada();
        probarEliminarSinDummy(lista12);
        System.out.println();
        System.out.println("========================================");
        System.out.println("FUSIONAR");
        System.out.println("========================================");

        ListaEnlazada a1 = crearLista(1, 4, 7);
        ListaEnlazada b1 = crearLista(2, 3, 8, 9);

        System.out.println("A: " + a1);
        System.out.println("B: " + b1);

        ListaEnlazada resultado1 = ListaEnlazada.fusionar(a1, b1);

        System.out.println("Resultado: " + resultado1);
        System.out.println("A después: " + a1);
        System.out.println("B después: " + b1);

        System.out.println();
        ListaEnlazada a2 = new ListaEnlazada();
        ListaEnlazada b2 = crearLista(2, 3);

        System.out.println("A: " + a2);
        System.out.println("B: " + b2);

        ListaEnlazada resultado2 = ListaEnlazada.fusionar(a2, b2);

        System.out.println("Resultado: " + resultado2);
        System.out.println("A después: " + a2);
        System.out.println("B después: " + b2);

        System.out.println();
        ListaEnlazada a3 = new ListaEnlazada();
        ListaEnlazada b3 = new ListaEnlazada();

        System.out.println("A: " + a3);
        System.out.println("B: " + b3);

        ListaEnlazada resultado3 = ListaEnlazada.fusionar(a3, b3);

        System.out.println("Resultado: " + resultado3);
        System.out.println("A después: " + a3);
        System.out.println("B después: " + b3);

        System.out.println();
        ListaEnlazada a4 = crearLista(1, 1);
        ListaEnlazada b4 = crearLista(1);

        System.out.println("A: " + a4);
        System.out.println("B: " + b4);

        ListaEnlazada resultado4 = ListaEnlazada.fusionar(a4, b4);

        System.out.println("Resultado: " + resultado4);
        System.out.println("A después: " + a4);
        System.out.println("B después: " + b4);
    }

    private static ListaEnlazada crearLista(int... valores) {

        ListaEnlazada lista = new ListaEnlazada();

        for (int valor : valores) {
            lista.insertar(valor);
        }

        return lista;
    }

    private static void probarEliminarDummy(ListaEnlazada lista) {

        System.out.println("Entrada: " + lista);

        lista.eliminarRepetidos();

        System.out.println("Salida:  " + lista);
        System.out.println();
    }

    private static void probarEliminarSinDummy(ListaEnlazada lista) {

        System.out.println("Entrada: " + lista);

        lista.eliminarRepetidosSinDummy();

        System.out.println("Salida:  " + lista);
        System.out.println();
    }
}