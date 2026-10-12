public class Ejemplo {
    public static void main(String[] args) {
        MiLista lista = new MiLista();

        lista.insertar(0, 10);
        
        lista.insertar(1, 20);
        
        lista.insertar(2, 30);

        System.out.print("Lista inicial: ");
        lista.mostrar();

        lista.insertar(1, 15);
        System.out.print("Después de insertar: ");
        lista.mostrar();

        lista.eliminar(2);
        System.out.print("Después de eliminar: ");
        
        lista.mostrar();

        System.out.println("Tamaño: " + lista.tamaño());
        System.out.println("¿Contiene 30? " + lista.contiene(30));
        System.out.println("¿Está vacía? " + lista.estaVacia());
    }
}
