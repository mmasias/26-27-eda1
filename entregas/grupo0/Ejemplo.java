public class Ejemplo {

    public static void main(String[] args) {
        // 1. Crear un array de 5 posiciones
        Array miArray = new Array(5);
        System.out.println("Longitud del array: " + miArray.longitud());

        miArray.asignar(0, 10);
        miArray.asignar(2, 25);
        miArray.asignar(miArray.longitud() - 1, 99);


        System.out.println("\nLecturas individuales:");
        System.out.println("Elemento en pos 0: " + miArray.obtener(0));
        System.out.println("Elemento en pos 1: " + miArray.obtener(1));
        System.out.println("Elemento en pos 2: " + miArray.obtener(2));
        System.out.println("Elemento en pos 4: " + miArray.obtener(4));
    }
}
