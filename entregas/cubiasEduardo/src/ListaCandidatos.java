public class ListaCandidatos {
    private NodoCandidato cabeza;

    public ListaCandidatos() {
        this.cabeza = null;
    }

    public void agregarCandidato(String nombre) {
        if (!candidatoExiste(nombre)) {
            NodoCandidato nuevo = new NodoCandidato(new Candidato(nombre));
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
        }
    }

    public boolean candidatoExiste(String nombre) {
        return buscarCandidato(nombre) != null;
    }

    public Candidato buscarCandidato(String nombre) {
        NodoCandidato actual = cabeza;
        while (actual != null) {
            if (actual.candidato.getNombre().equals(nombre)) {
                return actual.candidato;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public boolean eliminarCandidato(String nombre) {
        if (cabeza == null) {
            return false;
        }
        if (cabeza.candidato.getNombre().equals(nombre)) {
            cabeza = cabeza.siguiente;
            return true;
        }
        NodoCandidato actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.candidato.getNombre().equals(nombre)) {
                actual.siguiente = actual.siguiente.siguiente;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public void votar(String nombre) {
        Candidato candidato = buscarCandidato(nombre);
        if (candidato != null) {
            candidato.votar();
        }
    }

    public void desvotar(String nombre) {
        Candidato candidato = buscarCandidato(nombre);
        if (candidato != null) {
            candidato.desvotar();
        }
    }

    public NodoCandidato getCabeza() {
        return cabeza;
    }

    public void mostrar() {
        NodoCandidato actual = cabeza;
        while (actual != null) {
            System.out.println("   - " + actual.candidato.getNombre() + ": " + actual.candidato.getVotos() + " votos");
            actual = actual.siguiente;
        }
    }
}
