package laFila;

class CentroComercial {
    private final int MINUTOS_POR_HORA = 60;
    private final int HORAS_DEL_RETO_BASE = 4;
    private final int HORAS_DEL_ESCENARIO_COMPLETO = 2;
    private final int MINUTO_DE_INICIO_DE_LAS_REGLAS_NUEVAS = 20;

    private final double PROBABILIDAD_DE_LLEGADA = 0.6;
    private final double PROBABILIDAD_DE_QUE_SE_ABRA_UNA_CAJA = 0.4;
    private final double PROBABILIDAD_DE_LLEGADA_PREFERENTE = 0.2;
    private final double PROBABILIDAD_DE_QUE_ALGUIEN_SE_CUELE = 0.1;
    private final double PROBABILIDAD_DE_QUE_ALGUIEN_ENTREGUE_SUS_COMPRAS = 0.05;

    private final int MINUTOS_ENTRE_PREGUNTAS_DE_ABURRIMIENTO = 5;
    private final int MINUTOS_ENTRE_REVISIONES_DE_LA_FILA = 15;
    private final int LONGITUD_MAXIMA_DE_LA_FILA = 30;
    private final int LONGITUD_PARA_AVISAR_POR_LOS_PARLANTES = 25;
    private final int PERSONAS_QUE_ATIENDE_LA_CAJA_DEL_AVISO = 5;
    private final int PERSONAS_NECESARIAS_PARA_ENTREGAR_COMPRAS = 2;
    private final int METROS_POR_PERSONA = 1;

    private Fila fila;
    private boolean hayReglasNuevas;
    private int personasAtendidas;
    private int personasQueSeAburrieron;
    private int personasQueDesistieron;
    private int personasQueSeColaron;
    private int personasQueEntregaronSusCompras;
    private int avisosPorLosParlantes;

    public CentroComercial() {
        fila = new Fila();
        hayReglasNuevas = false;
        personasAtendidas = 0;
        personasQueSeAburrieron = 0;
        personasQueDesistieron = 0;
        personasQueSeColaron = 0;
        personasQueEntregaronSusCompras = 0;
        avisosPorLosParlantes = 0;
    }

    public void simularRetoBase() {
        System.out.println("===== RETO BASE: " + HORAS_DEL_RETO_BASE + " horas =====");
        int duracionEnMinutos = HORAS_DEL_RETO_BASE * MINUTOS_POR_HORA;
        for (int minuto = 1; minuto <= duracionEnMinutos; minuto = minuto + 1) {
            simularLlegada(minuto);
            simularAperturaDeCaja();
        }
        informarDelCierreDelRetoBase();
    }

    public void simularEscenarioCompleto() {
        System.out.println("===== ESCENARIO COMPLETO: " + HORAS_DEL_ESCENARIO_COMPLETO + " horas, reglas nuevas desde el minuto " + MINUTO_DE_INICIO_DE_LAS_REGLAS_NUEVAS + " =====");
        hayReglasNuevas = true;
        int duracionEnMinutos = HORAS_DEL_ESCENARIO_COMPLETO * MINUTOS_POR_HORA;
        for (int minuto = 1; minuto <= duracionEnMinutos; minuto = minuto + 1) {
            simularLlegada(minuto);
            if (lasReglasNuevasEstanActivas(minuto)) {
                simularReglasNuevas(minuto);
            }
            simularAperturaDeCaja();
            informarDelMinuto(minuto);
        }
        informarDelCierreDelEscenarioCompleto();
    }

    private void simularReglasNuevas(int minuto) {
        if (tocaPreguntarSiSeAburren(minuto)) {
            simularAburrimiento(minuto);
        }
        simularLlegadaPreferente(minuto);
        simularColada(minuto);
        simularEntregaDeCompras();
        if (tocaAvisarPorLosParlantes(minuto)) {
            simularAvisoPorLosParlantes(minuto);
        }
    }

    private void simularLlegada(int minuto) {
        if (sucede(PROBABILIDAD_DE_LLEGADA)) {
            intentarIncorporar(new Persona(minuto), fila.longitud(), minuto);
        }
    }

    private void simularAperturaDeCaja() {
        if (!fila.estaVacia() && sucede(PROBABILIDAD_DE_QUE_SE_ABRA_UNA_CAJA)) {
            atenderAlPrimeroDeLaFila();
        }
    }

    private void simularAburrimiento(int minuto) {
        for (int posicion = fila.longitud() - 1; posicion >= 0; posicion = posicion - 1) {
            if (fila.personaEn(posicion).seAburre(minuto)) {
                fila.retirarDe(posicion);
                personasQueSeAburrieron = personasQueSeAburrieron + 1;
            }
        }
    }

    private void simularLlegadaPreferente(int minuto) {
        if (sucede(PROBABILIDAD_DE_LLEGADA_PREFERENTE)) {
            Persona personaPreferente = new Persona(minuto, circunstanciaPreferenteAlAzar());
            intentarIncorporar(personaPreferente, posicionDetrasDelUltimoPreferente(), minuto);
        }
    }

    private void simularColada(int minuto) {
        if (!fila.estaVacia() && sucede(PROBABILIDAD_DE_QUE_ALGUIEN_SE_CUELE)) {
            int posicionDelConocido = posicionAlAzarEnLaFila();
            int longitudAntesDeColarse = fila.longitud();
            intentarIncorporar(new Persona(minuto), posicionDelConocido + 1, minuto);
            if (fila.longitud() > longitudAntesDeColarse) {
                personasQueSeColaron = personasQueSeColaron + 1;
            }
        }
    }

