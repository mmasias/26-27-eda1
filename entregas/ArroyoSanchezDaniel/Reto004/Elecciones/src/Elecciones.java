public class Elecciones {

    private Candidatos[] candidatos;
    private int numCandidatos;

    private Votantes[] votantes;
    private int numVotantes;

    private ListaAlumnos listaAlumnos;

    public Elecciones() {
        this.candidatos = new Candidatos[10];
        this.numCandidatos = 0;

        this.votantes = new Votantes[50];
        this.numVotantes = 0;

        this.listaAlumnos = new ListaAlumnos("Clase General") {
        };
    }

    public static void main(String[] args) {
        Elecciones elecciones = new Elecciones();
        elecciones.iniciarElecciones();
    }

    public void registrarAlumnoEnClase(String nombre) {
        listaAlumnos.añadirAlumno(nombre);
        if (numVotantes < votantes.length) {
            votantes[numVotantes] = new Votantes(nombre);
            numVotantes++;
        }
    }

    public void agregarCandidato(String nombre) {
        if (!listaAlumnos.contieneAlumno(nombre)) {
            System.out.println("Error: '" + nombre + "' no está en clase y no puede ser candidato.");
            return;
        }

        for (int i = 0; i < numCandidatos; i++) {
            if (candidatos[i].getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Error: '" + nombre + "' ya es candidato.");
                return;
            }
        }

        if (numCandidatos < candidatos.length) {
            candidatos[numCandidatos] = new Candidatos(nombre);
            numCandidatos++;
            System.out.println("Candidato registrado: " + nombre);
        } else {
            System.out.println("Límite de candidatos alcanzado.");
        }
    }

    public void emitirVoto(String nombreVotante, String nombreCandidato) {
        Votantes votante = buscarVotante(nombreVotante);

        if (votante == null) {
            System.out.println("Error: El alumno '" + nombreVotante + "' no pertenece a la clase.");
            return;
        }

        if (votante.haVotado()) {
            System.out.println("Error: El alumno '" + nombreVotante + "' ya ha ejercido su voto.");
            return;
        }

        Candidatos candidatoElegido = buscarCandidato(nombreCandidato);

        if (candidatoElegido == null) {
            System.out.println("Error: El candidato '" + nombreCandidato + "' no existe.");
            return;
        }

        candidatoElegido.agregarVoto();
        votante.votar();
        System.out.println("¡Voto registrado con éxito! " + nombreVotante + " votó por " + nombreCandidato);
    }

    private Votantes buscarVotante(String nombre) {
        for (int i = 0; i < numVotantes; i++) {
            if (votantes[i].getNombre().equalsIgnoreCase(nombre)) {
                return votantes[i];
            }
        }
        return null;
    }

    private Candidatos buscarCandidato(String nombre) {
        for (int i = 0; i < numCandidatos; i++) {
            if (candidatos[i].getNombre().equalsIgnoreCase(nombre)) {
                return candidatos[i];
            }
        }
        return null;
    }

    public void mostrarResultados() {
        System.out.println("\n=== RESULTADOS DE LAS ELECCIONES ===");
        for (int i = 0; i < numCandidatos; i++) {
            System.out.println(candidatos[i].getNombre() + ": " + candidatos[i].getVotos() + " votos");
        }
    }

    public void iniciarElecciones() {
        registrarAlumnoEnClase("Ana Pérez");
        registrarAlumnoEnClase("Carlos Gómez");
        registrarAlumnoEnClase("Sofía Ruiz");
        registrarAlumnoEnClase("Luis Torres");

        System.out.println("--- REGISTRO DE CANDIDATOS ---");
        agregarCandidato("Ana Pérez");
        agregarCandidato("Carlos Gómez");

        System.out.println("\n--- PROCESO DE VOTACIÓN ---");
        emitirVoto("Ana Pérez", "Carlos Gómez"); // Voto válido
        emitirVoto("Carlos Gómez", "Carlos Gómez"); // Voto válido (puede votarse a sí mismo)
        emitirVoto("Ana Pérez", "Carlos Gómez"); // Error: ya votó
        emitirVoto("Pedro Picapiedra", "Ana Pérez"); // Error: no está en la lista de alumnos
        emitirVoto("Sofía Ruiz", "Luis Torres"); // Error: candidato no existe

        mostrarResultados();
    }
}