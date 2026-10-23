class ListaAlumnos {
    private NodoAlumno cabeza;
    private int numeroDeAlumnos;

    public ListaAlumnos() {
        this.cabeza = null;
        this.numeroDeAlumnos = 0;
    }

    public void registrarLlegada(Alumno nuevoAlumno) {
        NodoAlumno nuevo = new NodoAlumno(nuevoAlumno);

        if (cabeza == null) {
            cabeza = nuevo;
            return;
        }

        NodoAlumno actual = cabeza;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }

        actual.siguiente = nuevo;
        numeroDeAlumnos++;
    }

    public void eliminarAlumno(Alumno alumnoAEliminar) {
        if (cabeza != null) {
            if (cabeza.alumno.equals(alumnoAEliminar)) {
                cabeza = cabeza.siguiente;
            } else {
                NodoAlumno actual = cabeza;
                NodoAlumno anterior = null;

                while (actual != null && !actual.alumno.equals(alumnoAEliminar)) {
                    anterior = actual;
                    actual = actual.siguiente;
                }

                if (actual != null) {
                    anterior.siguiente = actual.siguiente;
                }
                numeroDeAlumnos--;
            }
        }
    }

    public int contarPresentes() {
        int contador = 0;
        NodoAlumno actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }
        return contador;
    }
}
