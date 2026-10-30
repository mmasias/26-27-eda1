public class Nodo {

    private Persona persona;
    private Nodo siguiente;

    public Nodo(Persona persona, Nodo siguiente) {
        this.persona = persona;
        this.siguiente = siguiente;
    }

    public Persona getPersona() {
        return persona;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }
}