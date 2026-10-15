public class Fila {

    private Persona[] personas;
    private int tamano;
    private final int capacidadMaxima;

    public Fila(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.personas = new Persona[16];
        this.tamano = 0;
    }

    private void asegurarCapacidad() {
        if (tamano == personas.length) {
            Persona[] nuevo = new Persona[personas.length * 2];
            for (int i = 0; i < tamano; i++) {
                nuevo[i] = personas[i];
            }
            personas = nuevo;
        }
    }

    public boolean estaLlena() {
        return tamano >= capacidadMaxima;
    }

    public boolean estaVacia() {
        return tamano == 0;
    }

    public int tamano() {
        return tamano;
    }

    public void agregarAlFinal(Persona persona) {
        asegurarCapacidad();
        personas[tamano] = persona;
        tamano++;
    }

    public void agregarAlFrente(Persona persona) {
        agregarEnPosicion(persona, 0);
    }

    public void agregarDespuesDe(Persona persona, int indice) {
        agregarEnPosicion(persona, indice + 1);
    }

    private void agregarEnPosicion(Persona persona, int posicion) {
        asegurarCapacidad();
        for (int i = tamano; i > posicion; i--) {
            personas[i] = personas[i - 1];
        }
        personas[posicion] = persona;
        tamano++;
    }

    public int indiceUltimoPreferente() {
        int indice = -1;
        for (int i = 0; i < tamano; i++) {
            if (personas[i].isPreferente()) {
                indice = i;
            }
        }
        return indice;
    }

    public int indiceAleatorio() {
        return (int) (Math.random() * tamano);
    }

    public Persona atenderFrente() {
        Persona persona = personas[0];
        for (int i = 0; i < tamano - 1; i++) {
            personas[i] = personas[i + 1];
        }
        personas[tamano - 1] = null;
        tamano--;
        return persona;
    }

    public void removerEnIndice(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            personas[i] = personas[i + 1];
        }
        personas[tamano - 1] = null;
        tamano--;
    }

    public Persona obtener(int indice) {
        return personas[indice];
    }
}