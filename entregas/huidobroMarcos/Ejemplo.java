package entregas.huidobroMarcos;

public class Ejemplo {
    package listas.nodoDummy;

public class Ejemplo {

    public static void main(String[] args) {


        ListaEnlazada lista1 = new ListaEnlazada();
        lista1.insertarEnPosicion(0, 1);
        lista1.insertarEnPosicion(1, 1);
        lista1.insertarEnPosicion(2, 2);
        lista1.insertarEnPosicion(3, 3);
        lista1.insertarEnPosicion(4, 3);
        lista1.insertarEnPosicion(5, 4);

        System.out.print("Caso 1 - Entrada: ");
        lista1.imprimirLista();

        lista1.eliminarRepetidos();

        System.out.print("Caso 1 - Salida:  ");
        lista1.imprimirLista();


        ListaEnlazada lista2 = new ListaEnlazada();
        lista2.insertarEnPosicion(0, 1);
        lista2.insertarEnPosicion(1, 1);
        lista2.insertarEnPosicion(2, 1);

        System.out.print("\nCaso 2 - Entrada: ");
        lista2.imprimirLista();

        lista2.eliminarRepetidos();

        System.out.print("Caso 2 - Salida:  ");
        lista2.imprimirLista();


        
    }
}
}
