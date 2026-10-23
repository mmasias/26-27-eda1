public class Ejemplo {
    public static void main(String[] args) {
        System.out.println("=== INICIO DE LA JORNADA ELECTORAL ===");

        
        List presentes = new List();
        presentes.add("Ana");
        presentes.add("Hector");
        presentes.add("Carlos");
        presentes.add("Diana");
        presentes.add("Esteban");

        System.out.println("[Aula] Presentes iniciales: Ana, Hector, Carlos, Diana, Esteban.");

        System.out.println("[Incidencia] Llega tarde Fernando. Se añade a la lista de presentes.");
        presentes.add("Fernando");

        System.out.println("[Incidencia] Esteban se marcha del aula antes de votar. Se retira de la lista.");
        presentes.remove("Esteban");

        System.out.println("\n--- APERTURA DE CANDIDATURAS ---");
        List candidaturas = new List();

        candidaturas.add("Ana");
        System.out.println("[Candidatura] Ana se propone.");

        String candidatoIlegal = "Esteban";
        if (presentes.contains(candidatoIlegal)) {
            candidaturas.add(candidatoIlegal);
        } else {
            System.out.println("[Rechazo] " + candidatoIlegal + " intentó postularse pero NO está en el aula. Rechazado.");
        }

        candidaturas.add("Hector");
        System.out.println("[Candidatura] Hector se propone.");

        candidaturas.add("Carlos");
        System.out.println("[Candidatura] Carlos se propone.");

        System.out.println("[Incidencia] Carlos retira formalmente su candidatura.");
        candidaturas.remove("Carlos");

        System.out.println("\n--- CIERRE DE CANDIDATURAS ---");
        String[] candidatosFijados = candidaturas.toArray();
        System.out.print("[Fijación] Candidatos definitivos: ");
        for (String c : candidatosFijados) System.out.print(c + " ");
        System.out.println();

        Pizarra pizarra = new Pizarra(candidatosFijados);

        System.out.println("\n--- COMIENZO DE LA VOTACIÓN ---");
        Stack montonPapeletas = new Stack();
        List hanVotado = new List(); 

        emitirVoto("Ana", "Ana", presentes, hanVotado, montonPapeletas);
        
        emitirVoto("Hector", "Hector", presentes, hanVotado, montonPapeletas);
        
        emitirVoto("Diana", "", presentes, hanVotado, montonPapeletas);
        
        emitirVoto("Fernando", "Profesor", presentes, hanVotado, montonPapeletas);
        
        emitirVoto("Esteban", "Ana", presentes, hanVotado, montonPapeletas);
        
        emitirVoto("Ana", "Hector", presentes, hanVotado, montonPapeletas);
        
        System.out.println("\n--- CIERRE DE LA URNA ---");
        int totalPresentes = presentes.size();
        int totalVotaron = hanVotado.size();
        int abstenciones = totalPresentes - totalVotaron;
        System.out.println("Alumnos presentes en el aula: " + totalPresentes);
        System.out.println("Alumnos que emitieron papeleta: " + totalVotaron);
        System.out.println("Abstención calculada: " + abstenciones + " alumno(s) presente(s) no votaron.");

        System.out.println("\n--- LECTURA DE PAPELETAS Y ESCRUTINIO ---");
        while (!montonPapeletas.isEmpty()) {
            String papeleta = montonPapeletas.pop();
            int idx = pizarra.buscarIndice(papeleta);
            System.out.println("Secretario canta papeleta: \"" + papeleta + "\" -> Anotada como " + pizarra.getNombreOpcion(idx));
            pizarra.anotarPalito(idx);
        }

        pizarra.mostrarRecuento();

        System.out.println("\n[Incidencia en Recuento] El presidente advierte que anotó por error un palito de más en NULO.");
        int idxNulo = pizarra.buscarIndice("TextoNuloCualquiera");
        pizarra.borrarPalito(idxNulo);
        System.out.println("Se corrige borrando el palito en la pizarra.");

        pizarra.mostrarRecuento();

        pizarra.proclamarResultado();
    }

    private static void emitirVoto(String alumno, String contenidoPapeleta, List presentes, List hanVotado, Stack monton) {
        if (!presentes.contains(alumno)) {
            System.out.println("[Votación Denegada] " + alumno + " no está presente en el aula.");
            return;
        }
        if (hanVotado.contains(alumno)) {
            System.out.println("[Fraude Detectado] " + alumno + " ya ha votado. Intento de voto duplicado anulado.");
            return;
        }

        hanVotado.add(alumno);
        monton.push(contenidoPapeleta);
        System.out.println("[Voto Emitido] " + alumno + " deposita su papeleta en el montón.");
    }
}