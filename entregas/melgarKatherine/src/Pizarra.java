class Pizarra {

    private class NodoConteo {
        String candidato;
        int votos;
        NodoConteo siguiente;

        NodoConteo(String candidato) {
            this.candidato = candidato;
            this.votos = 0;
            this.siguiente = null;
        }
    }

    private NodoConteo cabeza;

    public void registrarCandidatura(String nombre) {
        NodoConteo nuevo = new NodoConteo(nombre);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
    }

    public void retirarCandidatura(String nombre) {
        NodoConteo dummy = new NodoConteo("");
        dummy.siguiente = cabeza;
        
        for (NodoConteo previo = dummy, actual = cabeza; actual != null; actual = actual.siguiente) {
            if (actual.candidato.equals(nombre)) {
                previo.siguiente = actual.siguiente;
            } else {
                previo = actual;
            }
        }
        cabeza = dummy.siguiente;
    }

    public void sumarVoto(String nombre) {
        boolean sumado = false;
        for (NodoConteo actual = cabeza; actual != null; actual = actual.siguiente) {
            if (actual.candidato.equals(nombre)) {
                actual.votos++;
                sumado = true;
            }
        }
    }

    public void corregirAnotacion(String nombre, int votosReales) {
        for (NodoConteo actual = cabeza; actual != null; actual = actual.siguiente) {
            if (actual.candidato.equals(nombre)) {
                actual.votos = votosReales;
            }
        }
    }

    public boolean esCandidatoValido(String nombre) {
        for (NodoConteo actual = cabeza; actual != null; actual = actual.siguiente) {
            if (actual.candidato.equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public void proclamarGanador() {
        int maxVotos = -1;
        boolean hayEmpate = false;
        String ganador = "";

        for (NodoConteo actual = cabeza; actual != null; actual = actual.siguiente) {
            if (actual.votos > maxVotos) {
                maxVotos = actual.votos;
                ganador = actual.candidato;
                hayEmpate = false;
            } else if (actual.votos == maxVotos) {
                hayEmpate = true;
            }
        }

        System.out.println("-".repeat(30));
        System.out.println("Resultados de la votación:");
        if (hayEmpate) {
            System.out.println(">>> Empate, se debe repetir la votación");
        } else {
            System.out.println(">>> Ganador: " + ganador + " con " + maxVotos + " votos.");
        }
        System.out.println("-".repeat(30));
    }
}