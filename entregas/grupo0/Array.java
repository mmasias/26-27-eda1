public class Array {

    private int tamaño;
    private Nodo cabeza;

    public Array(int tamaño){
        assert tamaño >= 0;

        Nodo dummy = new Nodo (-1);
        Nodo actual = dummy;

        for(int i = 0; i<tamaño; i++){
            Nodo nuevoNodo = new Nodo(0);
            actual.siguiente = nuevoNodo;
            actual = nuevoNodo;
        }

        this.tamaño = tamaño;
        this.cabeza = dummy.siguiente;
    }

    private Nodo obtenerPosicion(int posicion){
        assert posicion >= 0 && posicion < tamaño;
        Nodo actual = cabeza;

        int iteraciones = 0; 
        for (int i = 0; i < posicion; i++){
            actual = actual.siguiente;
        }
        return actual;
    }

    public int obtener(int posicion){
        return obtenerPosicion(posicion).dato;
    }

    public int longitud(){
        return tamaño;
    }

    public void asignar (int posicion, int valor){
        Nodo nodo = obtenerPosicion(posicion);
        nodo.dato = valor;
    }
}
