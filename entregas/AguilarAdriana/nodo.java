package entregas.AguilarAdriana;

public class Nodo {
    private Persona persona;
    private Nodo siguiente;

    public Nodo(Persona persona, Nodo siguiente) {
        this.persona = persona;
        this.siguiente = siguiente;
    }

    public Persona obtenerPersona() { return persona; }
    public Nodo obtenerSiguiente() { return siguiente; }
}