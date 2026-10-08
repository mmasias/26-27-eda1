package entregas.scr.herreraGabriel;

public class Ejemplo {

    public static void main(String[] args) {
        System.out.println("Eliminar repetidos");
        probarEliminarRepetidos(new int[] { 1, 1, 2, 3, 3, 4 });
        probarEliminarRepetidos(new int[] { 1, 1, 1 });
        probarEliminarRepetidos(new int[] { 1, 2, 2 });
        probarEliminarRepetidos(new int[] { 1, 2, 3 });
        probarEliminarRepetidos(new int[] { 5, 5, 6, 6 });
        probarEliminarRepetidos(new int[] {});
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

    static ListaEnlazada crear(int[] datos) {
        ListaEnlazada lista = new ListaEnlazada();
        for (int i = 0; i < datos.length; i++) {
            lista.insertarEnPosicion(i, datos[i]);
        }
        return lista;
    }
}