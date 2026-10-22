public class Ejemplo {
    public static void main(String[] args) {
        Eleccion e = new Eleccion();

        System.out.println("== 1. Llegada de alumnos ==");
        String[] alumnos = {"Ana", "Beto", "Carla", "Dani", "Eva", "Gus", "Hugo"};
        for (int i = 0; i < alumnos.length; i++) {
            e.llega(alumnos[i]);
        }

        System.out.println("\n== 2. Plazo de candidaturas ==");
        e.proponerCandidato("Ana");
        e.proponerCandidato("Carla");
        e.proponerCandidato("Beto");
        e.proponerCandidato("Gael");
        e.retirarCandidato("Carla");
        e.proponerCandidato("Dani");
        e.cerrarPlazo();
        e.proponerCandidato("Hugo");
        e.retirarCandidato("Beto");

        System.out.println("\n== 3. Secretario ==");
        e.elegirSecretario("Hugo");

        System.out.println("\n== 4. Votacion ==");
        e.abrirVotacion();
        e.votar("Ana", "Ana");
        e.votar("Beto", "Beto");
        e.votar("Carla", "");
        e.votar("Dani", "Dani");
        e.votar("Ana", "Beto");
        e.votar("Eva", "Carla");
        e.seMarcha("Eva");
        e.votar("Eva", "Ana");
        e.llega("Fran");
        e.votar("Fran", "Ana");
        e.votar("Gus", "Beto");
        e.seMarcha("Gus");
        e.llega("Gus");
        e.votar("Gus", "Dani");
        e.cerrarVotacion();

        System.out.println("\n== 5. Abstencion (antes del recuento) ==");
        e.informarAbstencion();

        System.out.println("\n== 6. Recuento ==");
        e.leerSiguiente();
        e.leerSiguienteAnotandoMal("Dani");
        e.mostrarPizarra();
        e.corregirAnotacion("Dani", "Ana");
        e.leerSiguiente();
        e.leerSiguiente();
        e.leerSiguiente();
        e.leerSiguiente();
        e.leerSiguiente();

        System.out.println("\n== 7. Proclamacion ==");
        e.proclamar();

        System.out.println("\n== 8. Costes ==");
        e.informeCostes();
    }
}