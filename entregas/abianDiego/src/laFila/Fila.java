package laFila;

class Fila {
    private final int CAPACIDAD_INICIAL = 10;
    private final int PRIMERA_POSICION = 0;

    private Persona[] personas;
    private int longitud;

    public Fila() {
        personas = new Persona[CAPACIDAD_INICIAL];
        longitud = 0;
    }

    public int longitud() {
        return longitud;
    }

    public boolean estaVacia() {
        return longitud == 0;
    }

    public Persona personaEn(int posicion) {
        assert esPosicionOcupada(posicion);
        return personas[posicion];
    }

    public void incorporarAlFinal(Persona persona) {
        incorporarEn(longitud, persona);
    }

    public void incorporarEn(int posicion, Persona persona) {
        assert posicion >= PRIMERA_POSICION && posicion <= longitud;
        if (estaLlena()) {
            duplicarCapacidad();
        }
        abrirHuecoEn(posicion);
        personas[posicion] = persona;
        longitud = longitud + 1;
    }

    public void retirarAlPrimero() {
        retirarDe(PRIMERA_POSICION);
    }

    public void retirarDe(int posicion) {
        assert esPosicionOcupada(posicion);
        cerrarHuecoEn(posicion);
        longitud = longitud - 1;
        personas[longitud] = null;
    }

    private boolean esPosicionOcupada(int posicion) {
        return posicion >= PRIMERA_POSICION && posicion < longitud;
    }

    private boolean estaLlena() {
        return longitud == personas.length;
    }

    private void duplicarCapacidad() {
        Persona[] personasAmpliadas = new Persona[personas.length * 2];
        for (int posicion = 0; posicion < longitud; posicion = posicion + 1) {
            personasAmpliadas[posicion] = personas[posicion];
        }
        personas = personasAmpliadas;
    }

    private void abrirHuecoEn(int posicionDelHueco) {
        for (int posicion = longitud; posicion > posicionDelHueco; posicion = posicion - 1) {
            personas[posicion] = personas[posicion - 1];
        }
    }

    private void cerrarHuecoEn(int posicionDelHueco) {
        for (int posicion = posicionDelHueco; posicion < longitud - 1; posicion = posicion + 1) {
            personas[posicion] = personas[posicion + 1];
        }
    }
}
