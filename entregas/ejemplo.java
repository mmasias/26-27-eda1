
import java.util.Set;

public class Ejemplo {

    public static void main(String[] args) {
        List presentes = new List();

        presentes.insertEnd("Juanito");
        presentes.insertEnd("Pepito");
        presentes.insertEnd("Anita");
        presentes.insertEnd("Luisito");
        presentes.insertEnd("Marta");
        presentes.insertEnd("Rosa");

        viewLine("PRESENTES INICIALES", presentes.listAll());
         System.out.println("\nLuisito se marcha.");
        presentes.delete("Luisito");
        System.out.println("Carlos llega tarde y se incorpora.");
        presentes.insertEnd("Carlos");
        viewLine("PRESENTES DEFINITIVOS", presentes.listAll());
        List candidaturas = new List();

        proponer(candidaturas, presentes, "Anita");
        proponer(candidaturas, presentes, "Pepito");
        proponer(candidaturas, presentes, "Luisito"); // Rechazada
        proponer(candidaturas, presentes, "Rosa");

        
        System.out.println("\nRosa retira su candidatura.");
        candidaturas.delete("Rosa");

        String[] candidatos = candidaturas.listAll();
        viewLine("CANDIDATOS DEFINITIVOS", candidatos);
        Pizarra pizarra = new Pizarra(candidatos);
        Queue urna = new Queue();
        Set<String> votantes = new HashSet<>();

        System.out.println("\n--- COMIENZA LA VOTACION ---");

        votar(urna, presentes, votantes, "Juanito", "Anita");
        votar(urna, presentes, votantes, "Pepito", "Pepito");
        votar(urna, presentes, votantes, "Anita", "Anita");
        votar(urna, presentes, votantes, "Marta", ""); 
        votar(urna, presentes, votantes, "Rosa", "Pepito");
        votar(urna, presentes, votantes, "Carlos", "Batman");
        votar(urna, presentes, votantes, "Luisito", "Anita");
        votar(urna, presentes, votantes, "Pepito", "Pepito");
        System.out.println("\n--- RECUENTO DE LA URNA ---");

        while (!urna.isEmpty()) {
            Papeleta papeleta = urna.dequeue();

            System.out.println(
                "Secretario: papeleta de " + papeleta.alumno
                + " -> "
                + (papeleta.voto.isEmpty()
                    ? "en blanco"
                    : papeleta.voto)
            );
            if (papeleta.alumno.equals("Juanito")) {
                System.out.println(
                    "ERROR: se anota por equivocación el voto de Juanito a Pepito."
                );
                pizarra.anotar("Pepito");

                System.out.println(
                    "Se comprueba la papeleta y se corrige el error."
                );
                pizarra.corregirAnotacion("Pepito", "Anita");
            } else {
                pizarra.anotar(papeleta.voto);
            }
        }
        System.out.println("\n--- RESULTADOS DEFINITIVOS ---");
        pizarra.mostrar();
        pizarra.proclamar();
    }
    static void proponer(
            List candidaturas, List presentes, String alumno) {

        if (presentes.contains(alumno)
                && !candidaturas.contains(alumno)) {

            candidaturas.insertEnd(alumno);
            System.out.println("Candidatura aceptada: " + alumno);

        } else {
            System.out.println("Candidatura rechazada: " + alumno);
        }
            }
    static void votar(
            Queue urna,
            List presentes,
            Set<String> votantes,
            String alumno,
            String papeleta) {

        if (!presentes.contains(alumno)) {
            System.out.println(
                "Papeleta rechazada: " + alumno
                + " no consta como presente."
            );
            return;
        }

        if (votantes.contains(alumno)) {
            System.out.println(
                "Papeleta rechazada: " + alumno
                + " ya ha votado."
            );
            return;
        }

        urna.enqueue(new Papeleta(alumno, papeleta));
        votantes.add(alumno);

        System.out.println("Voto aceptado: " + alumno);
    }
    static void viewLine(String titulo, String[] elementos) {
        System.out.println("\n" + titulo + " ----------------");

        int i = 0;
        while (i < elementos.length) {
            System.out.println(elementos[i]);
            i++;
        }

        System.out.println("--------------------------------");
    }
}

class List {

    private String[] datos = new String[20];
    private int cantidad = 0;

