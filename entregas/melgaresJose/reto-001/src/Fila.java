class Fila {

    private int longitud;
    private Persona primeroEnFila;

    public Fila(int largoInicial) {
        this.longitud = 0;
        this.primeroEnFila = null;
        for (int i = 0; i < largoInicial; i++) {
            aumentarFila(new Persona());
        }
    }

    public Fila() {
        this(0);
    }

    public void aumentarFila(Persona siguienteEnFila) {
        this.longitud++;
        if (primeroEnFila == null) {
            primeroEnFila = siguienteEnFila;
        } else {
            Persona actual = primeroEnFila;
            while (actual.getDetrasDe() != null) {
                actual = actual.getDetrasDe();
            }
            actual.colocarDetrasDe(siguienteEnFila);
        }
    }

    public boolean atenderPersona() {
        if (primeroEnFila != null) {
            primeroEnFila = primeroEnFila.getDetrasDe();
            longitud--;
            return true;
        }
        return false;
    }

    public void mostrarFila(int numeroCaja) {
        System.out.print("☺ [" + numeroCaja + "]: ");
        Persona actual = primeroEnFila;
        while (actual != null) {
            System.out.print(" ☻ ");
            actual = actual.quienEstaDetras();
        }
        System.out.println();
    }
}