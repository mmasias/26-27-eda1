class Inicial {
    public static void main(String[] args) {
        
        Persona jacobo = new Persona("Jacobo");
        Persona hector = new Persona("Hector");
        Persona hugo = new Persona("Hugo");
        Persona maikol = new Persona("Maikol");
        Persona luisFelipe = new Persona("Luis Felipe");

        Persona primero = jacobo;
        primero.encolar(hector);
        primero.encolar(hugo);
        primero.encolar(maikol);
        primero.encolar(luisFelipe);

        Persona intruso = new Persona("Intruso");
        intruso.colarseDetrasDe(hugo);

        System.out.println("--- ESTADO DE LA FILA ---");
        primero.mostrar();
        System.out.println("Total de personas: " + primero.contar());

        System.out.println("\n--- ESTADO DE LA FILA ---");
        primero.mostrarAlReves();

        System.out.println("\n--- ATENDIENDO Y VACIANDO LA FILA ---");
        
        if (primero != null) {
            do {
                System.out.println("Atendiendo a: " + primero.obtenerNombre());
                primero = primero.devolverSiguiente();
            } while (primero != null);
        }

        System.out.println("\nLa fila ha sido vaciada.");
    }
}