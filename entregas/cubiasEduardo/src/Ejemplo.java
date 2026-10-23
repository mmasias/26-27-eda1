public class Ejemplo {
    public static void main(String[] args) {
        ListaAlumnosPresentes presentes = new ListaAlumnosPresentes();
        ListaCandidatos candidaturas = new ListaCandidatos();
        ListaVotantes votantes = new ListaVotantes();
        UrnaCola urna = new UrnaCola();
        PilaAnotaciones anotaciones = new PilaAnotaciones();
        ListaGanadores ganadores = new ListaGanadores();

        int votosBlanco = 0;
        int votosNulos = 0;

        System.out.println("=== 1. REGISTRO DE ASISTENCIA EN EL AULA ===");
        presentes.registrarLlegada("Ana");
        presentes.registrarLlegada("Carlos");
        presentes.registrarLlegada("David");
        presentes.registrarLlegada("Elena");
        presentes.registrarLlegada("Fernando");
        presentes.registrarLlegada("Hugo");
        System.out.print("Alumnos inicialmente presentes: ");
        presentes.mostrar();

        System.out.println("\n[EVENTO: Alumno que llega tarde]");
        presentes.registrarLlegada("Guillermo");
        System.out.println("Ha llegado tarde: Guillermo.");
        System.out.print("Presentes actuales: ");
        presentes.mostrar();

        System.out.println("\n[EVENTO: Alumno que se marcha]");
        boolean salio = presentes.registrarSalida("Elena");
        if (salio) {
            System.out.println("Se ha marchado del aula: Elena.");
        }
        System.out.print("Presentes actuales: ");
        presentes.mostrar();

        System.out.println("\n=== 2. PRESENTACION Y GESTION DE CANDIDATURAS ===");
        candidaturas.agregarCandidato("Candidatura A");
        candidaturas.agregarCandidato("Candidatura B");
        candidaturas.agregarCandidato("Candidatura C");
        candidaturas.agregarCandidato("Candidatura D");
        System.out.println("Candidaturas registradas inicialmente:");
        candidaturas.mostrar();

        System.out.println("\n[EVENTO: Candidatura retirada]");
        boolean retirada = candidaturas.eliminarCandidato("Candidatura C");
        if (retirada) {
            System.out.println("Candidatura retirada voluntariamente: Candidatura C.");
        }

        System.out.println("\n[EVENTO: Candidatura rechazada]");
        boolean rechazada = candidaturas.eliminarCandidato("Candidatura D");
        if (rechazada) {
            System.out.println("Candidatura rechazada por no cumplir requisitos: Candidatura D.");
        }

        System.out.println("\nCandidaturas oficiales definitivas:");
        candidaturas.mostrar();

        System.out.println("\n=== 3. PROCESO DE VOTACION ===");

        votar(presentes, votantes, urna, "Ana", "Candidatura A");

        System.out.println("\n[EVENTO: Intento de votar de alguien que no esta presente]");
        votar(presentes, votantes, urna, "Elena", "Candidatura A");

        System.out.println("\n[EVENTO: Intento de votar dos veces]");
        votar(presentes, votantes, urna, "Ana", "Candidatura B");

        votar(presentes, votantes, urna, "Carlos", "Candidatura B");

        System.out.println("\n[EVENTO: Papeleta en blanco]");
        votar(presentes, votantes, urna, "David", "BLANCO");

        System.out.println("\n[EVENTO: Papeleta nula]");
        votar(presentes, votantes, urna, "Fernando", "VOTO_INVALIDO");

        votar(presentes, votantes, urna, "Guillermo", "Candidatura A");
        votar(presentes, votantes, urna, "Hugo", "Candidatura B");

        System.out.print("\nRegistro de votantes que ejercieron su voto: ");
        votantes.mostrar();

        System.out.println("\n=== 4. ESCRUTINIO Y RECUENTO ===");

        while (!urna.estaVacia()) {
            String papeleta = urna.desencolar();
            System.out.println("Extrayendo papeleta de la urna: " + papeleta);

            if (papeleta.equals("BLANCO")) {
                votosBlanco++;
                System.out.println(" -> Computado como voto en blanco.");
            } else if (!candidaturas.candidatoExiste(papeleta)) {
                votosNulos++;
                System.out.println(" -> Computado como voto nulo (opcion inexistente).");
            } else {
                candidaturas.votar(papeleta);
                anotaciones.apilar(papeleta);
                System.out.println(" -> Anotado 1 voto para " + papeleta + ".");

                if (papeleta.equals("Candidatura B") && candidaturas.buscarCandidato("Candidatura B").getVotos() == 1) {
                    System.out.println("\n[SIMULACION DE ERROR HUMANO DEL VOCAL]");
                    candidaturas.votar("Candidatura B");
                    anotaciones.apilar("Candidatura B");
                    System.out.println("Error: el vocal anoto por equivocacion otro voto de mas a Candidatura B.");
                    System.out.println("Estado antes de corregir:");
                    candidaturas.mostrar();

                    System.out.println("\n[EVENTO: Correccion de una anotacion erronea en el recuento]");
                    String erroneo = anotaciones.desapilar();
                    candidaturas.desvotar(erroneo);
                    System.out.println("Anotacion corregida desapilando y revirtiendo el voto de " + erroneo + ".");
                    System.out.println("Estado tras la correccion:");
                    candidaturas.mostrar();
                }
            }
        }

        System.out.println("\nRecuento finalizado.");
        System.out.println("Resultados finales del escrutinio:");
        candidaturas.mostrar();
        System.out.println("   - Votos en blanco: " + votosBlanco);
        System.out.println("   - Votos nulos: " + votosNulos);

        System.out.println("\n=== 5. PROCLAMACION ===");
        int maxVotos = -1;
        NodoCandidato actual = candidaturas.getCabeza();
        while (actual != null) {
            if (actual.candidato.getVotos() > maxVotos) {
                maxVotos = actual.candidato.getVotos();
            }
            actual = actual.siguiente;
        }

        if (maxVotos > 0) {
            actual = candidaturas.getCabeza();
            while (actual != null) {
                if (actual.candidato.getVotos() == maxVotos) {
                    ganadores.agregarGanador(actual.candidato);
                }
                actual = actual.siguiente;
            }
        }

        System.out.println("\n[EVENTO: Proclamacion detectando empate]");
        ganadores.mostrar();
    }

    private static void votar(ListaAlumnosPresentes presentes, ListaVotantes votantes, UrnaCola urna, String alumno, String opcion) {
        System.out.println("El alumno " + alumno + " acude a la mesa para votar por: " + opcion);
        if (!presentes.estaPresente(alumno)) {
            System.out.println(" RECHAZADO: " + alumno + " no esta presente en el aula.");
            return;
        }
        if (votantes.haVotado(alumno)) {
            System.out.println(" RECHAZADO: " + alumno + " ya ha ejercido su voto anteriormente.");
            return;
        }
        votantes.registrarVoto(alumno);
        urna.encolar(opcion);
        System.out.println(" ACEPTADO: Papeleta depositada en la urna.");
    }
}
