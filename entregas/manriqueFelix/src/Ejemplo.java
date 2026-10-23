public class Ejemplo {

    static List presentes = new List();
    static List candidaturas = new List();
    static List votantes = new List();
    static Stack papeletas = new Stack();

    public static void main(String[] args) {

        System.out.println("=== LLEGADA DE ALUMNOS ===");
        llegar("Ana");
        llegar("Luis");
        llegar("Marta");
        llegar("Pedro");
        llegar("Sofia");
        llegar("Carlos");
        System.out.println("Alumno que llega tarde:");
        llegar("Diego");
        System.out.println("Alumno que se marcha:");
        marcharse("Sofia");
        System.out.println("Presentes: " + presentes);

        System.out.println("\n=== PLAZO DE CANDIDATURAS ABIERTO ===");
        proponer("Ana");
        proponer("Luis");
        proponer("Marta");
        System.out.println("Candidatura rechazada (Elena no está presente):");
        proponer("Elena");
        System.out.println("Candidatura retirada:");
        retirar("Luis");
        proponer("Diego");
        System.out.println("Candidaturas: " + candidaturas);

        System.out.println("\n=== CIERRE DEL PLAZO ===");
        String[] candidatos = candidaturas.toArray(); // manual copy: list -> array
        Pizarra pizarra = new Pizarra(candidatos);
        System.out.println("Candidatos fijados: " + candidatos.length
                + " (copiados a mano de la lista al array)");
        System.out.println("El profesor elige como secretario a Carlos.");

        System.out.println("\n=== VOTACIÓN ABIERTA ===");
        votar("Ana", "Marta");
        votar("Luis", "Ana");
        System.out.println("Alguien que no está presente intenta votar:");
        votar("Elena", "Diego");
        System.out.println("Alguien intenta votar dos veces:");
        votar("Ana", "Diego");
        votar("Marta", "Ana");
        votar("Carlos", "Marta");
        System.out.println("Voto nulo (Luis se retiró, ya no es candidato):");
        votar("Diego", "Luis");
        System.out.println("Papeleta en blanco, y el alumno se marcha después de votar:");
        votar("Pedro", "");
        marcharse("Pedro");
        System.out.println("Papeletas en el montón: " + papeletas.size());

        System.out.println("\n=== VOTACIÓN CERRADA: LECTURA DE PAPELETAS ===");
        boolean primera = true;
        while (!papeletas.isEmpty()) {
            String papeleta = papeletas.pop();
            String cantado = papeleta.isEmpty() ? "(sin nombre)" : papeleta;
            System.out.println("El secretario canta: " + cantado);

            if (primera) {
                int mal = pizarra.anotar("Ana");
                System.out.println("  El presidente se equivoca y anota un palito en "
                        + pizarra.nombreDe(mal));
                pizarra.borrarPalito(mal);
                System.out.println("  Lo corrige: borra el palito de " + pizarra.nombreDe(mal));
                primera = false;
            }

            int posicion = pizarra.anotar(papeleta);
            System.out.println("  Palito en: " + pizarra.nombreDe(posicion)
                    + " (comparaciones al buscar: " + pizarra.comparaciones + ")");
        }

        System.out.println("\n=== PIZARRA ===");
        pizarra.mostrar();

        System.out.println("\n=== PROCLAMACIÓN ===");
        System.out.println(pizarra.proclamar());
    }

    static void llegar(String alumno) {
        presentes.add(alumno);
        System.out.println("  " + alumno + " llega al aula.");
    }

    static void marcharse(String alumno) {
        presentes.remove(alumno);
        System.out.println("  " + alumno + " se marcha del aula (comparaciones: "
                + presentes.comparisons + ").");
    }

    static void proponer(String alumno) {
        boolean presente = presentes.contains(alumno);
        if (!presente) {
            System.out.println("  RECHAZADA: " + alumno + " no está presente (comparaciones: "
                    + presentes.comparisons + ").");
            return;
        }
        candidaturas.add(alumno);
        System.out.println("  " + alumno + " se propone como candidato (comprobación de presencia: "
                + presentes.comparisons + " comparaciones).");
    }

    static void retirar(String alumno) {
        candidaturas.remove(alumno);
        System.out.println("  " + alumno + " retira su candidatura (comparaciones: "
                + candidaturas.comparisons + ").");
    }

    static void votar(String alumno, String papeleta) {
        if (!presentes.contains(alumno)) {
            System.out.println("  RECHAZADO: " + alumno + " no está presente (comparaciones: "
                    + presentes.comparisons + ").");
            return;
        }
        if (votantes.contains(alumno)) {
            System.out.println("  RECHAZADO: " + alumno + " ya votó (comparaciones: "
                    + votantes.comparisons + ").");
            return;
        }
        votantes.add(alumno);
        papeletas.push(papeleta);
        System.out.println("  " + alumno + " entrega su papeleta.");
    }
} 
