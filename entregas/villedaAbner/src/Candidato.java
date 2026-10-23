public class Candidato {
    String nombreCandidato;
    int cantidadDeVotos;


    public Candidato(Alumno alumnoCandidato){
        nombreCandidato= alumnoCandidato.nombre;
        cantidadDeVotos = 0;
    }

    public void darVoto(){
        cantidadDeVotos++;
    }

    public int obtenerTotalVotos(){
        return cantidadDeVotos;
    }
}
