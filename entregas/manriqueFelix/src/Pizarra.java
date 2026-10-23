public class Pizarra {
    private final String[] nombres;
    private final int[] votos;
    private final int numCandidatos;
    public int comparaciones;

    public Pizarra(String[] candidatos) {
        numCandidatos = candidatos.length;
        nombres = new String[numCandidatos + 2];
        votos = new int[numCandidatos + 2];
        for (int i = 0; i < numCandidatos; i++) {
            nombres[i] = candidatos[i];
        }
        nombres[numCandidatos] = "En blanco";
        nombres[numCandidatos + 1] = "Nulo";
    }

    public int anotar(String cantado) {
        comparaciones = 0;
        int posicion;
        if (cantado.isEmpty()) {
            posicion = numCandidatos;
        } else {
            posicion = buscarCandidato(cantado);
            if (posicion == -1) {
                posicion = numCandidatos + 1;
            }
        }
        votos[posicion]++;
        return posicion;
    }

    private int buscarCandidato(String nombre) {
        for (int i = 0; i < numCandidatos; i++) {
            comparaciones++;
            if (nombres[i].equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public void borrarPalito(int posicion) {
        if (votos[posicion] > 0) {
            votos[posicion]--;
        }
    }

    public String nombreDe(int posicion) {
        return nombres[posicion];
    }

    public void mostrar() {
        for (int i = 0; i < nombres.length; i++) {
            String palitos = "";
            for (int j = 0; j < votos[i]; j++) {
                palitos += "|";
            }
            System.out.println("  " + nombres[i] + ": " + palitos + " (" + votos[i] + ")");
        }
    }

    public String proclamar() {
        int max = 0;
        for (int i = 0; i < numCandidatos; i++) {
            if (votos[i] > max) {
                max = votos[i];
            }
        }
        if (max == 0) {
            return "Nadie recibió votos: no se proclama a nadie.";
        }
        String ganadores = "";
        int cuantos = 0;
        for (int i = 0; i < numCandidatos; i++) {
            if (votos[i] == max) {
                if (cuantos > 0) {
                    ganadores += ", ";
                }
                ganadores += nombres[i];
                cuantos++;
            }
        }
        if (cuantos > 1) {
            return "EMPATE entre " + ganadores + " con " + max
                    + " votos: no se proclama a nadie.";
        }
        return "Delegado proclamado: " + ganadores + " con " + max + " votos.";
    }
}