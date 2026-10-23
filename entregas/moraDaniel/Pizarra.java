public class Pizarra {
    private String[] nombres;
    private int[] votos;
    private int candidatos;

    public Pizarra(String[] candidatos) {
        this.candidatos = candidatos.length;
        nombres = new String[this.candidatos + 2];
        for (int opcion = 0; opcion < this.candidatos; opcion++) {
            nombres[opcion] = candidatos[opcion];
        }
        nombres[this.candidatos] = "En blanco";
        nombres[this.candidatos + 1] = "Nulo";
        votos = new int[nombres.length];
    }

    public void anotar(String voto) {
        int opcion = buscar(voto);
        votos[opcion]++;
        System.out.println("  Presidente: palito para " + nombres[opcion]);
    }

    public void borrar(String voto) {
        int opcion = buscar(voto);
        if (votos[opcion] > 0) {
            votos[opcion]--;
            System.out.println("  Presidente: se ha equivocado y borra el palito de " + nombres[opcion]);
        }
    }

    private int buscar(String voto) {
        if (voto.isEmpty()) {
            return candidatos;
        }
        for (int opcion = 0; opcion < candidatos; opcion++) {
            if (nombres[opcion].equals(voto)) {
                return opcion;
            }
        }
        return candidatos + 1;
    }

    public void mostrar() {
        int total = 0;
        System.out.println("PIZARRA ----------------");
        for (int opcion = 0; opcion < nombres.length; opcion++) {
            System.out.println(nombres[opcion] + ": " + "|".repeat(votos[opcion]) + " (" + votos[opcion] + ")");
            total += votos[opcion];
        }
        System.out.println("Total papeletas: " + total);
        System.out.println("------------------------");
    }

    public void proclamar() {
        int maximo = 0;
        for (int opcion = 0; opcion < candidatos; opcion++) {
            if (votos[opcion] > maximo) {
                maximo = votos[opcion];
            }
        }
        if (maximo == 0) {
            System.out.println("Ningún candidato tiene votos");
            return;
        }
        int ganadores = 0;
        String nombresGanadores = "";
        for (int opcion = 0; opcion < candidatos; opcion++) {
            if (votos[opcion] == maximo) {
                ganadores++;
                nombresGanadores += " " + nombres[opcion];
            }
        }
        if (ganadores > 1) {
            System.out.println(
                    "EMPATE a " + maximo + " votos entre" + nombresGanadores + ": hay que repetir la votación");
        } else {
            System.out.println("Delegado:" + nombresGanadores + " con " + maximo + " votos");
        }
    }
}
