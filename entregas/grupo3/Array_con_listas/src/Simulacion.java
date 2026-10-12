public class Simulacion {
    public static void main(String[] args) {
        Arreglo array1 = new Arreglo(10);

        array1.agregarElemento(0,9);
        array1.obtenerElemento(0);
        array1.longitud();

        for(int i = 0; i < array1.longitud(); i++){
            array1.agregarElemento(i, i+23);
        }
        for(int i = 0; i < array1.longitud(); i++){
            System.out.println(array1.obtenerElemento(i));
        }
    }

}
