package arrayConLista;

public class Ejemplo {
    public static void main(String[] args) {
        int[] notasEnArray = new int[5];
        ArrayConLista notasEnLista = new ArrayConLista(5);
        mostrarAmbos("Recién creados", notasEnArray, notasEnLista);

        notasEnArray[0] = 7;
        notasEnArray[2] = 9;
        notasEnArray[4] = 5;
        notasEnLista.asignar(0, 7);
        notasEnLista.asignar(2, 9);
        notasEnLista.asignar(4, 5);
        mostrarAmbos("Tras asignar las posiciones 0, 2 y 4", notasEnArray, notasEnLista);

        notasEnArray[2] = 10;
        notasEnLista.asignar(2, 10);
        mostrarAmbos("Tras cambiar la posición 2 por un 10", notasEnArray, notasEnLista);

        System.out.println("Longitud: " + notasEnArray.length + " | " + notasEnLista.longitud());
        System.out.println("Última posición: " + notasEnArray[4] + " | " + notasEnLista.obtener(4));
    }

    private static void mostrarAmbos(String momento, int[] array, ArrayConLista arrayConLista) {
        System.out.println(momento);
        System.out.println("  int[]         -> " + contenido(array));
        System.out.println("  ArrayConLista -> " + contenido(arrayConLista));
    }

    private static String contenido(int[] array) {
        String texto = "[";
        String separador = "";
        for (int posicion = 0; posicion < array.length; posicion++) {
            texto += separador + array[posicion];
            separador = ", ";
        }
        return texto + "]";
    }

    private static String contenido(ArrayConLista arrayConLista) {
        String texto = "[";
        String separador = "";
        for (int posicion = 0; posicion < arrayConLista.longitud(); posicion++) {
            texto += separador + arrayConLista.obtener(posicion);
            separador = ", ";
        }
        return texto + "]";
    }
}
