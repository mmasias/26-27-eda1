package entregas.aguilarAdriana;

public class ListaPresentes {
    private ListaEnlazada alumnos;

    public ListaPresentes() {
        this.alumnos = new ListaEnlazada();
    }

    public boolean entrar(int idAlumno) {
        if (this.alumnos.contiene(idAlumno)) {
            return false;
        }
        this.alumnos.insertarAlPrincipio(idAlumno);
        return true;
    }

    public boolean salir(int idAlumno) {
        return this.alumnos.eliminar(idAlumno);
    }

    public boolean contiene(int idAlumno) {
        return this.alumnos.contiene(idAlumno);
    }

    public int cantidad() {
        return this.alumnos.cantidad();
    }

    public void imprimir() {
        this.alumnos.imprimirLista();
    }
}
