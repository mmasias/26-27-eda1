public class NodoCandidato {
    
    public Candidato candidato;
    public NodoCandidato siguiente;

    public NodoCandidato(Candidato candidato) {
        this.candidato = candidato;
        this.siguiente = null;
    }
}

