class Refactoring {
    public static void main(String[] args) {
        System.out.println("=== INICIO DEL PROCESO ELECTORAL ===");

        ListaAlumnos censo = new ListaAlumnos();
        censo.agregar("A01", "Ana");
        censo.agregar("A02", "Carlos");
        censo.agregar("A03", "Elena");
        censo.agregar("A04", "David");
        System.out.println("[Censo inicial cargado con 4 alumnos]");

        ListaCandidaturas candidaturas = new ListaCandidaturas();
        candidaturas.agregar("Lista 1", "ACTIVA");
        candidaturas.agregar("Lista 2", "ACTIVA");
        candidaturas.agregar("Lista 3", "ACTIVA");
        candidaturas.agregar("Lista 4", "ACTIVA");
        System.out.println("[Candidaturas registradas]");

        UrnaCola urna = new UrnaCola();
        PilaIncidencias pilaIncidencias = new PilaIncidencias();

        NodoAlumno alumnoTarde = new NodoAlumno("A05", "Bea");
        NodoAlumno actual = censo.cabeza;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        actual.siguiente = alumnoTarde;
        System.out.println("EVENTO: Llega tarde el alumno Bea (A05) y se añade al censo dinámico.");

        NodoAlumno alumnoMarcha = censo.buscar("A04");
        if (alumnoMarcha != null) {
            alumnoMarcha.presente = false;
        }
        System.out.println("EVENTO: El alumno David (A04) se marcha antes de votar (presente = false).");

        candidaturas.cambiarEstado("Lista 3", "RETIRADA");
        candidaturas.cambiarEstado("Lista 4", "RECHAZADA");
        System.out.println("EVENTO: Candidatura 'Lista 3' retirada y 'Lista 4' rechazada.");

        urna.depositar("Lista 1");
        urna.depositar("BLANCA");
        urna.depositar("NULA");
        urna.depositar("Lista 1");
        System.out.println("EVENTO: Se depositan votos en la urna (incluyendo una papeleta BLANCA y una NULA).");

        NodoAlumno alumnoAusente = censo.buscar("A04");
        if (alumnoAusente != null && alumnoAusente.presente && !alumnoAusente.haVotado) {
            urna.depositar("Lista 2");
            alumnoAusente.haVotado = true;
        } else {
            System.out.println("EVENTO RECHAZADO: Intento de voto de David (A04), pero consta como ausente/marchado.");
        }

        NodoAlumno alumnoDuplicado = censo.buscar("A01");
        if (alumnoDuplicado != null && alumnoDuplicado.presente && !alumnoDuplicado.haVotado) {
            urna.depositar("Lista 2");
            alumnoDuplicado.haVotado = true;
        } else if (alumnoDuplicado != null && alumnoDuplicado.haVotado) {
            pilaIncidencias.apilar("Intento de doble voto bloqueado para alumno: " + alumnoDuplicado.id);
            System.out.println("EVENTO BLOQUEADO: Intento de doble voto detectado y bloqueado para Ana (A01). Registrado en Pila de incidencias.");
        }

        pilaIncidencias.apilar("Correccion en recuento: Voto nulo contabilizado erroneamente como valido, corregido.");
        System.out.println("EVENTO: Se corrige una anotación errónea en el recuento y se apila la incidencia.");

        int votosLista1 = 0;
        int votosLista2 = 0;
        int votosBlanco = 0;
        int votosNulo = 0;

        String v1 = urna.extraer();
        while (v1 != null) {
            if (v1.equals("Lista 1")) {
                votosLista1++;
            } else if (v1.equals("Lista 2")) {
                votosLista2++;
            } else if (v1.equals("BLANCA")) {
                votosBlanco++;
            } else if (v1.equals("NULA")) {
                votosNulo++;
            }
            v1 = urna.extraer();
        }

        System.out.println("\n--- RESULTADOS DEL ESCRUTINIO ---");
        System.out.println("Votos Lista 1: " + votosLista1);
        System.out.println("Votos Lista 2: " + votosLista2);
        System.out.println("Votos en Blanco: " + votosBlanco);
        System.out.println("Votos Nulos: " + votosNulo);

        if (votosLista1 == votosLista2) {
            System.out.println("PROCLAMACIÓN: EMPATE DETECTADO ENTRE LISTA 1 Y LISTA 2");
        } else if (votosLista1 > votosLista2) {
            System.out.println("PROCLAMACIÓN: GANADOR LISTA 1");
        } else {
            System.out.println("PROCLAMACIÓN: GANADOR LISTA 2");
        }
        
        System.out.println("=== FIN DEL PROCESO ELECTORAL ===");
    }
}