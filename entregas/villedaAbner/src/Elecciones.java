public class Elecciones {

    public static void main(String[] args) {
        ListaAlumnos presentes = new ListaAlumnos();
        ListaCandidatos candidatos = new ListaCandidatos();
        ColaUrna urna = new ColaUrna();
        int votosBlancosONulos = 0;
        int totalVotosEmitidos = 0;

        Alumno juan = new Alumno("Juan");
        Alumno maria = new Alumno("Maria");
        Alumno pepe = new Alumno("Pepe");
        Alumno manuel = new Alumno("Manuel");
        Alumno p2 = new Alumno("P2");
        Alumno hercules = new Alumno("Hercules");

        presentes.registrarLlegada(juan);
        presentes.registrarLlegada(maria);
        presentes.registrarLlegada(pepe);
        presentes.registrarLlegada(manuel);
        presentes.registrarLlegada(p2);
        presentes.registrarLlegada(hercules);

        // p2 tuvo que ir al baño

        presentes.eliminarAlumno(p2);

        candidatos.agregarCandidato(juan);
        candidatos.agregarCandidato(hercules);
        candidatos.agregarCandidato(p2);

        Voto votoDeJuan = new Voto("Juan");
        urna.encolar(votoDeJuan);

        Voto votoDeMaria = new Voto("Hercules");
        urna.encolar(votoDeMaria);

        Voto votoDePepe = new Voto("Hercules");
        urna.encolar(votoDePepe);

        Voto votoDeManuel = new Voto("Batman");
        urna.encolar(votoDeManuel);

        Voto votoDeHercules = new Voto("Hercules");
        urna.encolar(votoDeHercules);

        Voto votoActual = urna.desencolar();
        

        while (votoActual != null) {
            boolean votoValido = candidatos.sumarVotoA(votoActual.intencionVoto);

            if (!votoValido) {
                votosBlancosONulos++;
            }
            votoActual = urna.desencolar();
            totalVotosEmitidos++;
        }

        int abstencion = presentes.contarPresentes() - totalVotosEmitidos;
        System.out.println("Abstenciones (no votaron): " + abstencion);

        candidatos.calcularGanador();
    }
}
