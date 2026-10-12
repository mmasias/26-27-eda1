public class Ejemplo {
public static void main(String[] args) {
        demostrarListaConArray();
        demostrarListaConCursores();
    }

    private static void demostrarListaConArray() {
        titulo("A. ListaConArray: vista del usuario vs. vista interna");
        ListaConArray lista = new ListaConArray();
        System.out.print("Lista vacía      : ");
        lista.imprimirLista();
        System.out.print("Interior         : ");
        lista.imprimirInterior();

        titulo("B. Crecimiento: el array interno se duplica al llenarse");
        int[] llegadas = { 10, 20, 30, 40, 50 };
        for (int dato : llegadas) {
            lista.insertarAlFinal(dato);
            System.out.print("insertarAlFinal(" + dato + ") -> ");
            lista.imprimirInterior();
        }
        System.out.println("Ampliaciones realizadas: " + lista.ampliaciones());

        titulo("C. Inserción intermedia: desplazar a la derecha desde el final");
        mostrarAntes(lista);
        lista.insertarEnPosicion(2, 25);
        mostrarDespues(lista, "insertarEnPosicion(2, 25)");

        lista.insertarAlPrincipio(5);
        mostrarDespues(lista, "insertarAlPrincipio(5)");

        titulo("D. Eliminación sin huecos: desplazar a la izquierda");
        mostrarAntes(lista);
        lista.eliminarEnPosicion(0);
        mostrarDespues(lista, "eliminarEnPosicion(0)");
        lista.eliminarEnPosicion(lista.longitud() - 1);
        mostrarDespues(lista, "eliminarEnPosicion(último)");

        titulo("E. Eliminación por valor (todas las apariciones)");
        lista.insertarEnPosicion(1, 30);
        lista.insertarAlFinal(30);
        mostrarAntes(lista);
        lista.eliminarPorValor(30);
        System.out.print("eliminarPorValor(30) -> ");
        lista.imprimirLista();

        titulo("F. Posiciones no válidas");
        lista.insertarEnPosicion(-1, 7);
        lista.insertarEnPosicion(lista.longitud() + 1, 7);
        lista.eliminarEnPosicion(lista.longitud());
        lista.obtener(99);

        titulo("G. Coste: insertar al principio vs. al final (lista de 1000)");
        ListaConArray grande = new ListaConArray();
        for (int dato = 0; dato < 1000; dato++) {
            grande.insertarAlFinal(dato);
        }
        System.out.println("Ampliaciones para llegar a 1000 elementos: " + grande.ampliaciones()
                + " (capacidad final " + grande.capacidadInterna() + ")");
        grande.insertarAlFinal(-1);
        System.out.println("insertarAlFinal     -> desplazamientos: " + grande.desplazamientosDeLaUltimaOperacion());
        grande.insertarAlPrincipio(-1);
        System.out.println("insertarAlPrincipio -> desplazamientos: " + grande.desplazamientosDeLaUltimaOperacion());
        System.out.println("obtener(500) = " + grande.obtener(500) + " -> acceso directo, sin recorrido");
    }

    private static void demostrarListaConCursores() {
        titulo("H. Extensión: ListaConCursores (nodos simulados con dos arrays)");
        ListaConCursores lista = new ListaConCursores(6);
        lista.insertarEnPosicion(0, 10);
        lista.insertarEnPosicion(1, 20);
        lista.insertarEnPosicion(2, 30);
        System.out.print("Lista: ");
        lista.imprimirLista();
        lista.imprimirInterior();

        System.out.println();
        lista.insertarEnPosicion(1, 15);
        System.out.print("insertarEnPosicion(1, 15) -> ");
        lista.imprimirLista();
        System.out.println("  (15 ocupa la casilla 3, pero ningún dato se ha movido: solo cambian índices)");
        lista.imprimirInterior();

        System.out.println();
        lista.eliminarPorValor(10);
        System.out.print("eliminarPorValor(10) -> ");
        lista.imprimirLista();
        System.out.println("  (la casilla 0 vuelve a la cadena de libres)");
        lista.imprimirInterior();

        System.out.println();
        lista.insertarEnPosicion(99, 40);
        lista.insertarEnPosicion(99, 50);
        lista.insertarEnPosicion(99, 60);
        lista.insertarEnPosicion(99, 70);
        System.out.print("Tras llenar: ");
        lista.imprimirLista();
    }

    private static void mostrarAntes(ListaConArray lista) {
        System.out.print("Antes: ");
        lista.imprimirLista();
    }

    private static void mostrarDespues(ListaConArray lista, String operacion) {
        System.out.print(operacion + " -> ");
        lista.imprimirLista();
        System.out.println("   desplazamientos: " + lista.desplazamientosDeLaUltimaOperacion());
    }

    private static void titulo(String texto) {
        System.out.println();
        System.out.println("=== " + texto + " ===");
    }    
}
