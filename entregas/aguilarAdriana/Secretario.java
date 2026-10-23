package entregas.aguilarAdriana;

public class Secretario {
    private int idAlumno;

    public Secretario(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int obtenerIdAlumno() {
        return this.idAlumno;
    }

    public int extraerPapeleta(Pila papeletas) {
        return papeletas.pop();
    }
}
