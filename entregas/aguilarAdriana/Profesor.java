package entregas.aguilarAdriana;

public class Profesor {
    private String nombre;
    private boolean votacionAbierta;

    public Profesor(String nombre) {
        this.nombre = nombre;
        this.votacionAbierta = false;
    }

    public String obtenerNombre() {
        return this.nombre;
    }

    public Secretario elegirSecretario(int idAlumno, ListaPresentes presentes) {
        if (!presentes.contiene(idAlumno)) {
            return null;
        }
        return new Secretario(idAlumno);
    }

    public void abrirVotacion() {
        this.votacionAbierta = true;
    }

    public void cerrarVotacion() {
        this.votacionAbierta = false;
    }

    public boolean corregirAnotacion(
            String opcion,
            ResultadoRecuento recuento) {
        return recuento.corregirVotoDe(opcion);
    }

    public void proclamar(ResultadoRecuento recuento) {
        System.out.println(recuento.proclamar());
    }
}
