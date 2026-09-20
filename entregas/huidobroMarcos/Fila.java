package huidobroMarcos;

public class Fila {

    private Persona[] personas;
    private int longitud;

    public Fila(int capacidad) {
        personas = new Persona[capacidad];
        longitud = 0;
    }

    public void entrar(Persona persona) {
        personas[longitud] = persona;
        longitud++;
    }

    public Persona salir() {
        Persona persona = personas[0];

        for (int i = 0; i < longitud - 1; i++) {
            personas[i] = personas[i + 1];
        }

        longitud--;

        return persona;
    }

    public int longitud() {
        return longitud;
    }
}
