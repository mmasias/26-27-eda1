package elecciones;

class Eleccion {
    private static final int CANDIDATURAS = 0;
    private static final int ELECCION_DE_SECRETARIO = 1;
    private static final int ESPERANDO_VOTACION = 2;
    private static final int VOTACION = 3;
    private static final int ESCRUTINIO = 4;
    private static final int PROCLAMACION = 5;

    private final Lista presentes;
    private final Lista candidaturas;
    private final Lista yaHanVotado;
    private final Pila papeletas;
    private Pizarra pizarra;
    private String secretario;
    private int fase;

    Eleccion(String[] asistentes) {
        presentes = new Lista();
        for (String alumno : asistentes) {
            presentes.insertarAlFinal(alumno);
        }
        candidaturas = new Lista();
        yaHanVotado = new Lista();
        papeletas = new Pila();
        fase = CANDIDATURAS;
        System.out.println("Hay " + presentes.longitud() + " alumnos presentes. Se abre el plazo de candidaturas.");
    }

    void llegaTarde(String alumno) {
        assert !presentes.contiene(alumno);
        presentes.insertarAlFinal(alumno);
        System.out.println(alumno + " llega tarde: se anade a presentes (" + presentes.longitud() + ").");
    }

    void seMarcha(String alumno) {
        assert presentes.contiene(alumno);
        presentes.eliminar(alumno);
        System.out.println(alumno + " se marcha: sale de presentes (" + presentes.longitud() + ").");
    }

    void proponer(String alumno) {
        assert fase == CANDIDATURAS;
        if (!presentes.contiene(alumno)) {
            System.out.println("Candidatura RECHAZADA: " + alumno + " no esta presente.");
        } else if (candidaturas.contiene(alumno)) {
            System.out.println("Candidatura RECHAZADA: " + alumno + " ya es candidato.");
        } else {
            candidaturas.insertarAlFinal(alumno);
            System.out.println("Candidatura aceptada: " + alumno + ".");
        }
    }

    void retirar(String alumno) {
        assert fase == CANDIDATURAS;
        assert candidaturas.contiene(alumno);
        candidaturas.eliminar(alumno);
        System.out.println(alumno + " RETIRA su candidatura.");
    }

    void cerrarCandidaturas() {
        assert fase == CANDIDATURAS;
        String[] candidatos = candidaturas.aArray();
        pizarra = new Pizarra(candidatos);
        fase = ELECCION_DE_SECRETARIO;
        System.out.println("Se cierra el plazo: se copian " + candidatos.length + " candidaturas al array de la pizarra.");
        pizarra.mostrar();
    }

    void nombrarSecretario(String alumno) {
        assert fase == ELECCION_DE_SECRETARIO;
        assert presentes.contiene(alumno);
        secretario = alumno;
        fase = ESPERANDO_VOTACION;
        System.out.println("El profesor nombra secretario a " + secretario + ".");
    }

    void abrirVotacion() {
        assert fase == ESPERANDO_VOTACION;
        fase = VOTACION;
        System.out.println("El presidente abre la votacion.");
    }

    void votar(String alumno, String papeleta) {
        assert fase == VOTACION;
        if (!presentes.contiene(alumno)) {
            System.out.println("Voto RECHAZADO: " + alumno + " no esta presente (recorridos " + presentes.longitud() + " presentes).");
        } else if (yaHanVotado.contiene(alumno)) {
            System.out.println("Voto RECHAZADO: " + alumno + " ya ha votado (recorridos " + yaHanVotado.longitud() + " votantes).");
        } else {
            papeletas.apilar(papeleta);
            yaHanVotado.insertarAlFinal(alumno);
            System.out.println(alumno + " deja su papeleta encima del monton (" + papeletas.longitud() + ").");
        }
    }

    void cerrarVotacion() {
        assert fase == VOTACION;
        fase = ESCRUTINIO;
        System.out.println("El presidente cierra la votacion con " + papeletas.longitud() + " papeletas en el monton.");
    }

    boolean quedanPapeletas() {
        return !papeletas.estaVacia();
    }

    String cantarPapeleta() {
        assert fase == ESCRUTINIO;
        String voto = papeletas.desapilar();
        System.out.println(secretario + " toma la papeleta de encima y canta: " + loQueDice(voto) + " (quedan " + papeletas.longitud() + ").");
        return voto;
    }

    private String loQueDice(String voto) {
        String lectura = "\"" + voto + "\"";
        if (voto.isEmpty()) {
            lectura = "papeleta sin nombre";
        }
        return lectura;
    }

    void anotar(String voto) {
        assert fase == ESCRUTINIO;
        pizarra.anotar(voto);
        System.out.println("  El presidente anota un palito a " + pizarra.opcionDe(voto) + ".");
    }

    void corregir(String anotadoPorError) {
        assert fase == ESCRUTINIO;
        pizarra.borrarPalito(anotadoPorError);
        System.out.println("  ERROR al anotar: el presidente borra el palito de " + pizarra.opcionDe(anotadoPorError) + ".");
    }

    void proclamar() {
        assert fase == ESCRUTINIO;
        assert papeletas.estaVacia();
        fase = PROCLAMACION;
        System.out.println("Monton vacio: las papeletas leidas ya no estan en la pila.");
        pizarra.mostrar();
        pizarra.proclamar();
    }

    void informarAbstencion() {
        assert fase == PROCLAMACION;
        String abstencionistas = "";
        int abstenciones = 0;
        for (String alumno : presentes.aArray()) {
            if (!yaHanVotado.contiene(alumno)) {
                abstencionistas = abstencionistas + " " + alumno;
                abstenciones = abstenciones + 1;
            }
        }
        System.out.println("Abstencion: " + abstenciones + " de " + presentes.longitud() + " presentes no entregaron papeleta:" + abstencionistas + ".");
    }
}
