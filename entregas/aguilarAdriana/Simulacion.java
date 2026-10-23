package entregas.aguilarAdriana;

public class Simulacion {
    public static void main(String[] args) {
        ListaPresentes presentes = new ListaPresentes();
        llegar(presentes, 1);
        llegar(presentes, 2);
        llegar(presentes, 3);

        Candidaturas candidaturas = new Candidaturas();
        proponer(candidaturas, presentes, 1);
        proponer(candidaturas, presentes, 2);
        proponer(candidaturas, presentes, 3);
        retirar(candidaturas, 3);
        llegar(presentes, 4);
        proponer(candidaturas, presentes, 4);
        presentes.salir(2);
        llegar(presentes, 5);
        proponer(candidaturas, presentes, 6);
        candidaturas.cerrar();

        Candidato[] candidatos = candidaturas.vistaDefinitiva();
        mostrarCandidatos(candidatos);

        Pila monton = new Pila();
        ListaEnlazada yaHanVotado = new ListaEnlazada();
        Papeleta[] papeletas = new Papeleta[4];

        votar(monton, presentes, yaHanVotado, papeletas,
                1, new Papeleta(0, "1"));
        votar(monton, presentes, yaHanVotado, papeletas,
                3, new Papeleta(1, "4"));
        votar(monton, presentes, yaHanVotado, papeletas,
                4, new Papeleta(2, ""));
        votar(monton, presentes, yaHanVotado, papeletas,
                5, new Papeleta(3, "Batman"));
        votar(monton, presentes, yaHanVotado, papeletas,
                6, new Papeleta(4, "1"));
        votar(monton, presentes, yaHanVotado, papeletas,
                1, new Papeleta(5, "4"));

        ResultadoRecuento pizarra = new ResultadoRecuento(candidatos);
        Secretario secretario = new Secretario(4);
        contar(monton, papeletas, secretario, pizarra);

        System.out.println("Corrección de una anotación errónea");
        pizarra.sumarVotoDe("1");
        pizarra.corregirVotoDe("1");

        mostrarPizarra(pizarra);
        System.out.println(pizarra.proclamar());
    }

    private static void llegar(ListaPresentes presentes, int alumno) {
        presentes.entrar(alumno);
        System.out.println("Alumno presente: " + alumno);
    }

    private static void proponer(
            Candidaturas candidaturas,
            ListaPresentes presentes,
            int alumno) {
        if (candidaturas.proponer(alumno, presentes)) {
            System.out.println("Candidatura aceptada: " + alumno);
        } else {
            System.out.println("Candidatura rechazada: " + alumno);
        }
    }

    private static void retirar(Candidaturas candidaturas, int alumno) {
        if (candidaturas.retirar(alumno)) {
            System.out.println("Candidatura retirada: " + alumno);
        }
    }

    private static void votar(
            Pila monton,
            ListaPresentes presentes,
            ListaEnlazada yaHanVotado,
            Papeleta[] papeletas,
            int alumno,
            Papeleta papeleta) {
        if (!presentes.contiene(alumno)) {
            System.out.println("Papeleta rechazada: " + alumno
                    + " no está presente");
        } else if (yaHanVotado.contiene(alumno)) {
            System.out.println("Papeleta rechazada: " + alumno
                    + " ya ha votado");
        } else {
            papeletas[papeleta.obtenerId()] = papeleta;
            monton.push(papeleta.obtenerId());
            yaHanVotado.insertarAlPrincipio(alumno);
            System.out.println("Papeleta aceptada: " + alumno);
        }
    }

    private static void contar(
            Pila monton,
            Papeleta[] papeletas,
            Secretario secretario,
            ResultadoRecuento pizarra) {
        while (!monton.estaVacia()) {
            int idPapeleta = secretario.extraerPapeleta(monton);
            Papeleta papeleta = papeletas[idPapeleta];
            String voto = papeleta.obtenerNombreEscrito();
            System.out.println("Secretario: "
                    + (papeleta.estaEnBlanco() ? "en blanco" : voto));
            pizarra.sumarVotoDe(voto);
        }
    }

    private static void mostrarCandidatos(Candidato[] candidatos) {
        System.out.println("CANDIDATOS------------------");
        for (int i = 0; i < candidatos.length; i++) {
            System.out.println(candidatos[i].obtenerNombre());
        }
        System.out.println("---------------------------");
    }

    private static void mostrarPizarra(ResultadoRecuento pizarra) {
        String[] nombres = pizarra.nombres();
        int[] votos = pizarra.votos();
        System.out.println("PIZARRA---------------------");
        for (int i = 0; i < nombres.length; i++) {
            System.out.println(nombres[i] + " |".repeat(votos[i])
                    + " (" + votos[i] + ")");
        }
        System.out.println("---------------------------");
    }
}
