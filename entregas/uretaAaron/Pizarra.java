package entregas.uretaAaron;

public class Pizarra {

    private static final String EN_BLANCO = "En blanco";
    private static final String NULO = "Nulo";

    private String[] nombres;
    private int[] votos;
    private int candidatos;

    public Pizarra(String[] candidatos) {
        this.candidatos = candidatos.length;
        this.nombres = new String[this.candidatos + 2];

        int i = 0;
        while (i < this.candidatos) {
            this.nombres[i] = candidatos[i];
            i++;
        }
        this.nombres[this.candidatos] = EN_BLANCO;
        this.nombres[this.candidatos + 1] = NULO;
        this.votos = new int[this.nombres.length];
    }

    public void anotar(String voto) {
        this.votos[this.posicion(voto)]++;
    }

    public void corregir(String voto) {
        int idx = this.posicion(voto);
        if (this.votos[idx] > 0) {
            this.votos[idx]--;
            System.out.println("CORRECCION EN PIZARRA: Se borra un palito a " + this.nombres[idx]);
        }
    }

    private int posicion(String voto) {
        if (voto == null) {
            return this.candidatos + 1;
        }
        if (voto.equals("")) {
            return this.candidatos;
        }
        int i = 0;
        while (i < this.candidatos) {
            if (this.nombres[i].equals(voto)) {
                return i;
            }
            i++;
        }
        return this.candidatos + 1;
    }

    public void mostrar() {
        System.out.println("\n--- ESTADO DE LA PIZARRA ---");
        int i = 0;
        while (i < this.nombres.length) {
            System.out.print(this.nombres[i] + "\t: ");
            int palitos = 0;
            while (palitos < this.votos[i]) {
                System.out.print("|");
                palitos++;
            }
            System.out.println(" (" + this.votos[i] + ")");
            i++;
        }
        System.out.println("----------------------------");
    }

    public void proclamar() {
        int maximo = 0;
        int ganador = -1;
        boolean empate = false;

        int i = 0;
        while (i < this.candidatos) {
            if (this.votos[i] > maximo) {
                maximo = this.votos[i];
                ganador = i;
                empate = false;
            } else if (this.votos[i] == maximo && maximo > 0) {
                empate = true;
            }
            i++;
        }

        System.out.println("\n--- RESULTADO ELECTORAL ---");
        if (ganador == -1) {
            System.out.println("Ningun candidato ha recibido votos.");
        } else if (empate) {
            System.out.println("EMPATE DETECTADO a " + maximo + " votos. Se debe repetir la votacion.");
        } else {
            System.out.println("DELEGADO ELECTO: " + this.nombres[ganador] + " con " + maximo + " votos.");
        }
    }
}