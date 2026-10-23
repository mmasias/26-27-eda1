public class Candidato {
    String nombre;
    int votos;

    public Candidato(String nombre) {
        this.nombre = nombre;
        this.votos = 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Candidato)) return false;
        return this.nombre.equals(((Candidato) obj).nombre);
    }
}