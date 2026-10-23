package GarrastazuAsier.Reto004;


import java.util.ArrayList;

public class Eleccion {

    private ListaAlumnos censo;
    private ArrayList<Candidato> candidatos;
    private ColaVotos urna;
    private Recuento recuento;
    private ArrayList<String> alumnosQueHanVotado;
    private boolean abierta;

    public Eleccion() {
        censo = new ListaAlumnos();
        candidatos = new ArrayList<Candidato>();
        urna = new ColaVotos();
        recuento = null;
        alumnosQueHanVotado = new ArrayList<String>();
        abierta = false;
    }

    public void agregarAlumno(String alumno) {
        censo.agregar(alumno);
    }

    public void mostrarCenso() {
        censo.mostrar();
    }

    public void agregarCandidato(String nombre, String partido) {
        if (abierta) {
            System.out.println("No puedes añadir candidatos durante la votación.");
            return;
        }

        candidatos.add(new Candidato(nombre, partido));
        recuento = new Recuento(candidatos);
        System.out.println("Candidato añadido correctamente.");
    }

    public void mostrarCandidatos() {
        for (int i = 0; i < candidatos.size(); i++) {
            System.out.println(
                candidatos.get(i).getNombre()
                + " - "
                + candidatos.get(i).getPartido()
            );
        }
    }

    public void abrirVotacion() {
        if (candidatos.isEmpty()) {
            System.out.println("Primero debes añadir candidatos.");
            return;
        }

        abierta = true;
        System.out.println("La votación está abierta.");
    }

    public void votar(String identificador, String nombreCandidato) {
        if (!abierta) {
            System.out.println("La votación está cerrada.");
            return;
        }

        if (censo.buscar(identificador) == null) {
            System.out.println("El alumno no está censado.");
            return;
        }

        for (int i = 0; i < alumnosQueHanVotado.size(); i++) {
            if (alumnosQueHanVotado.get(i).equals(identificador)) {
                System.out.println("Este alumno ya ha votado.");
                return;
            }
        }

        urna.insertar(new Voto(nombreCandidato));
        alumnosQueHanVotado.add(identificador);

        System.out.println("Voto registrado correctamente.");
    }

    public void cerrarVotacionYContar() {
        if (!abierta) {
            System.out.println("La votación ya está cerrada.");
            return;
        }

        abierta = false;

        while (!urna.estaVacia()) {
            Voto voto = urna.extraer();
            recuento.contarVoto(voto, candidatos);
        }

        System.out.println("Votación cerrada y votos contabilizados.");
    }

    public void mostrarResultados() {
        if (abierta) {
            System.out.println("Cierra la votación antes de ver los resultados.");
            return;
        }

        if (recuento == null) {
            System.out.println("Todavía no hay candidatos registrados.");
            return;
        }

        recuento.mostrarResultados(candidatos);
    }
}
