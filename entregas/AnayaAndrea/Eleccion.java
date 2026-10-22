public class Eleccion {
    private enum Fase { PLAZO_ABIERTO, PLAZO_CERRADO, VOTACION_ABIERTA, VOTACION_CERRADA }

    private Fase fase = Fase.PLAZO_ABIERTO;
    private Lista<String> presentes = new Lista<String>();
    private Lista<String> votantes = new Lista<String>();
    private Lista<String> candidatosPlazo = new Lista<String>();
    private Pizarra pizarra;
    private Pila<String> urna = new Pila<String>();
    private String secretario;

    public void llega(String nombre) {
        if (presentes.contiene(nombre)) {
            System.out.println("  [!] " + nombre + " ya estaba presente");
            return;
        }
        presentes.agregar(nombre);
        if (fase == Fase.VOTACION_ABIERTA) {
            System.out.println("  " + nombre + " llega TARDE (la votacion ya esta abierta)");
        } else {
            System.out.println("  " + nombre + " llega");
        }
    }

    public void seMarcha(String nombre) {
        if (presentes.eliminar(nombre)) {
            System.out.println("  " + nombre + " se MARCHA (sale de la lista de presentes)");
        } else {
            System.out.println("  [!] " + nombre + " no estaba presente");
        }
    }

    public void proponerCandidato(String nombre) {
        if (fase != Fase.PLAZO_ABIERTO) {
            System.out.println("  Propuesta de " + nombre + " RECHAZADA: el plazo esta cerrado");
        } else if (!presentes.contiene(nombre)) {
            System.out.println("  Propuesta de " + nombre + " RECHAZADA: no esta presente");
        } else if (candidatosPlazo.contiene(nombre)) {
            System.out.println("  Propuesta de " + nombre + " RECHAZADA: ya estaba propuesto");
        } else {
            candidatosPlazo.agregar(nombre);
            System.out.println("  " + nombre + " propuesto como candidato");
        }
    }

    public void retirarCandidato(String nombre) {
        if (fase != Fase.PLAZO_ABIERTO) {
            System.out.println("  [!] " + nombre + " no puede retirarse: los candidatos ya estan fijados");
        } else if (candidatosPlazo.eliminar(nombre)) {
            System.out.println("  " + nombre + " se RETIRA (baja en cualquier posicion de la lista)");
        } else {
            System.out.println("  [!] " + nombre + " no era candidato");
        }
    }

    // COSTE O(c): copia unica de la lista al array de la pizarra
    public void cerrarPlazo() {
        fase = Fase.PLAZO_CERRADO;
        String[] nombres = new String[candidatosPlazo.tamano()];
        Nodo<String> actual = candidatosPlazo.getCabeza();
        int i = 0;
        while (actual != null) {
            nombres[i] = actual.getDato();
            i++;
            actual = actual.getSiguiente();
        }
        pizarra = new Pizarra(nombres);
        System.out.println("  Plazo CERRADO: " + nombres.length + " candidatos copiados al array de la pizarra"
                + " (+ columnas En blanco y Nulo)");
    }

    public void elegirSecretario(String nombre) {
        if (fase != Fase.PLAZO_CERRADO) {
            System.out.println("  [!] El secretario se elige despues de cerrar el plazo y antes de votar");
        } else if (!presentes.contiene(nombre)) {
            System.out.println("  Secretario " + nombre + " RECHAZADO: no esta presente");
        } else {
            secretario = nombre;
            System.out.println("  El profesor elige secretario a " + nombre);
        }
    }

    public void abrirVotacion() {
        if (fase != Fase.PLAZO_CERRADO || secretario == null) {
            System.out.println("  [!] Antes de votar hay que cerrar el plazo y elegir secretario");
            return;
        }
        fase = Fase.VOTACION_ABIERTA;
        System.out.println("  Votacion ABIERTA");
    }

    public void cerrarVotacion() {
        fase = Fase.VOTACION_CERRADA;
        System.out.println("  Votacion CERRADA");
    }

    public void votar(String nombre, String contenido) {
        if (fase != Fase.VOTACION_ABIERTA) {
            System.out.println("  [!] " + nombre + " intenta votar con la votacion cerrada");
        } else if (!presentes.contiene(nombre)) {
            System.out.println("  VOTO RECHAZADO: " + nombre + " no esta presente");
        } else if (votantes.contiene(nombre)) {
            System.out.println("  VOTO RECHAZADO: " + nombre + " ya habia votado");
        } else {
            votantes.agregar(nombre);
            urna.apilar(contenido == null ? "" : contenido.trim());
            System.out.println("  " + nombre + " deja su papeleta sobre el monton (monton: " + urna.tamano() + ")");
        }
    }

    public void informarAbstencion() {
        int antes = votantes.comparaciones();
        int abstenciones = 0;
        Nodo<String> actual = presentes.getCabeza();
        while (actual != null) {
            if (!votantes.contiene(actual.getDato())) {
                abstenciones++;
                System.out.println("  Presente que no entrego papeleta: " + actual.getDato());
            }
            actual = actual.getSiguiente();
        }
        System.out.println("  ABSTENCION: " + abstenciones + " de " + presentes.tamano() + " presentes");
        System.out.println("  Comparaciones en 'votantes': " + (votantes.comparaciones() - antes));
        System.out.println("  Intento por papeletas: presentes (" + presentes.tamano() + ") - papeletas ("
                + urna.tamano() + ") = " + (presentes.tamano() - urna.tamano()) + "  -> NO coincide");
    }

    private boolean puedeLeer() {
        if (fase != Fase.VOTACION_CERRADA) {
            System.out.println("  [!] El recuento empieza al cerrar la votacion");
            return false;
        }
        if (urna.estaVacia()) {
            System.out.println("  [!] No quedan papeletas en el monton");
            return false;
        }
        return true;
    }

    private String cantar(String voz) {
        String dicho = voz.length() == 0 ? "(sin nombre)" : voz;
        System.out.println("  " + secretario + " canta: " + dicho);
        return voz;
    }

    private int columnaDe(String voz) {
        if (voz.length() == 0) {
            return pizarra.columnaBlanco();
        }
        int posicion = pizarra.posicionDeCandidato(voz);
        if (posicion < 0) {
            return pizarra.columnaNulo();
        }
        return posicion;
    }

    public void leerSiguiente() {
        if (!puedeLeer()) {
            return;
        }
        String voz = cantar(urna.desapilar());
        int columna = columnaDe(voz);
        pizarra.anadirPalito(columna);
        System.out.println("  Pizarra: palito en '" + pizarra.nombre(columna) + "'");
    }

    public void leerSiguienteAnotandoMal(String columnaErronea) {
        if (!puedeLeer()) {
            return;
        }
        String voz = cantar(urna.desapilar());
        int columna = pizarra.posicionDeColumna(columnaErronea);
        pizarra.anadirPalito(columna);
        System.out.println("  Pizarra: palito en '" + pizarra.nombre(columna)
                + "'  <- ERROR del presidente (simulado)");
    }

    public void corregirAnotacion(String columnaErronea, String columnaCorrecta) {
        int mal = pizarra.posicionDeColumna(columnaErronea);
        int bien = pizarra.posicionDeColumna(columnaCorrecta);
        if (mal < 0 || bien < 0) {
            System.out.println("  [!] Columna inexistente");
            return;
        }
        pizarra.borrarPalito(mal);
        pizarra.anadirPalito(bien);
        System.out.println("  CORRECCION: borra un palito de '" + columnaErronea + "' y lo anota en '"
                + columnaCorrecta + "'");
    }

    public void mostrarPizarra() {
        System.out.print("  Pizarra:");
        for (int i = 0; i < pizarra.numColumnas(); i++) {
            System.out.print(" [" + pizarra.nombre(i) + ": " + pizarra.votos(i) + "]");
        }
        System.out.println();
    }

    public void proclamar() {
        if (fase != Fase.VOTACION_CERRADA || !urna.estaVacia()) {
            System.out.println("  [!] No se puede proclamar: quedan papeletas por leer o la votacion sigue abierta");
            return;
        }
        mostrarPizarra();
        int max = -1;
        for (int i = 0; i < pizarra.numCandidatos(); i++) {
            if (pizarra.votos(i) > max) {
                max = pizarra.votos(i);
            }
        }
        int empatados = 0;
        for (int i = 0; i < pizarra.numCandidatos(); i++) {
            if (pizarra.votos(i) == max) {
                empatados++;
            }
        }
        if (pizarra.numCandidatos() == 0) {
            System.out.println("  Sin candidatos: no hay proclamacion");
        } else if (empatados == 1) {
            for (int i = 0; i < pizarra.numCandidatos(); i++) {
                if (pizarra.votos(i) == max) {
                    System.out.println("  PROCLAMADO delegado: " + pizarra.nombre(i) + " con " + max + " votos");
                }
            }
        } else {
            System.out.print("  EMPATE a " + max + " votos entre:");
            for (int i = 0; i < pizarra.numCandidatos(); i++) {
                if (pizarra.votos(i) == max) {
                    System.out.print(" " + pizarra.nombre(i));
                }
            }
            System.out.println();
            System.out.println("  (el enunciado no da criterio de desempate: se informa, no se resuelve)");
        }
    }

    public void informeCostes() {
        System.out.println("  Comparaciones -> presentes: " + presentes.comparaciones()
                + " | votantes: " + votantes.comparaciones()
                + " | candidatos en plazo: " + candidatosPlazo.comparaciones()
                + " | pizarra (busqueda por nombre): " + pizarra.comparaciones());
        System.out.println("  Papeletas que quedan en el monton: " + urna.tamano()
                + " (leer es destructivo: no se pueden revisar)");
    }
}