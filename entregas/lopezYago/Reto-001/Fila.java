package entregas.lopezYago.Reto-001;

public class Fila {

    private static final int capacidadTecnica = 1000;

    private Persona[] personas;
    private int tamanio;

    public Fila() {
        this.personas = new Persona[capacidadTecnica];
        this.tamanio = 0;
    }

    public boolean estaVacia() {
        return tamanio == 0;
    }

    public int getTamanio() {
        return tamanio;
    }

    public Persona obtener(int posicion) {
        return personas[posicion];
    }

    public void agregarAlFinal(Persona persona) {
        personas[tamanio] = persona;
        tamanio++;
    }

    public void insertarEn(int posicion, Persona persona) {
        for (int i = tamanio; i > posicion; i--) {
            personas[i] = personas[i - 1];
        }
        personas[posicion] = persona;
        tamanio++;
    }

    public void quitarDe(int posicion) {
        for (int i = posicion; i < tamanio - 1; i++) {
            personas[i] = personas[i + 1];
        }
        personas[tamanio - 1] = null;
        tamanio--;
    }

    public Persona atenderPrimero() {
        Persona atendido = personas[0];
        quitarDe(0);
        return atendido;
    }

    public int posicionTrasUltimoPreferente() {
        for (int i = tamanio - 1; i >= 0; i--) {
            if (personas[i].esPreferente()) {
                return i + 1;
            }
        }
        return 0;
    }
}