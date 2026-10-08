public class Ejemplo {

    public static void main(String[] args) {

        ListaEnlazada lista;

        lista = new ListaEnlazada();
        lista.insertar(1);
        lista.insertar(1);
        lista.insertar(2);
        lista.insertar(3);
        lista.insertar(3);
        lista.insertar(4);

        System.out.println("Entrada: " + lista);
        lista.eliminarRepetidos();
        System.out.println("Salida:  " + lista);
        System.out.println();

        lista = new ListaEnlazada();
        lista.insertar(1);
        lista.insertar(1);
        lista.insertar(1);

        System.out.println("Entrada: " + lista);
        lista.eliminarRepetidos();
        System.out.println("Salida:  " + lista);
        System.out.println();

        lista = new ListaEnlazada();
        lista.insertar(1);
        lista.insertar(2);
        lista.insertar(2);

        System.out.println("Entrada: " + lista);
        lista.eliminarRepetidosSinDummy();
        System.out.println("Salida:  " + lista);
        System.out.println();

        lista = new ListaEnlazada();
        lista.insertar(1);
        lista.insertar(2);
        lista.insertar(3);

        System.out.println("Entrada: " + lista);
        lista.eliminarRepetidosSinDummy();
        System.out.println("Salida:  " + lista);
        System.out.println();

        lista = new ListaEnlazada();
        lista.insertar(5);
        lista.insertar(5);
        lista.insertar(6);
        lista.insertar(6);

        System.out.println("Entrada: " + lista);
        lista.eliminarRepetidos();
        System.out.println("Salida:  " + lista);
        System.out.println();

        lista = new ListaEnlazada();

        System.out.println("Entrada: " + lista);
        lista.eliminarRepetidos();
        System.out.println("Salida:  " + lista);
        System.out.println();

        ListaEnlazada a = new ListaEnlazada();
        a.insertar(1);
        a.insertar(4);
        a.insertar(7);

        ListaEnlazada b = new ListaEnlazada();
        b.insertar(2);
        b.insertar(3);
        b.insertar(8);
        b.insertar(9);

        System.out.println("A: " + a);
        System.out.println("B: " + b);

        ListaEnlazada resultado = ListaEnlazada.fusionar(a, b);

        System.out.println("Resultado: " + resultado);
        System.out.println("A después: " + a);
        System.out.println("B después: " + b);
    }
}