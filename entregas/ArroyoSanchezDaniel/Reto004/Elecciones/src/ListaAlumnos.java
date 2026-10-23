public abstract class ListaAlumnos {
    protected String nombre;
    protected Nodo cabeza;

    public ListaAlumnos(String nombre) {
        this.nombre = nombre;
        this.cabeza = null;
    }

    public void añadirAlumno(String nombre) {
        Nodo nuevoAlumno = new Nodo(nombre);
        if (cabeza == null) {
            cabeza = nuevoAlumno;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoAlumno;
        }
    }

    public void eliminarAlumno(String nombre) {
        if (cabeza == null) {
            return;
        }
        if (cabeza.dato.equals(nombre)) {
            cabeza = cabeza.siguiente;
            return;
        }
        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.dato.equals(nombre)) {
                actual.siguiente = actual.siguiente.siguiente;
                return;
            }
            actual = actual.siguiente;
        }
    }

    public boolean contieneAlumno(String nombre) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.dato.equalsIgnoreCase(nombre)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public void mostrarAlumnos() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.siguiente;
        }
    }
}