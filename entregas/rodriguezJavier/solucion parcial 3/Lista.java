public class Lista {

    private Nodo primero;

    public boolean insertarPersona(Persona persona, boolean copiar) {

        Nodo actual = primero;

        while (actual != null) {

            if (actual.getPersona().getDni().equals(persona.getDni())) {
                return false;
            }

            actual = actual.getSiguiente();
        }

        Persona personaGuardar;

        if (copiar) {
            personaGuardar = new Persona(persona);
        } else {
            personaGuardar = persona;
        }

        Nodo nuevo = new Nodo(personaGuardar, primero);
        primero = nuevo;

        return true;
    }

    public boolean contiene(Persona persona) {

        Nodo actual = primero;

        while (actual != null) {

            if (actual.getPersona().getDni().equals(persona.getDni())) {
                return true;
            }

            actual = actual.getSiguiente();
        }

        return false;
    }

    public Persona obtenerPersona(String dni) {

        Nodo actual = primero;

        while (actual != null) {

            if (actual.getPersona().getDni().equals(dni)) {
                return actual.getPersona();
            }

            actual = actual.getSiguiente();
        }

        return null;
    }
}