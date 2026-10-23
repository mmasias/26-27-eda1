public class Papeleta {
    private final String alumno;
    private final String voto;

    public Papeleta(String alumno, String voto) {
        this.alumno = alumno;
        this.voto = voto;
    }

    public String getAlumno() {
        return alumno;
    }

    public String getVoto() {
        return voto;
    }
}