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

        List candidaturas = new List();
        proponer(candidaturas, presentes, "Hector");
        proponer(candidaturas, presentes, "Jacobo");
        proponer(candidaturas, presentes, "Hugo");
        proponer(candidaturas, presentes, "Ana");
        
        candidaturas.delete("Javier");

        String[] candidatos = candidaturas.listAll();
        viewLine("CANDIDATOS", candidatos);

        Pizarra pizarra = new Pizarra(candidatos);
        Queue urna = new Queue();

        votar(urna, presentes, "Hector", "Jacobo");
        votar(urna, presentes, "Jacobo", "Hugo");
        votar(urna, presentes, "Hugo", "Ana");
        votar(urna, presentes, "Ana", "");
        votar(urna, presentes, "Javier", "Ana");
        votar(urna, presentes, "Rosa", "Batman");


        System.out.println("Las papeletas se leen en el orden en que se recogieron");
        while (!urna.estaVacia()) {
            String voto = urna.sacarPrimero();
            System.out.println("Secretario: " + (voto.isEmpty() ? "en blanco" : voto));
            pizarra.anotar(voto);
        }

        pizarra.mostrar();
        pizarra.proclamar();
    }

    static void proponer(List candidaturas, List presentes, String alumno) {
        if (presentes.contains(alumno) && !candidaturas.contains(alumno)) {
            candidaturas.insertEnd(alumno);
        } else {
            System.out.println("Candidatura rechazada: " + alumno);
        }
    }

    static void votar(Queue urna, List presentes, String alumno, String papeleta) {
        if (presentes.contains(alumno)) {
            urna.insertarAlFinal(papeleta);
        } else {
            System.out.println("Papeleta rechazada: " + alumno + " no consta como presente");
        }
    }

    static void viewLine(String titulo, String[] elementos) {
        System.out.println(titulo + "-".repeat(25 - titulo.length()));
        for (String elemento : elementos) {
            System.out.println(elemento);
        }
        System.out.println("-".repeat(25));
    }

    }

