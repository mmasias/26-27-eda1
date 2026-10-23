public class ListaCandidatos {
    private NodoCandidato cabeza;

    public ListaCandidatos() {
        this.cabeza = null;
    }

    public void agregarCandidato(Alumno candidato) {
        if (candidato.estaPresente) {
            Candidato nuevoCandidato = new Candidato(candidato);
            if (cabeza == null) {
                cabeza = new NodoCandidato(nuevoCandidato);
            } else {
                NodoCandidato actual = cabeza;

                while (actual.siguiente != null) {
                    actual = actual.siguiente;
                }

                actual.siguiente = new NodoCandidato(nuevoCandidato);
            }

        }
    }

    public void eliminarCandidato(Candidato candidatoAEliminar) {
        if (cabeza != null) {
            if (cabeza.candidato.equals(candidatoAEliminar)) {
                cabeza = cabeza.siguiente;
            } else {
                NodoCandidato actual = cabeza;
                NodoCandidato anterior = null;

                while (actual != null && !actual.candidato.equals(candidatoAEliminar)) {
                    anterior = actual;
                    actual = actual.siguiente;
                }

                if (actual != null) {
                    anterior.siguiente = actual.siguiente;
                }
            }
        }
    }

    public boolean sumarVotoA(String nombreBuscado) {
        NodoCandidato actual = cabeza;

        while (actual != null) {
            if (actual.candidato.nombreCandidato.equals(nombreBuscado)) {
                actual.candidato.darVoto();
                return true;
            }
            actual = actual.siguiente;
        }

        return false;
    }


    public void calcularGanador() {
        NodoCandidato actual = cabeza;
        int maxVotos = -1;
        boolean hayEmpate = false;
        String nombreGanador = "";

        while (actual != null) {
            if (actual.candidato.cantidadDeVotos > maxVotos) {
                maxVotos = actual.candidato.cantidadDeVotos;
                nombreGanador = actual.candidato.nombreCandidato;
                hayEmpate = false;
            } else if (actual.candidato.cantidadDeVotos == maxVotos) {
                hayEmpate = true;
            }
            actual = actual.siguiente;
        }

        if (hayEmpate) {
            System.out.println("El resultado es un EMPATE con " + maxVotos + " votos.");
        } else {
            System.out.println("El ganador es " + nombreGanador + " con " + maxVotos + " votos.");
        }
    }
}
