public class Simulacion {
    public static void main(String[] args) {
        Lista lista = new Lista();

        lista.insertarAlPrincipio(4);
        System.out.println("Elemento inicial: " + lista.obtenerElemento());
        lista.imprimirLista();
        lista.eliminarAlPrincipio();
        lista.imprimirLista();

    }

}