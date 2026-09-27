public class Cola {

    private Persona primero;
    private Persona ultimo;
    private int tamano;

    public Cola() {
        this.primero = null;
        this.ultimo = null;
        this.tamano = 0;
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public int getTamano() {
        return tamano;
    }

    public void encolar(Persona persona) {
        if (persona == null) return;

        if (estaVacia()) {
            primero = persona;
            ultimo = persona;
        } else {
            ultimo.setSiguiente(persona);
            persona.vaDelante(ultimo);
            ultimo = persona;
        }
        tamano++;
    }

    public Persona desencolar() {
        if (estaVacia()) {
            return null;
        }

        Persona atendido = primero;
        primero = primero.devolverSiguiente();

        if (primero == null) {
            ultimo = null;
        }

        tamano--;
        return atendido;
    }

    public String mostrar() {
        if (estaVacia()) return "Cola vacía";
        return primero.mostrar();
    }

    public String mostrarAlReves() {
        if (estaVacia()) return "Cola vacía";
        return primero.mostrarAlReves();
    }

    public Persona buscar(String nombre) {
        if (estaVacia()) return null;
        return primero.buscar(nombre);
    }
}