class Ejemplo {

    public static void main(String[] args) {
        ListaVotantes alumnos = new ListaVotantes();
        PizarraConteo pizarra = new PizarraConteo();
        PilaUrna urna = new PilaUrna();

        System.out.println("=== INICIO DE JORNADA ELECTORAL ===");

        alumnos.agregar("Hugo");
        alumnos.agregar("Juan");
        alumnos.agregar("Luis");
        alumnos.agregar("Brayan");
        alumnos.agregar("Mickey");

        pizarra.registrarCandidatura("Candidato A");
        pizarra.registrarCandidatura("Candidato B");
        pizarra.registrarCandidatura("Candidato C (Rechazado)");
        pizarra.registrarCandidatura("Candidato D (Se retira)");

        System.out.println("> La junta electoral rechaza a 'Candidato C (Rechazado)'.");
        pizarra.retirarCandidatura("Candidato C (Rechazado)");
        System.out.println("> 'Candidato D (Se retira)' abandona por motivos personales.");
        pizarra.retirarCandidatura("Candidato D (Se retira)");

        System.out.println("> Goofy LLEGA TARDE y es admitido en la lista.");
        alumnos.agregar("Goofy");
        System.out.println("> Brayan se indispone y SE MARCHA antes de votar.");
        alumnos.eliminar("Brayan");

        System.out.println("\n=== FASE DE VOTACION ===");

        emitirVoto("Hugo", "Candidato A", alumnos, urna);
        emitirVoto("Juan", "Candidato B", alumnos, urna);
        emitirVoto("Luis", "Candidato A", alumnos, urna);
        emitirVoto("Goofy", "Candidato B", alumnos, urna);
        emitirVoto("Brayan", "Candidato A", alumnos, urna);
        emitirVoto("Mickey", "", alumnos, urna);
        emitirVoto("Juan", "Candidato A", alumnos, urna);
        
        System.out.println("\n=== FASE DE RECUENTO ===");
        int totalPapeletas = urna.obtenerCantidad();
        int votosNulos = 0;
        int votosBlancos = 0;

        for (; !urna.estaVacia(); ) {
            String papeleta = urna.desapilar();
            
            if (papeleta.equals("")) {
                votosBlancos++;
                System.out.println("Leida: [Papeleta en Blanco]");
            } else if (pizarra.esCandidatoValido(papeleta)) {
                pizarra.sumarVoto(papeleta);
                System.out.println("Leida: Voto para " + papeleta);
            } else {
                votosNulos++;
                System.out.println("Leida: [Voto Nulo] - " + papeleta);
            }
        }

        System.out.println("> El presidente nota que le anotó un voto de más a Candidato A. Corrigiendo...");
        pizarra.corregirAnotacion("Candidato A", 1); 
        pizarra.proclamarGanador();

        System.out.println("\n=== REPORTE DE ABSTENCION ===");
        int presentes = alumnos.contarPresentes();
        int abstenciones = presentes - totalPapeletas;
        System.out.println("Total de presentes al cierre: " + presentes);
        System.out.println("Papeletas extraidas: " + totalPapeletas);
        System.out.println("ABSTENCIONES: " + abstenciones);
    }
    
    private static void emitirVoto(String alumno, String eleccion, ListaVotantes alumnos, PilaUrna urna) {
        if (!alumnos.estaPresente(alumno)) {
            System.out.println("[ALERTA] " + alumno + " intentó votar pero NO ESTA PRESENTE.");
        } else if (!alumnos.intentarVotar(alumno)) {
            System.out.println("[ALERTA] " + alumno + " intentó VOTAR DOS VECES. Rechazado.");
        } else {
            urna.apilar(eleccion);
            System.out.println(alumno + " introdujo su papeleta en la urna.");
        }
    }
}