import java.util.LinkedList;

public class Fila {

    private LinkedList<Persona> personas;
    private int maximo;

    public Fila() {
        personas = new LinkedList<>();
        maximo = 30;
    }

    public int tamanio() {
        return personas.size();
    }

    public boolean estaLlena() {
        return personas.size() >= maximo;
    }

    public boolean estaVacia() {
        return personas.isEmpty();
    }

    // Llegada normal: entra al final
    public boolean entrar(Persona persona) {

        if (estaLlena()) {
            return false;
        }

        personas.addLast(persona);
        return true;
    }

    // Sale la primera persona de la fila
    public Persona atender() {

        if (estaVacia()) {
            return null;
        }

        return personas.removeFirst();
    }

    // Introducir a una persona preferente
    public boolean entrarPreferente(Persona persona) {

        if (estaLlena()) {
            return false;
        }

        int posicion = 0;

        // Buscamos la última persona preferente
        for (int i = 0; i < personas.size(); i++) {

            if (personas.get(i).esPreferente()) {
                posicion = i + 1;
            }
        }

        personas.add(posicion, persona);

        return true;
    }

    // Colarse justo detrás de un conocido
    public boolean colarse(Persona persona, int numeroConocido) {

        if (estaLlena()) {
            return false;
        }

        for (int i = 0; i < personas.size(); i++) {

            if (personas.get(i).getNumero() == numeroConocido) {

                personas.add(i + 1, persona);
                return true;
            }
        }

        return false;
    }

    // Una persona abandona la fila
    public boolean abandonar(int posicion) {

        if (posicion < 0 || posicion >= personas.size()) {
            return false;
        }

        personas.remove(posicion);
        return true;
    }

    // Buscar una persona por su número
    public Persona buscar(int numero) {

        for (Persona persona : personas) {

            if (persona.getNumero() == numero) {
                return persona;
            }
        }

        return null;
    }

    public Persona obtener(int posicion) {

        if (posicion < 0 || posicion >= personas.size()) {
            return null;
        }

        return personas.get(posicion);
    }

    public void mostrar() {

        System.out.println(personas);
    }
}