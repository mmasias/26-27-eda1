public class Alumno {

    private String nombre;
    private String dni;

    public Alumno(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() {
    return nombre;
    }

    public String getDni() {
    return dni;
    }

    @Override
    public String toString() {
    return nombre + " (DNI: " + dni + ")";
    }

}
