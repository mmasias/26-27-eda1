package entregas.MartinezAdrian;

public class Ejemplo {
    public static void main(String[] args) {
        System.out.println("== Pruebas: eliminarRepetidos (con Dummy) ==");
        
        probar(new int[] { 1, 1, 2, 3, 3, 4 });
        probar(new int[] { 1, 1, 1 });
        probar(new int[] { 1, 2, 2 });
        probar(new int[] { 1, 2, 3 });
        probar(new int[] { 5, 5, 6, 6 });
        probar(new int[] {});
    }

    static void probar(int[] datos) {
        ListaEnlazada conDummy = crear(datos);
        ListaEnlazada sinDummy = crear(datos);

        System.out.print("Entrada:    ");
        conDummy.imprimirLista();

        conDummy.eliminarRepetidos();
        sinDummy.eliminarRepetidosSinDummy();

        System.out.print("Con dummy:  ");
        conDummy.imprimirLista();

        System.out.print("Sin dummy:  ");
        sinDummy.imprimirLista();
        System.out.println("----------------------------------------");
    }

    static ListaEnlazada crear(int[] datos) {
        ListaEnlazada lista = new ListaEnlazada();
        for (int i = 0; i < datos.length; i++) {
            lista.insertarEnPosicion(i, datos[i]);
        }
        return lista;
    }
}