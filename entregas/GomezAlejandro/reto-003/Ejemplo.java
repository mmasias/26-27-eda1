package entregas.GomezAlejandro.reto_003;

public class Ejemplo {

    public static void main(String[] args) {

        Lista lista = new Lista();

        System.out.println("1. Lista recien creada");
        mostrarLista(lista);
        System.out.println("Esta vacia: " + lista.estaVacia());
        System.out.println("Longitud: " + lista.longitud());

        System.out.println("\n2. Insertar al final");
        lista.insertarFinal(20);
        lista.insertarFinal(30);
        mostrarLista(lista);

        System.out.println("\n3. Insertar al inicio");
        lista.insertarInicio(10);
        mostrarLista(lista);

        System.out.println("\n4. Insertar en posicion 1");
        lista.insertar(1, 15);
        mostrarLista(lista);

        System.out.println("\n5. Obtener elemento en posicion 2");
        System.out.println(lista.obtener(2));

        System.out.println("\n6. Modificar posicion 2");
        lista.modificar(2, 99);
        mostrarLista(lista);

        System.out.println("\n7. Eliminar posicion 1");
        lista.eliminar(1);
        mostrarLista(lista);

        System.out.println("\n8. Eliminar inicio");
        lista.eliminarInicio();
        mostrarLista(lista);

        System.out.println("\n9. Eliminar final");
        lista.eliminarFinal();
        mostrarLista(lista);

        System.out.println("\n10. Insertar en posicion 0");
        lista.insertar(0, 5);
        mostrarLista(lista);

        System.out.println("\n11. Insertar al final usando longitud");
        lista.insertar(lista.longitud(), 50);
        mostrarLista(lista);

        System.out.println("\n12. Vaciar la lista");

        while (!lista.estaVacia()) {
            lista.eliminarFinal();
        }

        mostrarLista(lista);
        System.out.println("Esta vacia: " + lista.estaVacia());
        System.out.println("Longitud: " + lista.longitud());

        System.out.println("\n13. Prueba de ampliacion");

        Lista listaGrande = new Lista();

        System.out.println("Capacidad inicial: " + listaGrande.capacidad());

        for (int i = 1; i <= 10; i++) {
            listaGrande.insertarFinal(i * 10);
        }

        System.out.println("Capacidad antes del elemento 11: "
                + listaGrande.capacidad());

        listaGrande.insertarFinal(110);

        System.out.println("Longitud: " + listaGrande.longitud());
        System.out.println("Capacidad despues del elemento 11: "
                + listaGrande.capacidad());

        mostrarLista(listaGrande);

        System.out.println("Elemento en posicion 9: "
                + listaGrande.obtener(9));
        System.out.println("Elemento en posicion 10: "
                + listaGrande.obtener(10));
    }

    private static void mostrarLista(Lista lista) {
        System.out.print("Lista: [");

        for (int i = 0; i < lista.longitud(); i++) {
            if (i > 0) {
                System.out.print(", ");
            }

            System.out.print(lista.obtener(i));
        }

        System.out.println("]");
    }
}