public class Ejemplo {

    public static void main(String[] args) {

        Eleccion eleccion = new Eleccion();

        System.out.println("=== CONFIGURACION ELECTORAL ===");

        eleccion.proponerCandidatura("Hector San Miguel");
        eleccion.proponerCandidatura("Adriana Aguilar");
        eleccion.proponerCandidatura("Katerine Rafael");
        eleccion.proponerCandidatura("Francisco Dominguez");
        eleccion.proponerCandidatura("Rocio Fernandez");

        eleccion.retirarCandidatura("Francisco Dominguez");
        eleccion.rechazarCandidatura("Rocio Fernandez");

        Alumno ana = new Alumno("Ana", "D1");
        Alumno luis = new Alumno("Luis", "D2");
        Alumno marta = new Alumno("Marta", "D3");
        Alumno sofia = new Alumno("Sofia", "D4");
        Alumno elena = new Alumno("Elena", "D5");
        Alumno pedro = new Alumno("Pedro", "D6");
        Alumno jorge = new Alumno("Jorge", "D7");
        Alumno clara = new Alumno("Clara", "D8");

        eleccion.inscribirAlumno(ana);
        eleccion.inscribirAlumno(luis);
        eleccion.inscribirAlumno(marta);
        eleccion.inscribirAlumno(sofia);
        eleccion.inscribirAlumno(elena);
        eleccion.inscribirAlumno(pedro);
        eleccion.inscribirAlumno(jorge);
        eleccion.inscribirAlumno(clara);

        eleccion.cerrarBoleta();

        System.out.println("\n=== PASE DE LISTA ===");

        eleccion.registrarPresencia(ana);
        eleccion.registrarPresencia(luis);
        eleccion.registrarPresencia(marta);
        eleccion.registrarPresencia(sofia);
        eleccion.registrarPresencia(elena);
        eleccion.registrarPresencia(pedro);
        eleccion.registrarPresencia(clara);

        System.out.println("\n=== INCIDENCIAS DE ASISTENCIA ===");

        eleccion.alumnoLlegaTarde(jorge);
        eleccion.alumnoSeMarcha(pedro);

        System.out.println("\n=== COLA DE VOTACION ===");

        eleccion.ponerseEnCola(ana);
        eleccion.ponerseEnCola(luis);
        eleccion.ponerseEnCola(marta);
        eleccion.ponerseEnCola(sofia);
        eleccion.ponerseEnCola(elena);
        eleccion.ponerseEnCola(jorge);

        System.out.println("\n=== VOTACION ===");

        eleccion.atenderSiguiente("Hector San Miguel");
        eleccion.atenderSiguiente("Adriana Aguilar");
        eleccion.atenderSiguiente("BLANCO");
        eleccion.atenderSiguiente("NULO");
        eleccion.atenderSiguiente("Hector San Miguel");
        eleccion.atenderSiguiente("Adriana Aguilar");

        System.out.println("\n=== VOTANTE AUSENTE ===");

        eleccion.ponerseEnCola(pedro);
        eleccion.atenderSiguiente("Katerine Rafael");

        System.out.println("\n=== INTENTO DE DOBLE VOTO ===");

        eleccion.ponerseEnCola(ana);
        eleccion.atenderSiguiente("Katerine Rafael");

        System.out.println("\n=== CIERRE DE VOTACION ===");

        eleccion.cerrarUrna();

        System.out.println("\n=== ESCRUTINIO ===");

        eleccion.iniciarEscrutinio();
        eleccion.contarSiguiente("Hector San Miguel");

        System.out.println("\n=== CORRECCION DEL ERROR ===");

        eleccion.corregirAnotacion();

        eleccion.contarSiguiente("Hector San Miguel");
        eleccion.contarSiguiente("NULO");
        eleccion.contarSiguiente("BLANCO");
        eleccion.contarSiguiente("Adriana Aguilar");
        eleccion.contarSiguiente("Hector San Miguel");

        System.out.println("\n=== RESULTADO FINAL ===");

        eleccion.proclamar();
    }

    
}
