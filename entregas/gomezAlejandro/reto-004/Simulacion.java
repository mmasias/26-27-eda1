
public class Simulacion {

    public static void main(String[] args) {

        ListaAlumnosPresentes alumnos = new ListaAlumnosPresentes();

        System.out.println("--- COMIENZO DE LA SESION ---");

        alumnos.insertar("Alba");
        alumnos.insertar("Bruno");
        alumnos.insertar("Diego");
        alumnos.insertar("Lucia");
        alumnos.insertar("Nora");
        alumnos.insertar("Oscar");
        alumnos.insertar("Vega");

        alumnos.mostrar();

        System.out.println();
        System.out.println("--- OSCAR ABANDONA LA SESION ---");

        alumnos.eliminar("Oscar");
        alumnos.mostrar();

        System.out.println();
        System.out.println("--- TERESA LLEGA TARDE ---");

        alumnos.insertar("Teresa");
        alumnos.mostrar();

        System.out.println();
        System.out.println("--- APERTURA DE CANDIDATURAS ---");

        ListaCandidatos candidatos = new ListaCandidatos(alumnos);

        candidatos.insertar("Alba");
        candidatos.insertar("Bruno");
        candidatos.insertar("Diego");
        candidatos.insertar("Lucia");

        System.out.println("Candidaturas presentadas:");
        candidatos.mostrar();

        System.out.println();
        System.out.println("Mateo intenta presentarse:");

        if (!candidatos.insertar("Mateo")) {
            System.out.println("Candidatura rechazada: Mateo no está presente.");
        }

        System.out.println();
        System.out.println("--- RETIRADA DE UNA CANDIDATURA ---");

        candidatos.retirar("Diego");

        System.out.println("Candidaturas definitivas:");
        candidatos.mostrar();

        System.out.println();
        System.out.println("--- CIERRE DE CANDIDATURAS ---");

        Votacion votacion = new Votacion(alumnos, candidatos);

        votacion.cerrarCandidaturas();

        System.out.println();
        System.out.println("--- COMIENZO DE LA VOTACION ---");

        votacion.registrarVoto("Alba", "Alba");
        votacion.registrarVoto("Bruno", "Bruno");
        votacion.registrarVoto("Diego", "Alba");
        votacion.registrarVoto("Lucia", "Bruno");
        votacion.registrarVoto("Nora", "");
        votacion.registrarVoto("Teresa", "OpcionInvalida");

        System.out.println();
        System.out.println("--- INTENTO DE VOTO DE OSCAR ---");

        votacion.registrarVoto("Oscar", "Alba");

        System.out.println();
        System.out.println("--- INTENTO DE VOTO DUPLICADO ---");

        votacion.registrarVoto("Alba", "Bruno");

        System.out.println();
        System.out.println("--- CIERRE DE LA VOTACION ---");

        votacion.contarPapeletas();
        votacion.mostrarRecuento();

        System.out.println();
        System.out.println("--- PROCLAMACION DEL RESULTADO ---");

        votacion.mostrarResultado();

        System.out.println();
        System.out.println("--- CORRECCION DEL RECUENTO ---");

        votacion.modificarRecuento("Alba", 1);
        votacion.mostrarRecuento();

        System.out.println();
        System.out.println("--- RECTIFICACION DEL ERROR ---");

        votacion.modificarRecuento("Alba", -1);
        votacion.mostrarRecuento();

        System.out.println();
        System.out.println("--- RESULTADO DEFINITIVO ---");

        votacion.mostrarResultado();

        System.out.println();
        System.out.println("--- CALCULO DE ABSTENCIONES ---");

        votacion.calcularAbstencion();
    }
}
