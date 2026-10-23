public class Eleccion {
    private Lista presentes = new Lista();
    private Lista candidaturas = new Lista();
    private Lista votantes = new Lista();
    private Pila monton = new Pila();
    private Pizarra pizarra;
    private boolean plazoAbierto = true;
    private String secretario;

    public void llegar(String alumno) {
        presentes.insertar(alumno);
        System.out.println("Entra en clase: " + alumno);
    }

    public void marcharse(String alumno) {
        if (presentes.eliminar(alumno)) {
            System.out.println("Sale de clase: " + alumno);
        }
    }

    public void proponer(String alumno) {
        if (!plazoAbierto) {
            System.out.println("Candidatura RECHAZADA: " + alumno + ", el plazo está cerrado");
        } else if (!presentes.contiene(alumno)) {
            System.out.println("Candidatura RECHAZADA: " + alumno + " no está presente");
        } else if (candidaturas.contiene(alumno)) {
            System.out.println("Candidatura RECHAZADA: " + alumno + " ya es candidato");
        } else {
            candidaturas.insertar(alumno);
            System.out.println("Candidatura aceptada: " + alumno);
        }
    }

    public void retirar(String alumno) {
        if (plazoAbierto && candidaturas.eliminar(alumno)) {
            System.out.println("Candidatura RETIRADA: " + alumno);
        }
    }

    public void cerrarPlazo() {
        plazoAbierto = false;
        String[] candidatos = candidaturas.volcar();
        pizarra = new Pizarra(candidatos);
        System.out.print("Plazo cerrado. Candidatos:");
        for (String candidato : candidatos) {
            System.out.print(" " + candidato);
        }
        System.out.println();
    }

    public void nombrarSecretario(String alumno) {
        secretario = alumno;
        System.out.println("El profesor nombra secretario a " + secretario);
    }

    public void votar(String alumno, String papeleta) {
        if (!presentes.contiene(alumno)) {
            System.out.println("Voto RECHAZADO: " + alumno + " no está presente");
        } else if (votantes.contiene(alumno)) {
            System.out.println("Voto RECHAZADO: " + alumno + " ya ha votado");
        } else {
            monton.apilar(papeleta);
            votantes.insertar(alumno);
            System.out.println(alumno + " deja su papeleta en el montón");
        }
    }

    public String cantar() {
        String papeleta = monton.desapilar();
        if (papeleta.isEmpty()) {
            System.out.println(secretario + " canta: en blanco");
        } else {
            System.out.println(secretario + " canta: " + papeleta);
        }
        return papeleta;
    }

    public void anotar(String voto) {
        pizarra.anotar(voto);
    }

    public void borrar(String voto) {
        pizarra.borrar(voto);
    }

    public void recontar() {
        while (!monton.estaVacia()) {
            anotar(cantar());
        }
        pizarra.mostrar();
    }

    public void proclamar() {
        pizarra.proclamar();
    }

    public void contarAbstencion() {
        int abstenciones = 0;
        for (String alumno : presentes.volcar()) {
            if (!votantes.contiene(alumno)) {
                abstenciones++;
                System.out.println("No ha votado: " + alumno);
            }
        }
        System.out.println("Presentes: " + presentes.contar() + " | Votantes: " + votantes.contar()
                + " | Abstención: " + abstenciones);
    }
}
