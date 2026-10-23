package entregas.aguilarAdriana;

public class Votacion {
    private ListaPresentes presentes;
    private Candidaturas candidaturas;
    private Pila papeletas;
    private ListaEnlazada alumnosQueHanVotado;
    private Profesor presidente;
    private Secretario secretario;
    private ResultadoRecuento recuento;
    private boolean candidaturasCerradas;
    private boolean votacionAbierta;

    public Votacion(Profesor presidente) {
        this.presidente = presidente;
        this.presentes = new ListaPresentes();
        this.candidaturas = new Candidaturas();
        this.papeletas = new Pila();
        this.alumnosQueHanVotado = new ListaEnlazada();
        this.candidaturasCerradas = false;
        this.votacionAbierta = false;
    }

    public void registrarLlegada(int idAlumno) {
        this.presentes.entrar(idAlumno);
    }

    public void registrarSalida(int idAlumno) {
        this.presentes.salir(idAlumno);
    }

    public boolean proponerCandidato(int idAlumno) {
        return this.candidaturas.proponer(idAlumno, this.presentes);
    }

    public boolean retirarCandidato(int idAlumno) {
        return this.candidaturas.retirar(idAlumno);
    }

    public void cerrarCandidaturas() {
        this.candidaturas.cerrar();
        this.candidaturasCerradas = true;
        this.recuento = new ResultadoRecuento(
                this.candidaturas.vistaDefinitiva());
    }

    public void elegirSecretario(int idAlumno) {
        this.secretario = this.presidente.elegirSecretario(
                idAlumno, this.presentes);
    }

    public void abrirVotacion() {
        if (this.candidaturasCerradas && this.secretario != null) {
            this.presidente.abrirVotacion();
            this.votacionAbierta = true;
        }
    }

    public boolean votar(int idAlumno, int idPapeleta) {
        if (!this.votacionAbierta || !this.presentes.contiene(idAlumno)
                || this.alumnosQueHanVotado.contiene(idAlumno)) {
            return false;
        }
        this.papeletas.push(idPapeleta);
        this.alumnosQueHanVotado.insertarAlPrincipio(idAlumno);
        return true;
    }

    public void contarVotos() {
        if (this.recuento == null || this.secretario == null) {
            return;
        }
        while (!this.papeletas.estaVacia()) {
            int idPapeleta = this.secretario.extraerPapeleta(
                    this.papeletas);
            this.recuento.sumarVotoDe(String.valueOf(idPapeleta));
        }
    }

    public void corregirAnotacion(String opcion) {
        this.presidente.corregirAnotacion(opcion, this.recuento);
    }

    public void cerrarVotacion() {
        this.presidente.cerrarVotacion();
        this.votacionAbierta = false;
    }

    public void proclamarResultado() {
        this.presidente.proclamar(this.recuento);
    }
}
