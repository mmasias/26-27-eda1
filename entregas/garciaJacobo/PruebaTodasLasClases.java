package entregas.garciaJacobo;

public class PruebaTodasLasClases {
    private Console console;

    public static void main(String[] args) {
        Console console = new Console();
        Queue cola = new Queue();

        System.out.println(cola.estaVacia());

        cola.insertarAlFinal("a");
        cola.insertarAlFinal("b");
        cola.insertarAlFinal("c");
        System.out.println(cola.estaVacia());

        System.out.println(cola.sacarPrimero());
        System.out.println(cola.sacarPrimero());
        System.out.println(cola.sacarPrimero());
        System.out.println(cola.estaVacia());

        ListaAlumnosPresentes lista = new ListaAlumnosPresentes();
        System.out.println("----------------");
        lista.insertarInicio("jacobo");
        lista.insertarInicio("hugo");
        lista.insertarInicio("kiko");
        lista.mostrar();
        lista.eliminar("jacobo");
        System.out.println("----------------");
        lista.mostrar();
        lista.eliminar("kiko");

        System.out.println("----------------");
        lista.mostrar();

        ListaCandidatos candidatos = new ListaCandidatos();

        candidatos.insertarInicio("Ana");
        candidatos.insertarInicio("Luis");
        candidatos.insertarInicio("Carlos");

        console.writeln("Candidatos:");
        candidatos.mostrar();

        console.writeln("¿Ana es candidata?");
        console.writeln(candidatos.contiene("Ana"));

        console.writeln("Eliminamos a Luis:");
        candidatos.eliminar("Luis");

        console.writeln("Candidatos después de eliminar:");
        candidatos.mostrar();

        console.writeln("tamaño:" + candidatos.tamaño());
        System.out.println("----------------");

        console.writeln("Mostrar array de candidatos");
        String[] arrayCandidatos = candidatos.convertirAArray();
        candidatos.mostrarArray(arrayCandidatos);
        String[] opciones = candidatos.obtenerOpcionesVotacion(arrayCandidatos);
        console.writeln("Mostrar opciones de votacion");
        candidatos.mostrarArray(opciones);

       int[] recuento = candidatos.crearRecuento(opciones);

       candidatos.mostrarRecuento(recuento);
    }

}
