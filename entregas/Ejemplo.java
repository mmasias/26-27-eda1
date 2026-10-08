public class Ejemplo {

    static ListaEnlazada crear(int... datos) {
        ListaEnlazada lista = new ListaEnlazada();
        for (int d : datos) {
            lista.agregarAlFinal(d);
        }
        return lista;
    }

    static void probarRepetidos(int... datos) {
        ListaEnlazada conDummy = crear(datos);
        ListaEnlazada sinDummy = crear(datos);

        System.out.println("Entrada:         " + conDummy);
        conDummy.eliminarRepetidos();
        sinDummy.eliminarRepetidosSinDummy();
        System.out.println("Salida (dummy):  " + conDummy);
        System.out.println("Salida (sin):    " + sinDummy);
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== RETO BASE: eliminarRepetidos ===\n");
        probarRepetidos(1, 1, 2, 3, 3, 4);
        probarRepetidos(1, 1, 1);
        probarRepetidos(1, 2, 2);
        probarRepetidos(1, 2, 3);
        probarRepetidos(5, 5, 6, 6);
        probarRepetidos();
    }
}