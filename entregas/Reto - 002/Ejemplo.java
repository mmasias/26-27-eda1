public class Ejemplo {

    public static void main(String[] args) {

        ListaEnlazada miLista;

        miLista = new ListaEnlazada();
        miLista.insertar(1);
        miLista.insertar(1);
        miLista.insertar(2);
        miLista.insertar(3);
        miLista.insertar(3);
        miLista.insertar(4);

        System.out.println("Entrada: " + miLista);
        miLista.eliminarRepetidos();
        System.out.println("Salida:  " + miLista);
        System.out.println();

        miLista = new ListaEnlazada();
        miLista.insertar(1);
        miLista.insertar(1);
        miLista.insertar(1);

        System.out.println("Entrada: " + miLista);
        miLista.eliminarRepetidos();
        System.out.println("Salida:  " + miLista);
        System.out.println();

        miLista = new ListaEnlazada();
        miLista.insertar(1);
        miLista.insertar(2);
        miLista.insertar(2);

        System.out.println("Entrada: " + miLista);
        miLista.eliminarRepetidosSinDummy();
        System.out.println("Salida:  " + miLista);
        System.out.println();

        miLista = new ListaEnlazada();
        miLista.insertar(1);
        miLista.insertar(2);
        miLista.insertar(3);

        System.out.println("Entrada: " + miLista);
        miLista.eliminarRepetidosSinDummy();
        System.out.println("Salida:  " + miLista);
        System.out.println();

        miLista = new ListaEnlazada();
        miLista.insertar(5);
        miLista.insertar(5);
        miLista.insertar(6);
        miLista.insertar(6);

        System.out.println("Entrada: " + miLista);
        miLista.eliminarRepetidos();
        System.out.println("Salida:  " + miLista);
        System.out.println();

        miLista = new ListaEnlazada();

        System.out.println("Entrada: " + miLista);
        miLista.eliminarRepetidos();
        System.out.println("Salida:  " + miLista);
        System.out.println();

        ListaEnlazada listaUno = new ListaEnlazada();
        listaUno.insertar(1);
        listaUno.insertar(4);
        listaUno.insertar(7);

        ListaEnlazada listaDos = new ListaEnlazada();
        listaDos.insertar(2);
        listaDos.insertar(3);
        listaDos.insertar(8);
        listaDos.insertar(9);

        System.out.println("A: " + listaUno);
        System.out.println("B: " + listaDos);

        ListaEnlazada resultado = ListaEnlazada.fusionar(listaUno, listaDos);

        System.out.println("Resultado: " + resultado);
        System.out.println("A después: " + listaUno);
        System.out.println("B después: " + listaDos);
    }
}