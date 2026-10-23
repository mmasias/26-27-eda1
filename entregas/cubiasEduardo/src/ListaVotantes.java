public class ListaVotantes {
    private NodoVotante cabeza;

    public ListaVotantes() {
        this.cabeza = null;
    }

    public void registrarVoto(String nombre) {
        NodoVotante nuevo = new NodoVotante(nombre);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
    }

    public boolean haVotado(String nombre) {
        NodoVotante actual = cabeza;
        while (actual != null) {
            if (actual.nombre.equals(nombre)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public void mostrar() {
        NodoVotante actual = cabeza;
        while (actual != null) {
            System.out.print("[" + actual.nombre + "] ");
            actual = actual.siguiente;
        }
        System.out.println();
    }
}
