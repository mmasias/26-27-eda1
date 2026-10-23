public class Alumno {
    String nombre;
    boolean yaVoto;

    public Alumno(String nombre) {
        this.nombre = nombre;
        this.yaVoto = false;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Alumno)) return false;
        return this.nombre.equals(((Alumno) obj).nombre);
    }
}