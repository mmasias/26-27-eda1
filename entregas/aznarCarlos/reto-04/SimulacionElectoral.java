public class SimulacionElectoral {

    private static final int CAPACIDAD_MAXIMA = 50;

    private String[] censoPresentes = new String[CAPACIDAD_MAXIMA];
    private int totalCenso = 0;

    private String[] candidaturasOficiales = new String[CAPACIDAD_MAXIMA];
    private int totalCandidaturas = 0;

    private String[] registroVotaron = new String[CAPACIDAD_MAXIMA];
    private int totalVotaron = 0;

    private String[] urnaPapeletas = new String[CAPACIDAD_MAXIMA];
    private int frenteUrna = 0;
    private int finUrna = 0;

    private String[] opcionesEscrutinio = new String[CAPACIDAD_MAXIMA];
    private int[] conteoEscrutinio = new int[CAPACIDAD_MAXIMA];
    private int totalOpcionesEscrutinio = 0;

    public static void main(String[] args) {
        SimulacionElectoral sim = new SimulacionElectoral();
        sim.ejecutarSimulacion();
    }

    public void ejecutarSimulacion() {
        System.out.println("=== 1. GESTIÓN DE CANDIDATURAS ===");
        registrarCandidatura("Candidatura A", true);
        registrarCandidatura("Candidatura B", true);
        registrarCandidatura("Candidatura Irregular", false);

        registrarCandidatura("Candidatura C", true);
        retirarCandidatura("Candidatura C");

        System.out.println("\n=== 2. CONTROL DE ASISTENCIA Y CENSO ===");
        agregarAlCenso("Juan");
        agregarAlCenso("Maria");
        imprimirCenso();

        System.out.println("-> Alumno llega tarde: Carlos");
        agregarAlCenso("Carlos");

        System.out.println("-> Alumno se marcha: Maria");
        removerDelCenso("Maria");
        imprimirCenso();

        agregarAlCenso("David");
        agregarAlCenso("Sofia");

        System.out.println("\n=== 3. PROCESO DE VOTACIÓN ===");
        emitirVoto("Maria", "Candidatura A");

        emitirVoto("Juan", "Candidatura A");

        emitirVoto("Juan", "Candidatura B");

        agregarAlCenso("Luis");
        emitirVoto("Luis", "BLANCO");

        emitirVoto("Carlos", "CandidatoInexistente");

        emitirVoto("David", "Candidatura B");
        emitirVoto("Sofia", "Candidatura A");

        System.out.println("\n=== 4. ESCRUTINIO Y RECUENTO DE LA URNA ===");
        procesarEscrutinio();

        System.out.println("\n=== 5. CORRECCIÓN DE ANOTACIÓN ERRÓNEA ===");
        System.out.println("Error detectado: Se anotó manualmente 1 voto extra a Candidatura A.");
        modificarEscrutinioManualmente("Candidatura A", 1);
        imprimirEscrutinio();

        System.out.println("-> Aplicando corrección: restando el voto erróneo...");
        modificarEscrutinioManualmente("Candidatura A", -1);
        imprimirEscrutinio();

        System.out.println("\n=== 6. PROCLAMACIÓN DE RESULTADOS Y DETECCIÓN DE EMPATE ===");
        proclamarResultados();
    }

    public void agregarAlCenso(String nombre) {
        if (totalCenso < CAPACIDAD_MAXIMA) {
            censoPresentes[totalCenso] = nombre;
            totalCenso++;
        }
    }

    public void removerDelCenso(String nombre) {
        for (int i = 0; i < totalCenso; i++) {
            if (censoPresentes[i].equals(nombre)) {
                for (int j = i; j < totalCenso - 1; j++) {
                    censoPresentes[j] = censoPresentes[j + 1];
                }
                censoPresentes[totalCenso - 1] = null;
                totalCenso--;
                break;
            }
        }
    }

    public boolean estaEnCenso(String nombre) {
        for (int i = 0; i < totalCenso; i++) {
            if (censoPresentes[i].equals(nombre)) return true;
        }
        return false;
    }

    public void registrarCandidatura(String nombre, boolean cumpleRequisitos) {
        if (!cumpleRequisitos) {
            System.out.println("[CANDIDATURA RECHAZADA]: " + nombre + " no cumple con los requisitos.");
        } else {
            if (totalCandidaturas < CAPACIDAD_MAXIMA) {
                candidaturasOficiales[totalCandidaturas] = nombre;
                totalCandidaturas++;
                System.out.println("[CANDIDATURA ADMITIDA]: " + nombre);
            }
        }
    }

    public void retirarCandidatura(String nombre) {
        for (int i = 0; i < totalCandidaturas; i++) {
            if (candidaturasOficiales[i].equals(nombre)) {
                for (int j = i; j < totalCandidaturas - 1; j++) {
                    candidaturasOficiales[j] = candidaturasOficiales[j + 1];
                }
                candidaturasOficiales[totalCandidaturas - 1] = null;
                totalCandidaturas--;
                System.out.println("[CANDIDATURA RETIRADA]: " + nombre + " ha retirado su candidatura.");
                break;
            }
        }
    }

    public boolean esCandidaturaOficial(String nombre) {
        for (int i = 0; i < totalCandidaturas; i++) {
            if (candidaturasOficiales[i].equals(nombre)) return true;
        }
        return false;
    }

    public boolean yaVoto(String nombre) {
        for (int i = 0; i < totalVotaron; i++) {
            if (registroVotaron[i].equals(nombre)) return true;
        }
        return false;
    }

    public void emitirVoto(String votante, String opcionVoto) {
        if (!estaEnCenso(votante)) {
            System.out.println("[VOTO RECHAZADO]: " + votante + " no está presente en la mesa electoral.");
            return;
        }

        if (yaVoto(votante)) {
            System.out.println("[VOTO RECHAZADO]: " + votante + " ya ha ejercido su voto anteriormente.");
            return;
        }

        registroVotaron[totalVotaron] = votante;
        totalVotaron++;

        String papeletaFinal;
        if (opcionVoto.equalsIgnoreCase("BLANCO")) {
            papeletaFinal = "BLANCO";
        } else if (esCandidaturaOficial(opcionVoto)) {
            papeletaFinal = opcionVoto;
        } else {
            papeletaFinal = "NULO";
        }

        if (finUrna < CAPACIDAD_MAXIMA) {
            urnaPapeletas[finUrna] = papeletaFinal;
            finUrna++;
            System.out.println("[VOTO EMITIDO]: " + votante + " ha depositado su papeleta en la urna.");
        }
    }

    public void procesarEscrutinio() {
        System.out.println("Iniciando lectura de la urna en orden FIFO mediante indices...");
        while (frenteUrna < finUrna) {
            String papeleta = urnaPapeletas[frenteUrna];
            frenteUrna++;
            modificarEscrutinioManualmente(papeleta, 1);
        }
        imprimirEscrutinio();
    }

    public void modificarEscrutinioManualmente(String opcion, int delta) {
        int indice = -1;
        for (int i = 0; i < totalOpcionesEscrutinio; i++) {
            if (opcionesEscrutinio[i].equals(opcion)) {
                indice = i;
                break;
            }
        }

        if (indice != -1) {
            conteoEscrutinio[indice] += delta;
        } else if (delta > 0 && totalOpcionesEscrutinio < CAPACIDAD_MAXIMA) {
            opcionesEscrutinio[totalOpcionesEscrutinio] = opcion;
            conteoEscrutinio[totalOpcionesEscrutinio] = delta;
            totalOpcionesEscrutinio++;
        }
    }

    public void proclamarResultados() {
        int maxVotos = -1;
        String[] ganadores = new String[CAPACIDAD_MAXIMA];
        int totalGanadores = 0;

        for (int i = 0; i < totalOpcionesEscrutinio; i++) {
            String opcion = opcionesEscrutinio[i];
            int votos = conteoEscrutinio[i];

            if (esCandidaturaOficial(opcion)) {
                if (votos > maxVotos) {
                    maxVotos = votos;
                    totalGanadores = 0;
                    ganadores[totalGanadores] = opcion;
                    totalGanadores++;
                } else if (votos == maxVotos && maxVotos > 0) {
                    ganadores[totalGanadores] = opcion;
                    totalGanadores++;
                }
            }
        }

        System.out.println("Votos acumulados por candidaturas oficiales: " + maxVotos + " voto(s)");
        if (totalGanadores == 0) {
            System.out.println("No se han registrado votos para ninguna candidatura válida.");
        } else if (totalGanadores > 1) {
            System.out.print("[RESULTADO - EMPATE DETECTADO]: Se ha producido un empate entre las candidaturas [");
            for (int i = 0; i < totalGanadores; i++) {
                System.out.print(ganadores[i] + (i < totalGanadores - 1 ? ", " : ""));
            }
            System.out.println("] con " + maxVotos + " votos cada una.");
        } else {
            System.out.println("[RESULTADO - GANADOR]: La candidatura proclamada es " + ganadores[0]);
        }
    }

    private void imprimirCenso() {
        System.out.print("Alumnos presentes en censo: [");
        for (int i = 0; i < totalCenso; i++) {
            System.out.print(censoPresentes[i] + (i < totalCenso - 1 ? ", " : ""));
        }
        System.out.println("]");
    }

    private void imprimirEscrutinio() {
        System.out.print("Escrutinio actual: {");
        for (int i = 0; i < totalOpcionesEscrutinio; i++) {
            System.out.print(opcionesEscrutinio[i] + "=" + conteoEscrutinio[i] + (i < totalOpcionesEscrutinio - 1 ? ", " : ""));
        }
        System.out.println("}");
    }
}