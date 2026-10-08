package entregas.BolivarMarcos.Reto002;

class ListaEnlazada {

    private Nodo cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }
    
    public void imprimirLista(){
        Nodo actual=cabeza;
        while (actual != null) {
            System.out.print(actual.dato+"->");
            actual = actual.siguiente;
        }
        System.out.println("null");
    }

    public void insertarEnPosicion(int posicion, int dato) {
        Nodo dummy = new Nodo(-1);
        dummy.siguiente= cabeza;
        Nodo actual= dummy;
        int pasos=0;
        while (actual.siguiente != null && pasos < posicion) {
            actual = actual.siguiente;
            pasos++;
        }
        Nodo nuevoNodo = new Nodo(dato);
        nuevoNodo.siguiente = actual.siguiente;
        actual.siguiente= nuevoNodo;
        cabeza= dummy.siguiente;

    }

    public void eliminarRepetidos() {
        Nodo dummy = new Nodo(-1);
        dummy.siguiente= cabeza;
        Nodo actual= dummy;
        while (actual.siguiente != null && actual.siguiente.siguiente != null) {
            if (actual.siguiente.dato == actual.siguiente.siguiente.dato){
                int valor= actual.siguiente.dato;
                while (actual.siguiente != null && actual.siguiente.dato == valor) {
                    actual.siguiente = actual.siguiente.siguiente;
                }
            }else {
                actual= actual.siguiente;
            }
        }
        cabeza= dummy.siguiente;
    }
}
