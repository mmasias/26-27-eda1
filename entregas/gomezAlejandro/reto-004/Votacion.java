public class Votacion {
    private final ListaAlumnosPresentes presentes;
    private final ListaCandidatos candidatos;
    private final Queue papeletas;
    private final List votantes;

    private String[] opciones;
    private int[] recuento;

    private boolean candidaturasCerradas;
    private boolean recuentoRealizado;

    public Votacion(
            ListaAlumnosPresentes presentes,
            ListaCandidatos candidatos) {

        this.presentes = presentes;
        this.candidatos = candidatos;

        papeletas = new Queue();
        votantes = new List();

        opciones = null;
        recuento = null;

        candidaturasCerradas = false;
        recuentoRealizado = false;
    }

    public void cerrarCandidaturas() {
        if (candidaturasCerradas) {
            System.out.println("Las candidaturas ya están cerradas.");
            return;
        }

        String[] listaCandidatos = candidatos.convertirAArray();

        opciones = new String[listaCandidatos.length + 2];

        for (int i = 0; i < listaCandidatos.length; i++) {
            opciones[i] = listaCandidatos[i];
        }

        opciones[listaCandidatos.length] = "Blanco";
        opciones[listaCandidatos.length + 1] = "Nulo";

        recuento = new int[opciones.length];

        candidaturasCerradas = true;

        System.out.println("Candidaturas cerradas.");
    }

    public boolean registrarVoto(String alumno, String voto) {
        if (!candidaturasCerradas) {
            System.out.println("Todavía no se puede votar.");
            return false;
        }

        if (recuentoRealizado) {
            System.out.println("El recuento ya se ha realizado.");
            return false;
        }

        if (!presentes.contiene(alumno)) {
            System.out.println(alumno + " no está presente y no puede votar.");
            return false;
        }

        if (votantes.contains(alumno)) {
            System.out.println(alumno + " ya ha votado.");
            return false;
        }

        votantes.insertEnd(alumno);
        papeletas.insertEnd(new Papeleta(alumno, voto));

        System.out.println("Voto registrado para " + alumno + ".");
        return true;
    }

    public void contarPapeletas() {
        if (!candidaturasCerradas) {
            System.out.println("Primero hay que cerrar las candidaturas.");
            return;
        }

        if (recuentoRealizado) {
            System.out.println("El recuento ya se ha realizado.");
            return;
        }

        while (!papeletas.isEmpty()) {
            Papeleta papeleta = papeletas.removeFirst();
            String voto = papeleta.getVoto();

            if (voto == null || voto.equals("")) {
                sumarVoto("Blanco");
            } else if (voto.equals("Blanco")) {
                sumarVoto("Blanco");
            } else if (voto.equals("Nulo")) {
                sumarVoto("Nulo");
            } else if (candidatos.contiene(voto)) {
                sumarVoto(voto);
            } else {
                System.out.println(
                        "La papeleta de " + papeleta.getAlumno()
                        + " es nula: opción no válida.");

                sumarVoto("Nulo");
            }
        }

        recuentoRealizado = true;
        System.out.println("Recuento terminado.");
    }

    private void sumarVoto(String opcion) {
        int posicion = buscarOpcion(opcion);

        if (posicion != -1) {
            recuento[posicion]++;
        }
    }

    private int buscarOpcion(String opcion) {
        if (opciones == null) {
            return -1;
        }

        for (int i = 0; i < opciones.length; i++) {
            if (opciones[i].equals(opcion)) {
                return i;
            }
        }

        return -1;
    }

    public boolean modificarRecuento(String opcion, int cambio) {
        if (!recuentoRealizado) {
            System.out.println("Primero hay que realizar el recuento.");
            return false;
        }

        int posicion = buscarOpcion(opcion);

        if (posicion == -1) {
            System.out.println("La opción no existe.");
            return false;
        }

        if (recuento[posicion] + cambio < 0) {
            System.out.println("La corrección no puede dejar votos negativos.");
            return false;
        }

        recuento[posicion] += cambio;

        System.out.println(
                "Recuento corregido para " + opcion
                + ". Nuevo total: " + recuento[posicion]);

        return true;
    }

    public void mostrarRecuento() {
        if (!recuentoRealizado) {
            System.out.println("Todavía no se ha realizado el recuento.");
            return;
        }

        System.out.println("\n--- RECUENTO ---");

        for (int i = 0; i < opciones.length; i++) {
            System.out.println(opciones[i] + ": " + recuento[i]);
        }
    }

    public void mostrarResultado() {
        if (!recuentoRealizado) {
            System.out.println("Todavía no se ha realizado el recuento.");
            return;
        }

        int maximo = 0;
        int ganadores = 0;
        String ganador = "";

        for (int i = 0; i < candidatos.tamano(); i++) {
            int votos = recuento[i];

            if (votos > maximo) {
                maximo = votos;
                ganador = opciones[i];
                ganadores = 1;
            } else if (votos == maximo && votos > 0) {
                ganadores++;
            }
        }

        System.out.println("\n--- RESULTADO ---");

        if (maximo == 0) {
            System.out.println("Ningún candidato ha recibido votos.");
        } else if (ganadores > 1) {
            System.out.println("Hay un empate con " + maximo + " votos.");
        } else {
            System.out.println(
                    "Ganador: " + ganador + " con " + maximo + " votos.");
        }
    }

    public void calcularAbstencion() {
        if (!recuentoRealizado) {
            System.out.println("Primero hay que realizar el recuento.");
            return;
        }

        String[] alumnos = presentes.convertirAArray();
        int abstenciones = 0;

        for (int i = 0; i < alumnos.length; i++) {
            if (!votantes.contains(alumnos[i])) {
                abstenciones++;
            }
        }

        System.out.println("\n--- ABSTENCIONES ---");
        System.out.println("Alumnos presentes: " + alumnos.length);
        System.out.println("Alumnos que han votado: " + votantes.size());
        System.out.println("Abstenciones: " + abstenciones);
    }
}