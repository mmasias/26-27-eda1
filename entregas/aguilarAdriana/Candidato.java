package entregas.aguilarAdriana;

public class Candidato {
    private int idAlumno;
    private String nombre;
    private int votos;

    public Candidato(int idAlumno, String nombre) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.votos = 0;
    }

    public int obtenerIdAlumno() {
        return this.idAlumno;
    }

    public String obtenerNombre() {
        return this.nombre;
    }

    public int obtenerVotos() {
        return this.votos;
    }

    public void sumarVoto() {
        this.votos++;
    }

    public boolean corregirVoto() {
        if (this.votos == 0) {
            return false;
        }
        this.votos--;
        return true;
    }
}
