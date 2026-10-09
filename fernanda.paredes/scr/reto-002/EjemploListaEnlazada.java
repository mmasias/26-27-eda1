package entregas.paredesFernanda;

public class EjemploListaEnlazada {
    public static void main(String[] args) {
        ListaEnlazada lista1 = new ListaEnlazada();
        lista1.insertarAlPrincipio(4);
        lista1.insertarAlPrincipio(3);
        lista1.insertarAlPrincipio(3);
        lista1.insertarAlPrincipio(2);
        lista1.insertarAlPrincipio(1);
        lista1.insertarAlPrincipio(1);

        System.out.print("Entrada Base:       ");
        lista1.imprimirLista();

        lista1.eliminarRepetidos();
        System.out.print("Con Dummy (Salida): ");
        lista1.imprimirLista();

        System.out.println("------------------------------------");

        ListaEnlazada l1 = new ListaEnlazada();
        l1.insertarAlPrincipio(4);
        l1.insertarAlPrincipio(2);
        l1.insertarAlPrincipio(1);

        ListaEnlazada l2 = new ListaEnlazada();
        l2.insertarAlPrincipio(4);
        l2.insertarAlPrincipio(3);
        l2.insertarAlPrincipio(1);

        System.out.print("Lista 1: ");
        l1.imprimirLista();
        System.out.print("Lista 2: ");
        l2.imprimirLista();

        ListaEnlazada fusionada = ListaEnlazada.fusionar(l1, l2);
        System.out.print("Fusionada (Salida): ");
        fusionada.imprimirLista();
    }
}