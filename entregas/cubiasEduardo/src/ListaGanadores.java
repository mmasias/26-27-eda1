public class ListaGanadores {
    private NodoGanador cabeza;
    private int cantidad;

    public ListaGanadores() {
        this.cabeza = null;
        this.cantidad = 0;
    }

    public void agregarGanador(Candidato candidato) {
        NodoGanador nuevo = new NodoGanador(candidato);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        cantidad++;
    }

    public int getCantidad() {
        return cantidad;
    }

    public boolean hayEmpate() {
        return cantidad > 1;
    }

    public void mostrar() {
        if (cabeza == null) {
            System.out.println("No hay ganadores registrados.");
            return;
        }
        if (hayEmpate()) {
            System.out.println("Empate detectado con " + cabeza.candidato.getVotos() + " votos entre:");
        } else {
            System.out.println("Ganador proclamado con " + cabeza.candidato.getVotos() + " votos:");
        }
        NodoGanador actual = cabeza;
        while (actual != null) {
            System.out.println(" - " + actual.candidato.getNombre());
            actual = actual.siguiente;
        }
    }
}