    private void simularEntregaDeCompras() {
        boolean hayAQuienEntregarle = fila.longitud() >= PERSONAS_NECESARIAS_PARA_ENTREGAR_COMPRAS;
        if (hayAQuienEntregarle && sucede(PROBABILIDAD_DE_QUE_ALGUIEN_ENTREGUE_SUS_COMPRAS)) {
            fila.retirarDe(posicionAlAzarEnLaFila());
            personasQueEntregaronSusCompras = personasQueEntregaronSusCompras + 1;
        }
    }

    private void simularAvisoPorLosParlantes(int minuto) {
        System.out.println("   >> Minuto " + minuto + ": \"Pasen por esta caja en orden de fila\"");
        avisosPorLosParlantes = avisosPorLosParlantes + 1;
        int atendidasEnLaCajaDelAviso = 0;
        while (atendidasEnLaCajaDelAviso < PERSONAS_QUE_ATIENDE_LA_CAJA_DEL_AVISO && !fila.estaVacia()) {
            atenderAlPrimeroDeLaFila();
            atendidasEnLaCajaDelAviso = atendidasEnLaCajaDelAviso + 1;
        }
    }

    private void intentarIncorporar(Persona persona, int posicion, int minuto) {
        if (decideIncorporarse(persona, minuto)) {
            fila.incorporarEn(posicion, persona);
        } else {
            personasQueDesistieron = personasQueDesistieron + 1;
        }
    }

    private boolean decideIncorporarse(Persona persona, int minuto) {
        boolean laFilaEsDemasiadoLarga = lasReglasNuevasEstanActivas(minuto) && fila.longitud() >= LONGITUD_MAXIMA_DE_LA_FILA;
        return !laFilaEsDemasiadoLarga || !persona.desisteAlVerUnaFilaLarga();
    }

    private void atenderAlPrimeroDeLaFila() {
        fila.retirarAlPrimero();
        personasAtendidas = personasAtendidas + 1;
    }

    private int posicionDetrasDelUltimoPreferente() {
        int posicionDetrasDelUltimo = 0;
        for (int posicion = 0; posicion < fila.longitud(); posicion = posicion + 1) {
            if (tieneDerechoPreferente(fila.personaEn(posicion))) {
                posicionDetrasDelUltimo = posicion + 1;
            }
        }
        return posicionDetrasDelUltimo;
    }

    private boolean tieneDerechoPreferente(Persona persona) {
        return persona.getCircunstancia() != Circunstancia.NINGUNA;
    }

    private Circunstancia circunstanciaPreferenteAlAzar() {
        Circunstancia[] circunstanciasPreferentes = { Circunstancia.EMBARAZO, Circunstancia.TERCERA_EDAD, Circunstancia.DISCAPACIDAD };
        return circunstanciasPreferentes[(int) (Math.random() * circunstanciasPreferentes.length)];
    }

    private int posicionAlAzarEnLaFila() {
        return (int) (Math.random() * fila.longitud());
    }

    private boolean lasReglasNuevasEstanActivas(int minuto) {
        return hayReglasNuevas && minuto >= MINUTO_DE_INICIO_DE_LAS_REGLAS_NUEVAS;
    }

    private boolean tocaPreguntarSiSeAburren(int minuto) {
        return (minuto - MINUTO_DE_INICIO_DE_LAS_REGLAS_NUEVAS) % MINUTOS_ENTRE_PREGUNTAS_DE_ABURRIMIENTO == 0;
    }

    private boolean tocaAvisarPorLosParlantes(int minuto) {
        boolean esMinutoDeRevision = minuto % MINUTOS_ENTRE_REVISIONES_DE_LA_FILA == 0;
        return esMinutoDeRevision && fila.longitud() > LONGITUD_PARA_AVISAR_POR_LOS_PARLANTES;
    }

    private boolean sucede(double probabilidad) {
        return Math.random() < probabilidad;
    }

    private void informarDelMinuto(int minuto) {
        final String PERSONA = "o";
        int metrosDeFila = fila.longitud() * METROS_POR_PERSONA;
        System.out.println(String.format("Minuto %3d | %2d m | ", minuto, metrosDeFila) + PERSONA.repeat(fila.longitud()));
    }

    private void informarDelCierreDelRetoBase() {
        System.out.println("Personas atendidas: " + personasAtendidas);
        System.out.println("Personas que quedaron en la fila: " + fila.longitud());
        System.out.println();
    }

    private void informarDelCierreDelEscenarioCompleto() {
        System.out.println("----- Al cierre -----");
        System.out.println("Personas atendidas: " + personasAtendidas);
        System.out.println("Personas que quedaron en la fila: " + fila.longitud() + " (" + fila.longitud() * METROS_POR_PERSONA + " m)");
        System.out.println("Personas que se aburrieron y se fueron: " + personasQueSeAburrieron);
        System.out.println("Personas que desistieron al ver la fila tan larga: " + personasQueDesistieron);
        System.out.println("Personas que se colaron: " + personasQueSeColaron);
        System.out.println("Personas que entregaron sus compras a otra: " + personasQueEntregaronSusCompras);
        System.out.println("Avisos por los parlantes: " + avisosPorLosParlantes);
    }
}
