package entregas.AguilarAdriana;

public class Persona {
    private String nombre;
    private String dni;

    public Persona(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public Persona(Persona persona) {
        this.nombre = persona.nombre;
        this.dni = persona.dni;
    }

    public String obtenerNombre() { return nombre; }
    public String obtenerDni() { return dni; }
    public void cambiarNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() { return nombre + " (" + dni + ")"; }
}
