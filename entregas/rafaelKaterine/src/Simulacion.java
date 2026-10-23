public class Simulacion {

    public static void main(String[] args) {
        System.out.println("====== SIMULACIÓN ELECTORAL CON ESTRUCTURA DE VISTAS ======\n");

        ListaEnlazada resultadosElectorales = new ListaEnlazada();
        resultadosElectorales.agregar("Candidato 1");
        resultadosElectorales.agregar("Candidato 2");
        resultadosElectorales.agregar("Candidato 3");
        resultadosElectorales.agregar("Voto Nulo");
        resultadosElectorales.agregar("Abstención");


        ListaEnlazada alumnosPresentes = new ListaEnlazada();
        ListaEnlazada alumnosQueVotaron = new ListaEnlazada();
        Cola<String> urna = new Cola<>();
        Pila<String> historialRecuento = new Pila<>();


        alumnosPresentes.agregar("Ana");
        alumnosPresentes.agregar("Bernardo");
        alumnosPresentes.agregar("Carla");
        alumnosPresentes.agregar("David");
        alumnosPresentes.agregar("Fernando"); 


        System.out.println("--- 1. GESTIÓN DE CANDIDATURAS Y BAZAS ---");
        resultadosElectorales.agregar("Candidato Retirado");
        resultadosElectorales.agregar("Candidato Rechazado");

        System.out.println("[EVENTO] Candidatura voluntariamente retirada: 'Candidato Retirado'");
        resultadosElectorales.eliminar("Candidato Retirado");

        System.out.println("[EVENTO] Candidatura rechazada por la mesa: 'Candidato Rechazado'");
        resultadosElectorales.eliminar("Candidato Rechazado");
        System.out.println();

        System.out.println("--- 2. CONTROL DE PRESENCIA Y ASISTENCIA ---");
        alumnosPresentes.agregar("Elena");
        System.out.println("[EVENTO] Alumno llega tarde y se incorpora a la sala: Elena");

        alumnosPresentes.eliminar("Fernando");
        System.out.println("[EVENTO] Alumno se marcha del aula antes de votar: Fernando\n");


        System.out.println("--- 3. JORNADA DE VOTACIÓN EN URNA ---");

        emitirVoto("Ana", "Candidato 1", alumnosPresentes, alumnosQueVotaron, urna);

        System.out.print("[INTENTO FALLIDO] ");
        emitirVoto("Fernando", "Candidato 1", alumnosPresentes, alumnosQueVotaron, urna);

        emitirVoto("Bernardo", "Candidato 2", alumnosPresentes, alumnosQueVotaron, urna);

        System.out.print("[INTENTO FALLIDO] ");
        emitirVoto("Ana", "Candidato 2", alumnosPresentes, alumnosQueVotaron, urna);

        emitirVoto("Carla", "Voto en Blanco", alumnosPresentes, alumnosQueVotaron, urna);

        emitirVoto("David", "Voto Nulo", alumnosPresentes, alumnosQueVotaron, urna);

        System.out.println("Total de sobres introducidos en la Urna (Cola): " + urna.getTamano() + "\n");

        System.out.println("--- 4. ESC RUTINIO Y CORRECCIÓN DE ANOTACIÓN ERRÓNEA ---");

        int conteoPapeleta = 0;
        while (!urna.estaVacia()) {
            String papeleta = urna.desencolar(); // FIFO
            conteoPapeleta++;

            String categoriaAnotar = papeleta.equals("Voto en Blanco") ? "Voto Nulo" : papeleta;

            historialRecuento.apilar(categoriaAnotar);
            resultadosElectorales.sumarVoto(categoriaAnotar);
            System.out.println("Papeleta " + conteoPapeleta + " leída -> Anotado voto a: " + categoriaAnotar);

            if (conteoPapeleta == 2) {
                System.out.println("\n[ERROR EN MESA] Se detecta error al anotar la última papeleta.");
                String anotacionErrada = historialRecuento.desapilar(); // LIFO
                resultadosElectorales.restarVoto(anotacionErrada);
                System.out.println("[CORRECCIÓN] Se revierte voto desapilado de: " + anotacionErrada);

                String anotacionCorrecta = "Candidato 1";
                historialRecuento.apilar(anotacionCorrecta);
                resultadosElectorales.sumarVoto(anotacionCorrecta);
                System.out.println("[CORRECCIÓN] Anotación corregida y apilada para: " + anotacionCorrecta + "\n");
            }
        }
        System.out.println();


        System.out.println("--- 5. REPORTE Y REGISTRO DE ABSTENCIÓN ---");
        Nodo tempPresentes = alumnosPresentes.getCabeza();
        while (tempPresentes != null) {
            if (!alumnosQueVotaron.contiene(tempPresentes.nombre)) {
                resultadosElectorales.sumarVoto("Abstención");
                System.out.println("[ABSTENCIÓN DETECTADA] El alumno presente '" + tempPresentes.nombre + "' no entregó papeleta.");
            }
            tempPresentes = tempPresentes.siguiente;
        }
        System.out.println();

        System.out.println("--- 6. CONSULTAS INDIVIDUALES (mostrarCandidato) ---");
        resultadosElectorales.mostrarCandidato("Candidato 1");
        resultadosElectorales.mostrarCandidato("Candidato 3");
        resultadosElectorales.mostrarCandidato("Abstención");
        System.out.println();

        resultadosElectorales.mostrarResultados();

        System.out.println("--- 7. PROCLAMACIÓN Y DETECCIÓN DE EMPATE ---");
        evaluarEmpateYGanador(resultadosElectorales);
    }

    private static void emitirVoto(String alumno, String voto, ListaEnlazada presentes, ListaEnlazada votantes, Cola<String> urna) {
        if (!presentes.contiene(alumno)) {
            System.out.println("Voto denegado a " + alumno + ": No está presente en la sala.");
            return;
        }

        if (votantes.contiene(alumno)) {
            System.out.println("Voto denegado a " + alumno + ": Ya ha registrado un voto previo (intento duplicado).");
            return;
        }

        votantes.agregar(alumno);
        urna.encolar(voto);
        System.out.println("Voto depositado con éxito en la urna por " + alumno + " (" + voto + ")");
    }

    private static void evaluarEmpateYGanador(ListaEnlazada resultados) {
        Nodo temp = resultados.getCabeza();
        int maxVotos = -1;
        String ganador = "";
        boolean empate = false;

        while (temp != null) {
            if (temp.nombre.startsWith("Candidato")) {
                if (temp.votos > maxVotos) {
                    maxVotos = temp.votos;
                    ganador = temp.nombre;
                    empate = false;
                } else if (temp.votos == maxVotos && maxVotos > 0) {
                    empate = true;
                }
            }
            temp = temp.siguiente;
        }

        if (empate) {
            System.out.println("[PROCLAMACIÓN] ¡EMPATE DETECTADO! Existe una igualdad en el primer lugar con " + maxVotos + " voto(s). Se requiere segunda vuelta.");
        } else if (!ganador.equals("")) {
            System.out.println("[PROCLAMACIÓN] Candidatura ganadora: " + ganador + " con " + maxVotos + " voto(s).");
        }
    }
}