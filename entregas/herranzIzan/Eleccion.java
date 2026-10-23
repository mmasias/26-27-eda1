
public class Eleccion {

    private ListaEnlazada candidaturas;
    private String[] boleta;
    private ListaEnlazada censo;
    private ListaEnlazada presentes;
    private ListaEnlazada votantesRegistrados;
    private Cola colaEntrega;
    private Pila urna;
    private int[] marcador;

    private boolean boletaCerrada;
    private boolean urnaCerrada;
    private boolean escrutinioIniciado;
    private int votosAceptados;
    private int votosContados;

    private String errorAnotado;
    private String errorReal;

    public Eleccion() {
        candidaturas = new ListaEnlazada();
        censo = new ListaEnlazada();
        presentes = new ListaEnlazada();
        votantesRegistrados = new ListaEnlazada();
        colaEntrega = new Cola();
        urna = new Pila();

        boletaCerrada = false;
        urnaCerrada = false;
        escrutinioIniciado = false;
        votosAceptados = 0;
        votosContados = 0;
    }

    public void proponerCandidatura(String nombre) {
        if (boletaCerrada || nombre == null ||
            nombre.isEmpty() || candidaturas.contiene(nombre)) {
            System.out.println("Candidatura no admitida: " + nombre);
            return;
        }

        candidaturas.agregarFinal(nombre);
    }

    public void retirarCandidatura(String nombre) {
        if (!boletaCerrada && candidaturas.eliminar(nombre)) {
            System.out.println("Candidatura retirada: " + nombre);
        } else {
            System.out.println("No se puede retirar: " + nombre);
        }
    }

    public void rechazarCandidatura(String nombre) {
        if (!boletaCerrada && candidaturas.eliminar(nombre)) {
            System.out.println("Candidatura rechazada: " + nombre);
        } else {
            System.out.println("No se puede rechazar: " + nombre);
        }
    }

    public void cerrarBoleta() {
        if (boletaCerrada || candidaturas.estaVacia()) {
            throw new IllegalStateException(
                "No se puede cerrar la boleta."
            );
        }

        boleta = new String[candidaturas.tamano()];

        for (int i = 0; i < boleta.length; i++) {
            boleta[i] = candidaturas.obtener(i);
        }

        marcador = new int[boleta.length + 2];
        boletaCerrada = true;
        System.out.println("Boleta definitiva cerrada.");
    }

    public void inscribirAlumno(Alumno alumno) {
        if (boletaCerrada) {
            System.out.println("El censo ya esta cerrado.");
            return;
        }

        String dni = alumno.getDni();

        if (!censo.contiene(dni)) {
            censo.agregarFinal(dni);
        }
    }

    public void registrarPresencia(Alumno alumno) {
        String dni = alumno.getDni();

        if (!urnaCerrada && censo.contiene(dni)
                && !presentes.contiene(dni)) {
            presentes.agregarFinal(dni);
            System.out.println(alumno.getNombre() + " esta presente.");
        } else {
            System.out.println("No se puede registrar a "
                    + alumno.getNombre());
        }
    }

    public void alumnoLlegaTarde(Alumno alumno) {
        System.out.println(alumno.getNombre() + " llega tarde.");
        registrarPresencia(alumno);
    }

    public void alumnoSeMarcha(Alumno alumno) {
        if (!urnaCerrada && presentes.eliminar(alumno.getDni())) {
            System.out.println(alumno.getNombre() + " se marcha.");
        } else {
            System.out.println("No se puede registrar la salida.");
        }
    }

    public void ponerseEnCola(Alumno alumno) {
        if (!boletaCerrada || urnaCerrada) {
            System.out.println("La votacion no esta abierta.");
            return;
        }

        colaEntrega.encolar(alumno.getDni());
        System.out.println(alumno.getNombre() + " entra en la cola.");
    }

    public void atenderSiguiente(String opcion) {
        if (!boletaCerrada || urnaCerrada || colaEntrega.estaVacia()) {
            System.out.println("No se puede atender al votante.");
            return;
        }

        String dni = colaEntrega.desencolar();

        if (!censo.contiene(dni)) {
            System.out.println("RECHAZADO: no pertenece al censo.");
            return;
        }

        if (!presentes.contiene(dni)) {
            System.out.println("RECHAZADO: no esta presente.");
            return;
        }

        if (votantesRegistrados.contiene(dni)) {
            System.out.println("RECHAZADO: intento de doble voto.");
            return;
        }

        if (votosAceptados >= censo.tamano()) {
            System.out.println("RECHAZADO: limite de votos alcanzado.");
            return;
        }

        if (opcion == null) {
            System.out.println("RECHAZADO: papeleta inexistente.");
            return;
        }

        Papeleta papeleta = new Papeleta(opcion);

        urna.apilar(papeleta.getOpcion());
        votantesRegistrados.agregarFinal(dni);
        votosAceptados++;

        System.out.println("Papeleta depositada correctamente.");
    }

