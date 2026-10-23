public class Ejemplo {
    public static void main(String[] args) {

        Eleccion eleccion = new Eleccion();

        titulo("ALUMNOS EN CLASE");
        eleccion.llegar("Juanito");
        eleccion.llegar("Pepito");
        eleccion.llegar("Anita");
        eleccion.llegar("Luisito");
        eleccion.llegar("Marta");
        eleccion.llegar("Rosa");
        eleccion.llegar("Sofia");

        titulo("CANDIDATURAS");
        eleccion.proponer("Anita");
        eleccion.proponer("Pepito");
        eleccion.proponer("Marta");
        eleccion.llegar("Carlos");
        eleccion.marcharse("Luisito");
        eleccion.proponer("Luisito");
        eleccion.proponer("Pepito");
        eleccion.retirar("Marta");
        eleccion.cerrarPlazo();
        eleccion.proponer("Rosa");
        eleccion.nombrarSecretario("Sofia");

        titulo("VOTACIÓN");
        eleccion.votar("Juanito", "Anita");
        eleccion.votar("Pepito", "Pepito");
        eleccion.votar("Anita", "Anita");
        eleccion.votar("Marta", "");
        eleccion.votar("Rosa", "Pepito");
        eleccion.votar("Carlos", "Batman");
        eleccion.votar("Luisito", "Anita");
        eleccion.votar("Anita", "Anita");

        titulo("RECUENTO");
        String voto = eleccion.cantar();
        eleccion.anotar("Anita");
        eleccion.borrar("Anita");
        eleccion.anotar(voto);
        eleccion.recontar();

        titulo("PROCLAMACIÓN");
        eleccion.proclamar();

        titulo("ABSTENCIÓN");
        eleccion.contarAbstencion();
    }

    static void titulo(String texto) {
        System.out.println();
        System.out.println("=== " + texto + " ===");
    }
}
