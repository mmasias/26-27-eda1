package entregas.aguilarAdriana;

public class Candidaturas {
    private Candidato[] propuestas;
    private Candidato[] definitivas;
    private int cantidad;
    private boolean cerradas;

    public Candidaturas() {
        this.propuestas = new Candidato[10];
        this.definitivas = null;
        this.cantidad = 0;
        this.cerradas = false;
    }

    public boolean proponer(int idAlumno, ListaPresentes presentes) {
        if (this.cerradas || !presentes.contiene(idAlumno)
                || this.posicionDeAlumno(idAlumno) != -1) {
            return false;
        }
        if (this.cantidad == this.propuestas.length) {
            Candidato[] ampliadas = new Candidato[this.propuestas.length * 2];
            for (int i = 0; i < this.propuestas.length; i++) {
                ampliadas[i] = this.propuestas[i];
            }
            this.propuestas = ampliadas;
        }
        this.propuestas[this.cantidad] =
                new Candidato(idAlumno, String.valueOf(idAlumno));
        this.cantidad++;
        return true;
    }

    public boolean retirar(int idAlumno) {
        if (this.cerradas) {
            return false;
        }
        int posicion = this.posicionDeAlumno(idAlumno);
        if (posicion == -1) {
            return false;
        }
        for (int i = posicion; i < this.cantidad - 1; i++) {
            this.propuestas[i] = this.propuestas[i + 1];
        }
        this.propuestas[this.cantidad - 1] = null;
        this.cantidad--;
        return true;
    }

    public void cerrar() {
        this.definitivas = new Candidato[this.cantidad];
        for (int i = 0; i < this.cantidad; i++) {
            this.definitivas[i] = this.propuestas[i];
        }
        this.cerradas = true;
    }

    public boolean estanCerradas() {
        return this.cerradas;
    }

    public boolean esCandidato(String nombre) {
        return this.posicionDe(nombre) != -1;
    }

    public int posicionDe(String nombre) {
        Candidato[] candidatos = this.cerradas
                ? this.definitivas : this.propuestas;
        int limite = this.cerradas ? candidatos.length : this.cantidad;
        for (int i = 0; i < limite; i++) {
            if (candidatos[i].obtenerNombre().equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public Candidato[] vistaDefinitiva() {
        return this.definitivas;
    }

    private int posicionDeAlumno(int idAlumno) {
        for (int i = 0; i < this.cantidad; i++) {
            if (this.propuestas[i].obtenerIdAlumno() == idAlumno) {
                return i;
            }
        }
        return -1;
    }
}
