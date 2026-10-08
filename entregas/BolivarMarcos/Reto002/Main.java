package entregas.BolivarMarcos.Reto002;

public class Main  {

   public static void main(String[] args) {
        System.out.println("== Eliminar repetidos");
        probarRepetidos(new int[] { 1, 1, 2, 3, 3, 4 });
        probarRepetidos(new int[] { 1, 1, 1 });
        probarRepetidos(new int[] { 1, 2, 2 });
        probarRepetidos(new int[] { 1, 2, 3 });
        probarRepetidos(new int[] { 5, 5, 6, 6 });
        probarRepetidos(new int[] {});
    }

    static void probarRepetidos(int[] datos) {
        ListaEnlazada lista = crear(datos);

        System.out.print("entrada: ");
        lista.imprimirLista();

        lista.eliminarRepetidos();

        System.out.print("salida:  ");
        lista.imprimirLista();
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