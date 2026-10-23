public class Candidato {
    private String nombre;
    private int votos;

    public Candidato(String nombre) {
        this.nombre = nombre;
        this.votos = 0;
    }

    public void votar() {
        this.votos++;
    }

    public void desvotar() {
        if (this.votos > 0) {
            this.votos--;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getVotos() {
        return votos;
    }
}
