public class Elecciones {

    private Lista censo;
    private Lista yaVotaron;
    private Cola urna;
    private Pila historialRecuento;

    private final String[] candidatos = {"Perro Sanxe", "Frijol", "Abasjew", "Coletas"};
    final int NUMERO_CANDIDATOS = candidatos.length;

    public Elecciones() {
        this.censo = new Lista();
        this.yaVotaron = new Lista();
        this.urna = new Cola();
        this.historialRecuento = new Pila();
    }

    public void iniciar() {
        System.out.println("=== INICIO DEL PROCESO ELECTORAL ===\n");

        preparacion();
        votacion();
        escrutinioYProclamacion();

        System.out.println("\n=== FIN DEL PROCESO ELECTORAL ===");
    }

    private void preparacion() {
        System.out.println("-- Fase de Preparación --");

        censo.agregar("Pepito");
        censo.agregar("Pedrito");
        censo.agregar("Fulanito");
        censo.agregar("Menganito");

        censo.agregar("Luis");
        censo.eliminar("Pedrito");

        System.out.println("Censo actualizado. Candidatos registrados:");
        for (int i = 0; i < candidatos.length; i++) {
            System.out.println(" [" + i + "] " + candidatos[i]);
        }
        System.out.println();
    }

    private void votacion() {
        System.out.println("-- Fase de Votación --");

        votar("Pedrito", "Frijol");

        votar("Pepito", "Frijol");
        votar("Menganito", "Coletas");
        votar("Fulanito", "BLANCO");
        votar("Luis", "Franco");

        votar("Pepito", "Perro Sanxe");

        System.out.println("Votación finalizada.\n");
    }

    private void votar(String votante, String candidato) {
        if (!censo.contiene(votante)) {
            System.out.println("[Error al votar] " + votante + " no se encuentra en el censo.");
            return;
        }
        if (yaVotaron.contiene(votante)) {
            System.out.println("[Error al votar] " + votante + " eres un corrupto, ya votaste.");
            return;
        }
        yaVotaron.agregar(votante);
        urna.encolar(candidato);
        System.out.println("[Voto registrado] " + votante + " acaba de votar.");
    }

    private void escrutinioYProclamacion() {
        System.out.println("-- Fase de Escrutinio --");

        int[] recuento = new int[NUMERO_CANDIDATOS + 2];

        while (!urna.estaVacia()) {
            String papeleta = urna.desencolar();
            historialRecuento.apilar(papeleta);

            switch (papeleta) {
                case "Perro Sanxe" -> recuento[0]++;
                case "Frijol" -> recuento[1]++;
                case "Abasjew" -> recuento[2]++;
                case "Coletas" -> recuento[3]++;
                case "BLANCO" -> recuento[4]++;
                default -> recuento[5]++;
            }
        }

        System.out.println("\n[Mesa Electoral] Se corrigió una anotación errónea en el acta.");
        String ultimoVoto = historialRecuento.desapilar();
        if (ultimoVoto != null && ultimoVoto.equals("VOTO_NULO_CORRUPTO")) {
            recuento[5]--;
        }

        System.out.println("\nResultados finales del recuento:");
        System.out.println(" - " + candidatos[0] + ": " + recuento[0] + " votos");
        System.out.println(" - " + candidatos[1] + ": " + recuento[1] + " votos");
        System.out.println(" - " + candidatos[2] + ": " + recuento[2] + " votos");
        System.out.println(" - " + candidatos[3] + ": " + recuento[3] + " votos");
        System.out.println(" - Votos en Blanco: " + recuento[4]);
        System.out.println(" - Votos Nulos: " + recuento[5]);

        int abstencion = censo.getTamanoLista() - yaVotaron.getTamanoLista();
        System.out.println("\nReporte de Abstención:");
        System.out.println(" - Presentes en censo: " + censo.getTamanoLista());
        System.out.println(" - Total votantes: " + yaVotaron.getTamanoLista());
        System.out.println(" - Abstención: " + abstencion);
    }

    public static void main(String[] args) {
        Elecciones elecciones = new Elecciones();
        elecciones.iniciar();
    }
}