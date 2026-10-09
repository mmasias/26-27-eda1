package listaConArray;

import java.util.ArrayList;

public class Ejemplo {
    public static void main(String[] args) {
        ArrayList<Integer> listaDeJava = new ArrayList<>();
        ListaConArray listaConArray = new ListaConArray();
        mostrarAmbas("Recién creadas", listaDeJava, listaConArray);

        listaDeJava.add(10);
        listaDeJava.add(20);
        listaDeJava.add(30);
        listaConArray.insertarAlFinal(10);
        listaConArray.insertarAlFinal(20);
        listaConArray.insertarAlFinal(30);
        mostrarAmbas("Tras insertar al final 10, 20 y 30", listaDeJava, listaConArray);

        listaDeJava.add(1, 15);
        listaConArray.insertar(1, 15);
        mostrarAmbas("Tras insertar un 15 en la posición 1", listaDeJava, listaConArray);

        listaDeJava.add(40);
        listaConArray.insertarAlFinal(40);
        mostrarAmbas("Tras insertar al final un 40 (el array interno de 4 casillas ya estaba lleno)", listaDeJava, listaConArray);

        listaDeJava.add(0, 5);
        listaConArray.insertar(0, 5);
        mostrarAmbas("Tras insertar un 5 en la posición 0", listaDeJava, listaConArray);

        int eliminadoDeJava = listaDeJava.remove(2);
        int eliminadoDeListaConArray = listaConArray.eliminar(2);
        mostrarAmbas("Tras eliminar la posición 2 (" + eliminadoDeJava + " | " + eliminadoDeListaConArray + ")", listaDeJava, listaConArray);

        int ultimaPosicion = listaConArray.longitud() - 1;
        listaDeJava.remove(ultimaPosicion);
        listaConArray.eliminar(ultimaPosicion);
        mostrarAmbas("Tras eliminar la última posición", listaDeJava, listaConArray);

        System.out.println("Longitud: " + listaDeJava.size() + " | " + listaConArray.longitud());
    }

    private static void mostrarAmbas(String momento, ArrayList<Integer> listaDeJava, ListaConArray listaConArray) {
        System.out.println(momento);
        System.out.println("  ArrayList     -> " + contenido(listaDeJava));
        System.out.println("  ListaConArray -> " + contenido(listaConArray));
    }

    private static String contenido(ArrayList<Integer> listaDeJava) {
        String texto = "[";
        String separador = "";
        for (int posicion = 0; posicion < listaDeJava.size(); posicion++) {
            texto += separador + listaDeJava.get(posicion);
            separador = ", ";
        }
        return texto + "]";
    }

    private static String contenido(ListaConArray listaConArray) {
        String texto = "[";
        String separador = "";
        for (int posicion = 0; posicion < listaConArray.longitud(); posicion++) {
            texto += separador + listaConArray.obtener(posicion);
            separador = ", ";
        }
        return texto + "]";
    }
}
