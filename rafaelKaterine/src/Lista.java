public class Lista {
    private Nodo primero;

    private static class Nodo {
        Persona persona;
        Nodo siguiente;

        Nodo(Persona persona, Nodo siguiente) {
            this.persona = persona;
            this.siguiente = siguiente;
        }
    }

    public boolean existeReferencia(Persona persona) {
        Nodo actual = primero;
        while (actual != null) {
            if (actual.persona == persona) { 
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public boolean insertar(Persona persona, boolean hacerCopia) {
        if (persona == null) return false;

        if (existeReferencia(persona)) {
            return false; 
        }

        Persona personaAInsertar = hacerCopia ? new Persona(persona) : persona;
        primero = new Nodo(personaAInsertar, primero);
        return true;
    }
}