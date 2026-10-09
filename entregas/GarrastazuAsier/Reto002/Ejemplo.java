public class Ejemplo {
    public static void main(String[] args) {
        ListaEnlazada lista = new ListaEnlazada();

        lista.agregar(1);
        lista.agregar(1);
        lista.agregar(2);
        lista.agregar(3);
        lista.agregar(3);

        System.out.println("Antes: " + lista);

        lista.eliminarRepetidos();

        System.out.println("Después: " + lista);

        ListaEnlazada a = new ListaEnlazada();
        a.agregar(1);
        a.agregar(4);
        a.agregar(7);

        ListaEnlazada b = new ListaEnlazada();
        b.agregar(2);
        b.agregar(3);
        b.agregar(8);

        System.out.println("Lista a: " + a);
        System.out.println("Lista b: " + b);

        ListaEnlazada resultado = ListaEnlazada.fusionar(a, b);

        System.out.println("Fusionada: " + resultado);
        System.out.println("a después: " + a);
        System.out.println("b después: " + b);
    }
}
