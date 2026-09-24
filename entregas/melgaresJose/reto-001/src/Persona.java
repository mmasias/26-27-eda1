class Persona {

    private Persona detrasDe;

    public Persona() {
        detrasDe = null;
    }

    public Persona(Persona enFrente) {
        detrasDe = enFrente;
    }

    public void colocarDetrasDe(Persona enFrente) {
        detrasDe = enFrente;
    }

    public Persona quienEstaDetras() {
        return detrasDe;
    }
}