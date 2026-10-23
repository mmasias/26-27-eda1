public class Pizarra {

    private Candidato[] candidatos;
    private Pila papeletas;
    private Lista alumnos;
    private Candidato[] votos;

    public Pizarra(Candidato[] candidatos, Lista alumnos) {
        this.candidatos = new Candidato[candidatos.length];
        this.alumnos = new Lista();
        this.papeletas = new Pila();
        this.votos = new Candidato[candidatos.length + 2];

        for (int i = 0; i < candidatos.length; i++) {
            Candidato candidato = candidatos[i];
            this.candidatos[i] = candidato;
            this.votos[i] = candidato;
        }
        for (int i = 0; i < alumnos.tamano(); i++) {
            this.alumnos.insertarFinal(alumnos.obtener(i));
        }
        this.votos[candidatos.length] = new Candidato("Nulo");
        this.votos[candidatos.length + 1] = new Candidato("Abstención");
    }
}
