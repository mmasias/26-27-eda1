public class ListaAlumnosPresentes {
    private NodoAlumno cabeza;

    public ListaAlumnosPresentes() {
        this.cabeza = null;
    }

    public void registrarLlegada(String nombre) {
        NodoAlumno nuevo = new NodoAlumno(nombre);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
    }

    public boolean registrarSalida(String nombre) {
        if (cabeza == null) {
            return false;
        }
        if (cabeza.nombre.equals(nombre)) {
            cabeza = cabeza.siguiente;
            return true;
        }
        NodoAlumno actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.nombre.equals(nombre)) {
                actual.siguiente = actual.siguiente.siguiente;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public boolean estaPresente(String nombre) {
        NodoAlumno actual = cabeza;
        while (actual != null) {
            if (actual.nombre.equals(nombre)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public void mostrar() {
        NodoAlumno actual = cabeza;
        while (actual != null) {
            System.out.print("[" + actual.nombre + "] ");
            actual = actual.siguiente;
        }
        System.out.println();
    }
}
