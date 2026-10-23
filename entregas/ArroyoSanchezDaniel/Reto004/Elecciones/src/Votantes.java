public class Votantes extends ListaAlumnos {

    private boolean haVotado;

    public Votantes(String nombre) {
        super(nombre);
        this.haVotado = false;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean haVotado() {
        return haVotado;
    }

    public void votar() {
        haVotado = true;

    }

}