    public void insertEnd(String dato) {
        if (cantidad == datos.length) {
            String[] nuevos = new String[datos.length * 2];

            for (int i = 0; i < cantidad; i++) {
                nuevos[i] = datos[i];
            }

            datos = nuevos;
        }

        datos[cantidad] = dato;
        cantidad++;
    }

    public boolean contains(String dato) {
        for (int i = 0; i < cantidad; i++) {
            if (datos[i].equals(dato)) {
                return true;
            }
        }

        return false;
    }

    public void delete(String dato) {
        for (int i = 0; i < cantidad; i++) {
            if (datos[i].equals(dato)) {
                for (int j = i; j < cantidad - 1; j++) {
                    datos[j] = datos[j + 1];
                }

                cantidad--;
                datos[cantidad] = null;
                return;
            }
        }
    }

    public String[] listAll() {
        String[] resultado = new String[cantidad];

        for (int i = 0; i < cantidad; i++) {
            resultado[i] = datos[i];
        }

        return resultado;
    }
}
class Papeleta {

    String alumno;
    String voto;

    public Papeleta(String alumno, String voto) {
        this.alumno = alumno;
        this.voto = voto;
    }
}

class Queue {

    private Papeleta[] datos = new Papeleta[20];
    private int inicio = 0;
    private int cantidad = 0;

    public void enqueue(Papeleta papeleta) {
        if (cantidad == datos.length) {
            Papeleta[] nuevos = new Papeleta[datos.length * 2];

            for (int i = 0; i < cantidad; i++) {
                nuevos[i] = datos[(inicio + i) % datos.length];
            }

            datos = nuevos;
            inicio = 0;
        }

        int fin = (inicio + cantidad) % datos.length;
        datos[fin] = papeleta;
        cantidad++;
    }

    public Papeleta dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("La urna está vacía.");
        }

        Papeleta resultado = datos[inicio];
        datos[inicio] = null;
        inicio = (inicio + 1) % datos.length;
        cantidad--;

        return resultado;
    }

    public boolean isEmpty() {
        return cantidad == 0;
    }
}
class Pizarra {

    private String[] candidatos;
    private int[] votos;
    private int blancos = 0;
    private int nulos = 0;

    public Pizarra(String[] candidatos) {
        this.candidatos = candidatos;
        this.votos = new int[candidatos.length];
    }

    public void anotar(String voto) {

        if (voto == null || voto.isEmpty()) {
            blancos++;
            return;
        }

        int posicion = buscar(voto);

        if (posicion == -1) {
            nulos++;
            System.out.println(
                "Voto nulo: " + voto + " no es un candidato."
            );
        } else {
            votos[posicion]++;
        }
    }
    public void corregirAnotacion(String erroneo, String correcto) {

        int posicionErronea = buscar(erroneo);
        int posicionCorrecta = buscar(correcto);

        if (posicionErronea == -1 || posicionCorrecta == -1
                || votos[posicionErronea] <= 0) {
            System.out.println("No se puede corregir la anotación.");
            return;
        }

        votos[posicionErronea]--;
        votos[posicionCorrecta]++;

        System.out.println(
            "Corrección realizada: se resta un voto a " + erroneo
            + " y se añade a " + correcto + "."
        );
    }

    private int buscar(String candidato) {
        for (int i = 0; i < candidatos.length; i++) {
            if (candidatos[i].equals(candidato)) {
                return i;
            }
        }

        return -1;
    }

    public void mostrar() {

        for (int i = 0; i < candidatos.length; i++) {
            System.out.println(
                candidatos[i] + ": " + votos[i] + " votos"
            );
        }

        System.out.println("Votos en blanco: " + blancos);
        System.out.println("Votos nulos: " + nulos);
    }

    public void proclamar() {

        int maximo = -1;
        int ganadores = 0;
        String ganador = "";

        for (int i = 0; i < votos.length; i++) {
            if (votos[i] > maximo) {
                maximo = votos[i];
                ganador = candidatos[i];
                ganadores = 1;
            } else if (votos[i] == maximo) {
                ganadores++;
            }
        }

        if (ganadores > 1) {
            System.out.println(
                "Resultado: EMPATE entre los candidatos con "
                + maximo + " votos."
            );
        } else {
            System.out.println(
                "Candidato proclamado: " + ganador
                + " con " + maximo + " votos."
            );
        }
    }
}
