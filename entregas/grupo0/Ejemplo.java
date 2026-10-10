public class Ejemplo {

    public static void main(String[] args) {

        Array miArray = new Array(5);
        System.out.println("Longitud del array: " + miArray.longitud());

        miArray.asignar(0, 10);
        miArray.asignar(2, 25);
        miArray.asignar(miArray.longitud() - 1, 99);


        System.out.println("\nLecturas individuales:");
        System.out.println("Elemento en pos 0: " + miArray.obtener(0));
        System.out.println("Elemento en pos 1: " + miArray.obtener(1));
        System.out.println("Elemento en pos 2: " + miArray.obtener(2));
        System.out.println("Elemento en pos 3: " + miArray.obtener(3));
        System.out.println("Elemento en pos 4: " + miArray.obtener(4));

        miArray.mostrar();
    }
}
