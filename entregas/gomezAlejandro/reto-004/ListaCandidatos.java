public class ListaCandidatos {
    private final List candidatos;
    private final ListaAlumnosPresentes presentes;

    public ListaCandidatos(ListaAlumnosPresentes presentes) {
        this.presentes = presentes;
        candidatos = new List();
    }

    public boolean insertar(String candidato) {
        if (!presentes.contiene(candidato)) {
            return false;
        }

        if (candidatos.contains(candidato)) {
            return false;
        }

        candidatos.insertEnd(candidato);
        return true;
    }

    public boolean retirar(String candidato) {
        return candidatos.delete(candidato);
    }

    public boolean contiene(String candidato) {
        return candidatos.contains(candidato);
    }

    public int tamano() {
        return candidatos.size();
    }

    public String[] convertirAArray() {
        return candidatos.toArray();
    }

    public void mostrar() {
        candidatos.listAll();
    }
}