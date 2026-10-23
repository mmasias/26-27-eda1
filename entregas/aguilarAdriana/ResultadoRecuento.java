package entregas.aguilarAdriana;

public class ResultadoRecuento {
    private static final String EN_BLANCO = "En blanco";
    private static final String NULO = "Nulo";
    private String[] nombres;
    private int[] votos;
    private int candidatos;

    public ResultadoRecuento(Candidato[] candidatos) {
        this.candidatos = candidatos.length;
        this.nombres = new String[this.candidatos + 2];
        for (int i = 0; i < this.candidatos; i++) {
            this.nombres[i] = candidatos[i].obtenerNombre();
        }
        this.nombres[this.candidatos] = EN_BLANCO;
        this.nombres[this.candidatos + 1] = NULO;
        this.votos = new int[this.nombres.length];
    }

    public void sumarVotoDe(String nombre) {
        this.votos[this.posicion(nombre)]++;
    }

    public boolean corregirVotoDe(String nombre) {
        int posicion = this.posicion(nombre);
        if (this.votos[posicion] == 0) {
            return false;
        }
        this.votos[posicion]--;
        return true;
    }

    public int votosDe(String nombre) {
        return this.votos[this.posicion(nombre)];
    }

    public String[] nombres() {
        return this.nombres;
    }

    public int[] votos() {
        return this.votos;
    }

    public boolean hayEmpate() {
        int maximo = 0;
        int cantidadMaximos = 0;
        for (int i = 0; i < this.candidatos; i++) {
            if (this.votos[i] > maximo) {
                maximo = this.votos[i];
                cantidadMaximos = 1;
            } else if (this.votos[i] == maximo && maximo > 0) {
                cantidadMaximos++;
            }
        }
        return cantidadMaximos > 1;
    }

    public String proclamar() {
        int maximo = 0;
        int ganador = -1;
        for (int i = 0; i < this.candidatos; i++) {
            if (this.votos[i] > maximo) {
                maximo = this.votos[i];
                ganador = i;
            }
        }
        if (ganador == -1) {
            return "Ningún candidato ha recibido votos";
        }
        if (this.hayEmpate()) {
            return "Empate a " + maximo
                    + " votos: hay que repetir la votación";
        }
        return "Delegado electo: " + this.nombres[ganador]
                + " con " + maximo + " votos";
    }

    private int posicion(String nombre) {
        if (nombre == null || nombre.isEmpty()
                || nombre.equals(EN_BLANCO)) {
            return this.candidatos;
        }
        for (int i = 0; i < this.candidatos; i++) {
            if (this.nombres[i].equals(nombre)) {
                return i;
            }
        }
        return this.candidatos + 1;
    }
}
