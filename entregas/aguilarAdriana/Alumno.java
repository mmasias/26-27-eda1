package entregas.aguilarAdriana;

public class Alumno {
    private int id;
    private String nombre;
    private boolean votoEmitido;

    public Alumno(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.votoEmitido = false;
    }

    public int obtenerId() {
        return this.id;
    }

    public String obtenerNombre() {
        return this.nombre;
    }

    public boolean haVotado() {
        return this.votoEmitido;
    }

    public boolean marcarComoVotado() {
        if (this.votoEmitido) {
            return false;
        }
        this.votoEmitido = true;
        return true;
    }
}
