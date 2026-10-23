public class Pizarra {
    private String[] opciones;
    private int[] votos;

    public Pizarra(String[] candidatosFijados) {
        // Se reservan candidatos + 2 posiciones fijas: BLANCO y NULO
        opciones = new String[candidatosFijados.length + 2];
        votos = new int[opciones.length];

        for (int i = 0; i < candidatosFijados.length; i++) {
            opciones[i] = candidatosFijados[i];
        }
        opciones[candidatosFijados.length] = "BLANCO";
        opciones[candidatosFijados.length + 1] = "NULO";
    }

    public int buscarIndice(String textoPapeleta) {
        if (textoPapeleta == null || textoPapeleta.trim().isEmpty()) {
            return opciones.length - 2; // Índice de BLANCO
        }
        for (int i = 0; i < opciones.length - 2; i++) {
            if (opciones[i].equalsIgnoreCase(textoPapeleta.trim())) {
                return i; // Candidato válido
            }
        }
        return opciones.length - 1; // Índice de NULO
    }

    public void anotarPalito(int indice) {
        votos[indice]++;
    }

    public void borrarPalito(int indice) {
        if (votos[indice] > 0) {
            votos[indice]--;
        }
    }

    public String getNombreOpcion(int indice) {
        return opciones[indice];
    }

    public void mostrarRecuento() {
        System.out.println("\n--- ESTADO DE LA PIZARRA ---");
        for (int i = 0; i < opciones.length; i++) {
            System.out.print(opciones[i] + ": " + votos[i] + " palitos (");
            for (int p = 0; p < votos[i]; p++) System.out.print("|");
            System.out.println(")");
        }
    }

    public void proclamarResultado() {
        int maxVotos = -1;
        int numCandidatosReales = opciones.length - 2;

        for (int i = 0; i < numCandidatosReales; i++) {
            if (votos[i] > maxVotos) {
                maxVotos = votos[i];
            }
        }

        int countEmpate = 0;
        String ganador = "";
        for (int i = 0; i < numCandidatosReales; i++) {
            if (votos[i] == maxVotos && maxVotos > 0) {
                countEmpate++;
                ganador = opciones[i];
            }
        }

        System.out.println("\n==================================");
        System.out.println("      PROCLAMACIÓN OFICIAL");
        System.out.println("==================================");
        if (countEmpate > 1) {
            System.out.println("RESULTADO: ¡EMPATE detectado con " + maxVotos + " votos! Se requiere segunda vuelta.");
        } else if (countEmpate == 1) {
            System.out.println("RESULTADO: Proclamado/a delegado/a electo/a: " + ganador + " con " + maxVotos + " votos.");
        } else {
            System.out.println("RESULTADO: Ningún candidato ha obtenido votos válidos.");
        }
    }
}