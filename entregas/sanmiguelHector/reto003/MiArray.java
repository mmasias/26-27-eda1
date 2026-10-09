public class MiArray
{
    public static void main(String[] args)
    {
        Array miArray = new Array(5);

        miArray.añadir(10);
        miArray.añadir(20);
        miArray.añadir(30);

        System.out.println("Array original:");
        miArray.imprimirArray();

        miArray.añadir(99, 1);

        System.out.println("\nTras modificar la posición 1 con el valor 99:");
        miArray.imprimirArray();

        miArray.añadir(40, miArray.getNumeroDatos());

        System.out.println("\nTras añadir un nuevo dato al final:");
        miArray.imprimirArray();
    }
}