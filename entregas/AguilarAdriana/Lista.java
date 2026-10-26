package entregas.AguilarAdriana;

public class Lista {
    private Nodo primero;

    public void insertarPersona(Persona persona) {
        Nodo nuevo = new Nodo(persona, primero);
        primero = nuevo;
    }

    public boolean contiene(Persona persona) {
        Nodo actual = primero;
        while (actual != null) {
            if (actual.obtenerPersona().obtenerDni().equals(persona.obtenerDni())) {
                return true;
            }
            actual = actual.obtenerSiguiente();
        }
        return false;
    }

    public Persona obtenerPersona(String dni) {
        Nodo actual = primero;
        while (actual != null) {
            if (actual.obtenerPersona().obtenerDni().equals(dni)) {
                return actual.obtenerPersona();
            }
            actual = actual.obtenerSiguiente();
        }
        return null;
    }

    public void mostrar() {
        Nodo actual = primero;
        while (actual != null) {
            System.out.println("  " + actual.obtenerPersona());
            actual = actual.obtenerSiguiente();
        }
    }
}