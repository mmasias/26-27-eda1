public class Prueba {
    public static void main(String[] args) {
        ListaEnlazada lista = new ListaEnlazada();
        lista.insertarAlPrincipio(1);
        lista.insertarAlPrincipio(1);
        lista.insertarAlPrincipio(2);
        lista.insertarAlPrincipio(3);
        lista.insertarAlPrincipio(3);
        lista.imprimirLista();
        lista.eliminarRepetidos();
        lista.imprimirLista();


        ListaEnlazada lista2 = new ListaEnlazada();
        lista2.insertarAlPrincipio(1);
        lista2.insertarAlPrincipio(2);
        lista2.insertarAlPrincipio(2);
        lista2.insertarAlPrincipio(6);
        lista2.insertarAlPrincipio(7);
        lista2.imprimirLista();
        lista2.eliminarRepetidosSinDummy();
        lista2.imprimirLista();

    }
}