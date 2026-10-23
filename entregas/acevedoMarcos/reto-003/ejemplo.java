
public class Ejemplo {

    public static void main(String[] args) {

        List presentes = new List();
        presentes.insertEnd("Hector");
        presentes.insertEnd("Jacobo");
        presentes.insertEnd("Hugo");
        presentes.insertEnd("Ana");
        presentes.insertEnd("Javier");
        presentes.insertEnd("Rosa");

        presentes.delete("Luisito");
        presentes.insertEnd("Carlos");

        viewLine("PRESENTES", presentes.listAll());

        List candidatos = new List();

        proponer(candidatos, presentes, "Hector");
        proponer(candidatos, presentes, "Jacobo");
        proponer(candidatos, presentes, "Hugo");
        proponer(candidatos, presentes, "Ana");

        candidatos.delete("Javier");

        String[] listaCandidatos = candidatos.listAll();
        viewLine("CANDIDATOS", listaCandidatos);

        Pizarra resultados = new Pizarra(listaCandidatos);
        Queue urna = new Queue();

        votar(urna, presentes, "Hector", "Jacobo");
        votar(urna, presentes, "Jacobo", "Hugo");
        votar(urna, presentes, "Hugo", "Ana");
        votar(urna, presentes, "Ana", "");
        votar(urna, presentes, "Javier", "Ana");
        votar(urna, presentes, "Rosa", "Batman");

        System.out.println("Lectura de votos según el orden de recogida:");

        while (!urna.estaVacia()) {
            String papeleta = urna.sacarPrimero();

            if (papeleta.isEmpty()) {
                System.out.println("Secretario: voto en blanco");
            } else {
                System.out.println("Secretario: " + papeleta);
            }

            resultados.anotar(papeleta);
        }

        resultados.mostrar();
        resultados.proclamar();
    }

    static void proponer(List candidatos, List presentes, String alumno) {

        boolean estaPresente = presentes.contains(alumno);
        boolean yaPropuesto = candidatos.contains(alumno);

        if (estaPresente && !yaPropuesto) {
            candidatos.insertEnd(alumno);
        } else {
            System.out.println("No se acepta la candidatura de: " + alumno);
        }
    }

    static void votar(Queue urna, List presentes, String alumno, String voto) {

        if (presentes.contains(alumno)) {
            urna.insertarAlFinal(voto);
        } else {
            System.out.println(
                "Voto rechazado: " + alumno + " no está en la lista de presentes"
            );
        }
    }

    static void viewLine(String titulo, String[] elementos) {

        String separador = "-".repeat(25);

        System.out.println(titulo + "-".repeat(25 - titulo.length()));

        for (int i = 0; i < elementos.length; i++) {
            System.out.println(elementos[i]);
        }

        System.out.println(separador);
    }
}
