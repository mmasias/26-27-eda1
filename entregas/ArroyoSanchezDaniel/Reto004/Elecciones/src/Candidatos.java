public class Candidatos extends ListaAlumnos {

    private int votos;

    public Candidatos(String nombre) {
        super(nombre);
        this.votos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVotos() {
        return votos;
    }

    public void agregarVoto() {
        votos++;
    }

}
