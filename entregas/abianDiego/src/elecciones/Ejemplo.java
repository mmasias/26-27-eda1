package elecciones;

class Ejemplo {
    private static final String EN_BLANCO = "";

    public static void main(String[] args) {
        titulo("PRESENTES");
        Eleccion eleccion = new Eleccion(new String[] { "Juanito", "Pepito", "Anita", "Luisito", "Marta", "Rosa", "Lucas" });
        eleccion.llegaTarde("Carlos");
        eleccion.seMarcha("Luisito");

        titulo("CANDIDATURAS");
        eleccion.proponer("Anita");
        eleccion.proponer("Pepito");
        eleccion.proponer("Marta");
        eleccion.proponer("Luisito");
        eleccion.retirar("Marta");
        eleccion.cerrarCandidaturas();

        titulo("SECRETARIO");
        eleccion.nombrarSecretario("Rosa");

        titulo("VOTACION");
        eleccion.abrirVotacion();
        eleccion.votar("Juanito", "Anita");
        eleccion.votar("Pepito", "Pepito");
        eleccion.votar("Anita", "Anita");
        eleccion.votar("Marta", EN_BLANCO);
        eleccion.votar("Luisito", "Anita");
        eleccion.votar("Carlos", "Batman");
        eleccion.votar("Pepito", "Pepito");
        eleccion.votar("Rosa", "Pepito");
        eleccion.cerrarVotacion();

        titulo("ESCRUTINIO");
        String primerVoto = eleccion.cantarPapeleta();
        eleccion.anotar("Anita");
        eleccion.corregir("Anita");
        eleccion.anotar(primerVoto);
        while (eleccion.quedanPapeletas()) {
            eleccion.anotar(eleccion.cantarPapeleta());
        }

        titulo("PROCLAMACION");
        eleccion.proclamar();
        eleccion.informarAbstencion();
    }

    private static void titulo(String texto) {
        System.out.println();
        System.out.println("== " + texto + " ==");
    }
}
