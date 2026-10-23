
public class PruebaTodasLasClases {

    public static void main(String[] args) {

        Console console = new Console();

        console.writeln("--- PRUEBA DE LIST ---");

        List lista = new List();

        lista.insertEnd("Alba");
        lista.insertEnd("Bruno");
        lista.insertEnd("Carlos");

        lista.listAll();

        console.writeln("Contiene Alba:");
        console.writeln(lista.contains("Alba"));

        console.writeln("Tamano: " + lista.size());

        lista.delete("Bruno");
        lista.listAll();

        console.writeln("----------------");

        console.writeln("--- PRUEBA DE QUEUE ---");

        Queue cola = new Queue();

        console.writeln("Cola vacia:");
        console.writeln(cola.isEmpty());

        cola.insertEnd(new Papeleta("Alba", "Alba"));
        cola.insertEnd(new Papeleta("Bruno", "Bruno"));

        console.writeln("Cola vacia despues de insertar:");
        console.writeln(cola.isEmpty());

        console.writeln("----------------");

        console.writeln("--- PRUEBA DE ALUMNOS PRESENTES ---");

        ListaAlumnosPresentes alumnos = new ListaAlumnosPresentes();

        alumnos.insertar("Alba");
        alumnos.insertar("Bruno");
        alumnos.insertar("Carlos");

        alumnos.mostrar();

        alumnos.eliminar("Bruno");

        console.writeln("Alumnos despues de eliminar a Bruno:");
        alumnos.mostrar();

        console.writeln("----------------");

        console.writeln("--- PRUEBA DE CANDIDATOS ---");

        ListaCandidatos candidatos = new ListaCandidatos(alumnos);

        candidatos.insertar("Alba");
        candidatos.insertar("Carlos");

        candidatos.mostrar();

        console.writeln("Alba es candidata:");
        console.writeln(candidatos.contiene("Alba"));

        console.writeln("Numero de candidatos: " + candidatos.tamano());

        console.writeln("Array de candidatos:");

        String[] arrayCandidatos = candidatos.convertirAArray();

        for (int i = 0; i < arrayCandidatos.length; i++) {
            console.writeln(arrayCandidatos[i]);
        }

        console.writeln("----------------");

        console.writeln("--- PRUEBA DE VOTACION ---");

        Votacion votacion = new Votacion(alumnos, candidatos);

        votacion.cerrarCandidaturas();

        votacion.registrarVoto("Alba", "Alba");
        votacion.registrarVoto("Carlos", "Carlos");

        votacion.contarPapeletas();
        votacion.mostrarRecuento();
        votacion.mostrarResultado();

        console.writeln("--- FIN DE LAS PRUEBAS ---");
    }
}
