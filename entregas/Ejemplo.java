public class Ejemplo {

    static ListaEnlazada crear(int... datos) {
        ListaEnlazada lista = new ListaEnlazada();
        for (int d : datos) {
            lista.agregarAlFinal(d);
        }
        return lista;
    }

    public static void main(String[] args) {
        ListaEnlazada l = crear(1, 2, 3);
        System.out.println(l);   // 1 -> 2 -> 3
    }
}