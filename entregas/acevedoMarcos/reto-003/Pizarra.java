
public class Pizarra {

    private static final String BLANCO = "En blanco";
    private static final String VOTO_NULO = "Nulo";

    private String[] nombres;
    private int[] votos;
    private int totalCandidatos;

    public Pizarra(String[] candidatos) {
        totalCandidatos = candidatos.length;

        nombres = new String[totalCandidatos + 2];

        for (int i = 0; i < totalCandidatos; i++) {
            nombres[i] = candidatos[i];
        }

        nombres[totalCandidatos] = BLANCO;
        nombres[totalCandidatos + 1] = VOTO_NULO;

        votos = new int[nombres.length];
    }

    public void anotar(String voto) {
        int indice = buscarPosicion(voto);
        votos[indice]++;
    }

    public void corregir(String voto) {
        int indice = buscarPosicion(voto);

        if (votos[indice] > 0) {
            votos[indice]--;
        }
    }

    private int buscarPosicion(String voto) {
        if (voto.isEmpty()) {
            return totalCandidatos;
        }

        for (int i = 0; i < totalCandidatos; i++) {
            if (nombres[i].equals(voto)) {
                return i;
            }
        }

        return totalCandidatos + 1;
    }

    public void mostrar() {
        System.out.println("PIZARRA" + "-".repeat(18));

        for (int i = 0; i < nombres.length; i++) {
            String linea = String.format("%-10s", nombres[i]);
            linea += " " + "|".repeat(votos[i]);
            linea += " (" + votos[i] + ")";

            System.out.println(linea);
        }

        System.out.println("-".repeat(25));
    }

    public void proclamar() {
        int maxVotos = 0;
        int indiceGanador = -1;
        boolean hayEmpate = false;

        for (int i = 0; i < totalCandidatos; i++) {
            if (votos[i] > maxVotos) {
                maxVotos = votos[i];
                indiceGanador = i;
                hayEmpate = false;
            } else if (votos[i] == maxVotos && maxVotos > 0) {
                hayEmpate = true;
            }
        }

        if (indiceGanador == -1) {
            System.out.println("Ningún candidato ha recibido votos");
        } else if (hayEmpate) {
            System.out.println("Empate a " + maxVotos
                    + " votos: hay que repetir la votación");
        } else {
            System.out.println("Delegado electo: "
                    + nombres[indiceGanador] + " con "
                    + maxVotos + " votos");
        }
    }
}
