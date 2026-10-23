public class NodoCandidato {
    Candidato candidato;
    NodoCandidato siguiente;

    public NodoCandidato(Candidato candidato) {
        this.candidato = candidato;
        this.siguiente = null;
    }
}
