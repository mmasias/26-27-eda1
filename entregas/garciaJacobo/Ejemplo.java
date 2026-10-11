
package entregas.garciaJacobo;

public class Ejemplo {

    public static void main(String[] args) {
        Console console = new Console();
        Lista lista = new Lista();

        console.writeln("----- PRUEBAS DE LA CLASE LISTA -----");
        console.writeln();

        console.writeln("1. Lista recien creada");
        mostrarLista(lista, console);
        console.writeln("Esta vacia: " + lista.estaVacia());
        console.writeln("Longitud: " + lista.longitud());
        console.writeln();

        console.writeln("2. Insertar al final");
        lista.insertarFinal(20);
        lista.insertarFinal(30);
        mostrarLista(lista, console);
        console.writeln();

        console.writeln("3. Insertar al inicio");
        lista.insertarInicio(10);
        mostrarLista(lista, console);
        console.writeln();

        console.writeln("4. Insertar en una posicion");
        lista.insertar(1, 15);
        mostrarLista(lista, console);
        console.writeln();

        console.writeln("5. Obtener elemento en posicion 2");
        console.writeln("Elemento: " + lista.obtener(2));
        console.writeln();

        console.writeln("6. Modificar elemento en posicion 2");
        lista.modificar(2, 99);
        mostrarLista(lista, console);
        console.writeln();

        console.writeln("7. Eliminar elemento en posicion 1");
        lista.eliminar(1);
        mostrarLista(lista, console);
        console.writeln();

        console.writeln("8. Eliminar el inicio");
        lista.eliminarInicio();
        mostrarLista(lista, console);
        console.writeln();

        console.writeln("9. Eliminar el final");
        lista.eliminarFinal();
        mostrarLista(lista, console);
        console.writeln();

        console.writeln("10. Insertar en la posicion 0");
        lista.insertar(0, 5);
        mostrarLista(lista, console);
        console.writeln();

        console.writeln("11. Insertar al final usando la longitud");
        lista.insertar(lista.longitud(), 50);
        mostrarLista(lista, console);
        console.writeln();

        console.writeln("12. Vaciar la lista");
        while (!lista.estaVacia()) {
            lista.eliminarFinal();
        }
        mostrarLista(lista, console);
        console.writeln("Esta vacia: " + lista.estaVacia());
        console.writeln("Longitud: " + lista.longitud());
        console.writeln();

        console.writeln("----- PRUEBA DE AMPLIACION DEL ARRAY -----");

        Lista listaGrande = new Lista();

        console.writeln("Capacidad inicial: " + listaGrande.capacidad());

        listaGrande.insertarFinal(10);
        listaGrande.insertarFinal(20);
        listaGrande.insertarFinal(30);
        listaGrande.insertarFinal(40);
        listaGrande.insertarFinal(50);
        listaGrande.insertarFinal(60);
        listaGrande.insertarFinal(70);
        listaGrande.insertarFinal(80);
        listaGrande.insertarFinal(90);
        listaGrande.insertarFinal(100);

        console.writeln("Elementos insertados: " + listaGrande.longitud());
        console.writeln("Capacidad antes de insertar el elemento 11: "
                + listaGrande.capacidad());

        console.writeln("Insertando el elemento 110...");
        listaGrande.insertarFinal(110);

        console.writeln("Elementos insertados: " + listaGrande.longitud());
        console.writeln("Capacidad despues de insertar el elemento 11: "
                + listaGrande.capacidad());

        mostrarLista(listaGrande, console);

        console.writeln("Elemento en posicion 9: " + listaGrande.obtener(9));
        console.writeln("Elemento en posicion 10: " + listaGrande.obtener(10));
    }

    private static void mostrarLista(Lista lista, Console console) {
        console.write("Lista: [");

        for (int i = 0; i < lista.longitud(); i++) {
            console.write(lista.obtener(i));

            if (i < lista.longitud() - 1) {
                console.write(", ");
            }
        }

        console.writeln("]");
    }
}
