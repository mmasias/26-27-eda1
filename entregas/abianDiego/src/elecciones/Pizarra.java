package elecciones;

class Pizarra {
    private static final String EN_BLANCO = "En blanco";
    private static final String NULO = "Nulo";
    private static final int OPCIONES_FIJAS = 2;
    private static final int ANCHO_COLUMNA = 10;
    private static final String PALITO = "|";

    private final String[] opciones;
    private final int[] palitos;
    private final int numeroDeCandidatos;
    private final int posicionEnBlanco;
    private final int posicionNulo;

    Pizarra(String[] candidatos) {
        numeroDeCandidatos = candidatos.length;
        posicionEnBlanco = numeroDeCandidatos;
        posicionNulo = numeroDeCandidatos + 1;
        opciones = new String[numeroDeCandidatos + OPCIONES_FIJAS];
        for (int i = 0; i < numeroDeCandidatos; i = i + 1) {
            opciones[i] = candidatos[i];
        }
        opciones[posicionEnBlanco] = EN_BLANCO;
        opciones[posicionNulo] = NULO;
        palitos = new int[opciones.length];
    }

    void anotar(String voto) {
        int posicion = posicionDe(voto);
        palitos[posicion] = palitos[posicion] + 1;
    }

    void borrarPalito(String voto) {
        int posicion = posicionDe(voto);
        assert palitos[posicion] > 0;
        palitos[posicion] = palitos[posicion] - 1;
    }

    String opcionDe(String voto) {
        return opciones[posicionDe(voto)];
    }

    private int posicionDe(String voto) {
        int posicion = posicionEnBlanco;
        if (!voto.isEmpty()) {
            posicion = 0;
            while (posicion < numeroDeCandidatos && !opciones[posicion].equals(voto)) {
                posicion = posicion + 1;
            }
            if (posicion == numeroDeCandidatos) {
                posicion = posicionNulo;
            }
        }
        return posicion;
    }

    void mostrar() {
        for (int i = 0; i < opciones.length; i = i + 1) {
            String nombre = String.format("%-" + ANCHO_COLUMNA + "s", opciones[i]);
            System.out.println("  " + nombre + PALITO.repeat(palitos[i]) + " (" + palitos[i] + ")");
        }
    }

    void proclamar() {
        int maximo = 0;
        for (int i = 0; i < numeroDeCandidatos; i = i + 1) {
            if (palitos[i] > maximo) {
                maximo = palitos[i];
            }
        }
        String masVotados = "";
        int ganadores = 0;
        for (int i = 0; i < numeroDeCandidatos; i = i + 1) {
            if (palitos[i] == maximo) {
                masVotados = masVotados + " " + opciones[i];
                ganadores = ganadores + 1;
            }
        }
        if (maximo == 0) {
            System.out.println("Ningun candidato tiene votos: no hay delegado.");
        } else if (ganadores > 1) {
            System.out.println("EMPATE a " + maximo + " votos entre:" + masVotados + ". Hay que repetir la votacion.");
        } else {
            System.out.println("Delegado electo:" + masVotados + ", con " + maximo + " votos.");
        }
    }
}
