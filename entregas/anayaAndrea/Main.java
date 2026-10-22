public class Main {
    public static void main(String[] args) {
        ArraySimulado a = new ArraySimulado(3, 0);
        a.set(0, 10);
        a.set(2, 7);
        a.mostrar();
        System.out.println("Longitud: " + a.length());

        try {
            a.set(3, 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        }

        ListaSimulada l = new ListaSimulada(2);
        l.agregar(10);
        l.agregar(20);
        l.agregar(30);
        l.mostrar();
        System.out.println("Size: " + l.size() + " Capacidad: " + l.capacidad());

        l.insertar(1, 15);
        l.mostrar();

        l.eliminar(0);
        l.mostrar();

        try {
            l.get(10);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}