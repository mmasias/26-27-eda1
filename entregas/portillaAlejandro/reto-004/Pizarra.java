
public class Pizarra {

    private static final String EN_BLANCO = "En blanco";
    private static final String NULO = "Nulo";

    private String[] nombres;
    private int[] votos;
    private int candidatos;

    public Pizarra(String[] listaCandidatos) {

        candidatos = listaCandidatos.length;

        nombres = new String[candidatos + 2];
        votos = new int[candidatos + 2];

        for (int i = 0; i < candidatos; i++) {
            nombres[i] = listaCandidatos[i];
        }

        nombres[candidatos] = EN_BLANCO;
        nombres[candidatos + 1] = NULO;
    }

    public void anotar(String voto) {
        int indice = posicion(voto);
        votos[indice] = votos[indice] + 1;
    }

    public void corregir(String voto) {
        int indice = posicion(voto);

        if (votos[indice] != 0) {
            votos[indice] = votos[indice] - 1;
        }
    }

    private int posicion(String voto) {

        if (voto.isEmpty()) {
            return candidatos;
        }

        int i = 0;

        while (i < candidatos) {
            if (nombres[i].equals(voto)) {
                return i;
            }
            i++;
        }

        return candidatos + 1;
    }

    public void mostrar() {

        System.out.println("PIZARRA" + "-".repeat(18));

        int i = 0;

        while (i < nombres.length) {
            System.out.println(
                String.format("%-10s", nombres[i])
                + " "
                + "|".repeat(votos[i])
                + " (" + votos[i] + ")"
            );

            i++;
        }

        System.out.println("-".repeat(25));
    }

    public void proclamar() {

        int maximo = 0;
        int ganador = -1;
        boolean empate = false;

        for (int i = 0; i < candidatos; i++) {

            if (votos[i] > maximo) {
                maximo = votos[i];
                ganador = i;
                empate = false;

            } else if (votos[i] == maximo && maximo > 0) {
                empate = true;
            }
        }

        if (ganador < 0) {
            System.out.println("Ningún candidato ha recibido votos");

        } else if (empate) {
            System.out.println(
                "Empate a " + maximo
                + " votos: hay que repetir la votación"
            );

        } else {
            System.out.println(
                "Delegado electo: " + nombres[ganador]
                + " con " + maximo + " votos"
            );
        }
    }
}