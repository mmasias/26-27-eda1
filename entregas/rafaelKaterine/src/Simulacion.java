public class Simulacion {

    public static void main(String[] args) {
        Persona jacobo = new Persona("Jacobo");
        Persona hector = new Persona("Héctor");
        Persona hugo = new Persona("Hugo");
        Persona maikol = new Persona("Maikol");
        Persona luisFelipe = new Persona("Luis Felipe");

        Cola cola = new Cola();
        cola.encolar(jacobo);
        cola.encolar(hector);
        cola.encolar(hugo);
        cola.encolar(maikol);
        cola.encolar(luisFelipe);

        System.out.println("Cola actual: " + cola.mostrar());
        System.out.println("Tamaño de la cola: " + cola.getTamano());
        System.out.println("Cola vista al revés: " + cola.mostrarAlReves());

        System.out.println("\nBuscar a Hugo en la cola:");
        Persona personaBuscada = cola.buscar("Hugo");
        System.out.println("Buscando a 'Hugo': " + (personaBuscada != null ? "Encontrado" : "No encontrado"));

        System.out.println("\n--- PROCESO DE ATENCIÓN DE LA COLA ---");
        int turno = 1;
        while (!cola.estaVacia()) {
            Persona atendido = cola.desencolar();
            System.out.println("Turno " + turno + ": Atendiendo a " + atendido.getNombre());
            System.out.println("   -> Quedan en cola (" + cola.getTamano() + " personas): " + cola.mostrar());
            turno++;
        }

        System.out.println("\n--- ESTADO FINAL ---");
        System.out.println("Estado de la cola: " + cola.mostrar());
    }
}