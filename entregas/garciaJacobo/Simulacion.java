package entregas.garciaJacobo;

public class Simulacion {

    public static void main(String[] args) {
        Console console = new Console();

        ListaAlumnosPresentes alumnos = new ListaAlumnosPresentes();

        console.writeln("Alumnos presentes al comenzar:");
        alumnos.insertarInicio("Ana");
        alumnos.insertarInicio("Luis");
        alumnos.insertarInicio("Carlos");
        alumnos.mostrar();

        console.writeln("");
        console.writeln("Llega Marta tarde.");
        console.writeln();
        alumnos.insertarInicio("Marta");

        console.writeln("Alumnos presentes después de la llegada:");
        console.writeln();
        alumnos.mostrar();

        console.writeln("");
        console.writeln("Luis se marcha.");
        console.writeln();

        alumnos.eliminar("Luis");

        console.writeln("Alumnos presentes después de la salida:");
        console.writeln();
        alumnos.mostrar();

        console.writeln("");
        console.writeln("Se abre el plazo de candidaturas.");

        ListaCandidatos candidatos = new ListaCandidatos();

        candidatos.insertarInicio("Marta");
        candidatos.insertarInicio("Carlos");
        candidatos.mostrar();

        console.writeln("");
        console.writeln("Carlos retira su candidatura.");
        candidatos.eliminar("Carlos");

        console.writeln("Ana se propone como candidata.");
        candidatos.insertarInicio("Ana");

        console.writeln("Candidatos después de los cambios:");
        candidatos.mostrar();

        console.writeln("");
        console.writeln("---Se cierra el plazo de candidaturas.---");

        String[] arrayCandidatos = candidatos.convertirAArray();

        console.writeln("Candidatos definitivos:");
        candidatos.mostrarArray(arrayCandidatos);

        String[] opciones = candidatos.obtenerOpcionesVotacion(arrayCandidatos);

        console.writeln("Opciones de votación:");
        candidatos.mostrarArray(opciones);

        Votacion votacion = new Votacion(opciones);

        votacion.recogerPapeleta("Ana");
        votacion.recogerPapeleta("Marta");
        votacion.recogerPapeleta("");
        votacion.recogerPapeleta("Carlos");

        console.writeln("");
        console.writeln("El profesor elige a Javier como secretario.");
        console.writeln("--- Se abre la votación. ---");
        while (votacion.quedanPapeletas()) {
            String voto = votacion.leerPapeleta();

            console.writeln("Javier lee la papeleta: " + voto);

            votacion.contarVoto(voto);
        }
        votacion.mostrarRecuento();
        votacion.mostrarResultado();
    }

}