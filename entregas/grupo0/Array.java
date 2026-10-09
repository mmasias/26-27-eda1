public class Array {

    private int tamaño;
    private ListaEnlazada lista;

    public Array(int tamaño) {
        this.tamaño = tamaño;
        lista = new ListaEnlazada();

        for (int i = 0; i < tamaño; i++){
            lista.insertarEnPosicion(i, 0);
        }

    }

    public void obtenerPosicion(int posicion){
        Nodo actual = cabeza;
    }

}
