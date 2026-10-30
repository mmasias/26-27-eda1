public class persona {

    private String nombre;
    private String dni;

    public persona(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public persona(persona persona) {
        this.nombre = persona.nombre;
        this.dni = persona.dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}