    public void cerrarUrna() {
        if (!boletaCerrada || urnaCerrada || !colaEntrega.estaVacia()) {
            System.out.println("No se puede cerrar la urna.");
            return;
        }

        urnaCerrada = true;

        if (urna.tamano() != votosAceptados ||
            votosAceptados != votantesRegistrados.tamano() ||
            votosAceptados > censo.tamano()) {
            throw new IllegalStateException(
                "IRREGULARIDAD: numero de papeletas incorrecto."
            );
        }

        System.out.println("Urna cerrada y verificada.");
    }

    private int indiceDe(String opcion) {
        if (opcion == null) {
            return boleta.length + 1;
        }

        if (opcion.equals("BLANCO")) {
            return boleta.length;
        }

        if (opcion.equals("NULO")) {
            return boleta.length + 1;
        }

        for (int i = 0; i < boleta.length; i++) {
            if (boleta[i].equals(opcion)) {
                return i;
            }
        }

        return boleta.length + 1;
    }

    private String normalizarOpcion(String opcion) {
        int indice = indiceDe(opcion);

        if (indice == boleta.length) {
            return "BLANCO";
        }

        if (indice == boleta.length + 1) {
            return "NULO";
        }

        return boleta[indice];
    }

    public void iniciarEscrutinio() {
        if (!urnaCerrada || escrutinioIniciado) {
            throw new IllegalStateException(
                "No se puede iniciar el escrutinio."
            );
        }

        escrutinioIniciado = true;
        System.out.println("Comienza el recuento de papeletas.");
    }

    public void contarSiguiente(String anotacion) {
        if (!escrutinioIniciado || urna.estaVacia()
                || errorAnotado != null) {
            throw new IllegalStateException(
                "No se puede contar otra papeleta."
            );
        }

        String opcionReal = normalizarOpcion(urna.desapilar());
        String opcionAnotada = normalizarOpcion(anotacion);

        marcador[indiceDe(opcionAnotada)]++;
        votosContados++;

        System.out.println("\nSecretario anota: " + opcionAnotada);

        imprimirMarcador();

        if (!opcionReal.equals(opcionAnotada)) {
            errorAnotado = opcionAnotada;
            errorReal = opcionReal;
            System.out.println(
                "ERROR: anotacion pendiente de corregir."
            );
        }
    }

    public void corregirAnotacion() {
        if (errorAnotado == null) {
            System.out.println("No hay errores pendientes.");
            return;
        }

        marcador[indiceDe(errorAnotado)]--;
        marcador[indiceDe(errorReal)]++;

        System.out.println("\nCORRECCION: " + errorAnotado
                + " -> " + errorReal);

        errorAnotado = null;
        errorReal = null;

        imprimirMarcador();
    }

    public void imprimirMarcador() {
        System.out.println("--- PIZARRA ELECTORAL ---");

        for (int i = 0; i < marcador.length; i++) {

            String nombre;

            if (i < boleta.length) {
                nombre = boleta[i];
            } else if (i == boleta.length) {
                nombre = "BLANCO";
            } else {
                nombre = "NULO";
            }

            System.out.print(nombre + ": ");

            for (int j = 0; j < marcador[i]; j++) {
                System.out.print("| ");
            }

            System.out.println("(" + marcador[i] + " votos)");
        }
    }

    public void proclamar() {
        if (!escrutinioIniciado || !urna.estaVacia() ||
            votosContados != votosAceptados || errorAnotado != null) {
            throw new IllegalStateException(
                "El escrutinio no ha terminado correctamente."
            );
        }

        if (votosContados == 0) {
            System.out.println("No se han emitido votos.");
            return;
        }

        int maximo = 0;
        int empatados = 0;
        String ganador = "";

        for (int i = 0; i < boleta.length; i++) {
            if (marcador[i] > maximo) {
                maximo = marcador[i];
                ganador = boleta[i];
                empatados = 1;
            } else if (marcador[i] == maximo && maximo > 0) {
                empatados++;
            }
        }

        System.out.println("\n=== PROCLAMACION ===");

        imprimirMarcador();

        System.out.println("Votos emitidos: " + votosContados);
        System.out.println("Abstenciones del censo: "
                + (censo.tamano() - votosAceptados));

        if (maximo == 0) {
            System.out.println(
                "No hay votos validos para candidaturas."
            );
        } else if (empatados > 1) {
            System.out.println("EMPATE entre:");

            for (int i = 0; i < boleta.length; i++) {
                if (marcador[i] == maximo) {
                    System.out.println("- " + boleta[i]);
                }
            }
        } else {
            System.out.println("Ganador: " + ganador
                    + " con " + maximo + " votos.");
        }
    }

}

