public class ListaAlumnosPresentes {
    private final List alumnos;

    public ListaAlumnosPresentes() {
        alumnos = new List();
    }

    public boolean insertar(String alumno) {
        if (alumnos.contains(alumno)) {
            return false;
        }

        alumnos.insertEnd(alumno);
        return true;
    }

    public boolean eliminar(String alumno) {
        return alumnos.delete(alumno);
    }

    public boolean contiene(String alumno) {
        return alumnos.contains(alumno);
    }

    public int tamano() {
        return alumnos.size();
    }

    public String[] convertirAArray() {
        return alumnos.toArray();
    }

    public void mostrar() {
        alumnos.listAll();
    }
}