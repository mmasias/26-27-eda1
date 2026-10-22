public class Pizarra {
    private String[] nombres;
    private int[] votos;
    private int numCandidatos;
    private int comparaciones;

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

    // COSTE O(c): localizar la columna por nombre exige recorrer el array de nombres
    private int buscar(String nombre, int limite) {
        for (int i = 0; i < limite; i++) {
            comparaciones++;
            if (nombres[i].equals(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public int posicionDeCandidato(String nombre) {
        return buscar(nombre, numCandidatos);
    }

    public int posicionDeColumna(String nombre) {
        return buscar(nombre, nombres.length);
    }

    public int columnaBlanco() {
        return numCandidatos;
    }

    public int columnaNulo() {
        return numCandidatos + 1;
    }

    public void anadirPalito(int posicion) {
        votos[posicion]++;
    }

    public void borrarPalito(int posicion) {
        if (votos[posicion] > 0) {
            votos[posicion]--;
        }
    }

    public int votos(int posicion) {
        return votos[posicion];
    }

    public String nombre(int posicion) {
        return nombres[posicion];
    }

    public int numCandidatos() {
        return numCandidatos;
    }

    public int numColumnas() {
        return nombres.length;
    }

    public int comparaciones() {
        return comparaciones;
    }
}