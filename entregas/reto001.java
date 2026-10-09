public class reto001 {

    private static final int MAXIMO = 30;

    private Persona[] personas;
    private int cantidad;

    public Fila() {
        this.personas = new Persona[MAXIMO];
        this.cantidad = 0;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public boolean estaVacia() {
        return this.cantidad == 0;
    }

    public boolean estaLlena() {
        return this.cantidad == MAXIMO;
    }

    public void incorporarAlFinal(Persona persona) {
        if (!this.estaLlena()) {
            this.personas[this.cantidad] = persona;
            this.cantidad++;
        }
    }

    public Persona atender() {
        if (this.estaVacia()) {
            return null;
        }

        Persona atendida = this.personas[0];

        this.desplazarDesde(0);

        return atendida;
    }

    private void desplazarDesde(int posicion) {
        int i = posicion;

        while (i < this.cantidad - 1) {
            this.personas[i] = this.personas[i + 1];
            i++;
        }

        this.personas[this.cantidad - 1] = null;
        this.cantidad--;
    }

    public void eliminarAburridos(int minutoActual) {
        int posicion = 0;

        while (posicion < this.cantidad) {

            if (this.personas[posicion]
                    .llevaMasDeOchoMinutos(minutoActual)) {

                this.desplazarDesde(posicion);
            } else {
                posicion++;
            }
        }
    }

    public void incorporarPreferente(Persona persona) {

        if (this.estaLlena()) {
            return;
        }

        int posicion = 0;

        while (posicion < this.cantidad
                && this.personas[posicion].tieneAtencionPreferente()) {
            posicion++;
        }

        this.desplazarHaciaDerecha(posicion);

        this.personas[posicion] = persona;
        this.cantidad++;
    }

    private void desplazarHaciaDerecha(int posicion) {
        int i = this.cantidad;

        while (i > posicion) {
            this.personas[i] = this.personas[i - 1];
            i--;
        }
    }

    public boolean colarseDetrasDe(Persona persona, Persona conocido) {

        if (this.estaLlena()) {
            return false;
        }

        int posicionConocido = this.posicionDe(conocido);

        if (posicionConocido == -1) {
            return false;
        }

        this.desplazarHaciaDerecha(posicionConocido + 1);

        this.personas[posicionConocido + 1] = persona;
        this.cantidad++;

        return true;
    }

    private int posicionDe(Persona persona) {

        int posicion = 0;

        while (posicion < this.cantidad) {

            if (this.personas[posicion] == persona) {
                return posicion;
            }

            posicion++;
        }

        return -1;
    }

    public Persona personaEn(int posicion) {
        return this.personas[posicion];
    }

    public Persona ultimoPreferente() {

        int posicion = this.cantidad - 1;

        while (posicion >= 0) {

            if (this.personas[posicion].tieneAtencionPreferente()) {
                return this.personas[posicion];
            }

            posicion--;
        }

        return null;
    }

    public Persona primeraPersona() {
        if (this.estaVacia()) {
            return null;
        }

        return this.personas[0];
    }

    public void incorporarDespuesDe(Persona persona, Persona anterior) {

        if (this.estaLlena()) {
            return;
        }

        int posicion = this.posicionDe(anterior);

        if (posicion == -1) {
            return;
        }

        this.desplazarHaciaDerecha(posicion + 1);

        this.personas[posicion + 1] = persona;
        this.cantidad++;
    }
}