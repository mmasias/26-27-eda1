public class Main {

    public static void main(String[] args) {
        SimuladorSinCola simulacion = new SimuladorSinCola("Simulacion sin Cola (Usuarios enlazados hacia adelante)");
        simulacion.ejecutar();

        Persona jacobo = new Persona("jacobo");
        Persona hector = new Persona("hector");
        Persona hugo = new Persona("hugo");
        Persona maikol = new Persona("maikol");
        Persona luisFelipe = new Persona("luisFelipe");

        Persona primero = jacobo;

        primero.encolar(hector);
        primero.encolar(hugo);
        primero.encolar(maikol);
        primero.encolar(luisFelipe);

        System.out.println("\n--- VACIANDO Y ATENDIENDO LA COLA ---");

        while (primero != null) {
            System.out.println("Atendiendo a: " + primero.obtenerNombre());
            
            if (primero.haySiguiente()) {
                primero = primero.devolverSiguiente();
            } else {
                primero = null;            }
        }
    }
}
