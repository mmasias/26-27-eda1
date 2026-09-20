package entregas.moraDaniel;

public class Fila {

    private static final int CAPACIDAD_MAXIMA_DEL_ARRAY = 200;

    private final Persona[] personasEnFila = new Persona[CAPACIDAD_MAXIMA_DEL_ARRAY];
    private int cantidadDePersonasEnFila = 0;

    public int getCantidadDePersonasEnFila() {
        return cantidadDePersonasEnFila;
    }

    public boolean estaVacia() {
        return cantidadDePersonasEnFila == 0;
    }

    public Persona getPersonaEnPosicion(int posicion) {
        return personasEnFila[posicion];
    }

    public void insertarEnPosicion(int posicionDondeInsertar, Persona personaAInsertar) {
        for (int indiceDeDesplazamiento = cantidadDePersonasEnFila; indiceDeDesplazamiento > posicionDondeInsertar; indiceDeDesplazamiento--) {
            personasEnFila[indiceDeDesplazamiento] = personasEnFila[indiceDeDesplazamiento - 1];
        }
        personasEnFila[posicionDondeInsertar] = personaAInsertar;
        cantidadDePersonasEnFila++;
    }

    public void insertarAlFinal(Persona personaAInsertar) {
        insertarEnPosicion(cantidadDePersonasEnFila, personaAInsertar);
    }

    public Persona eliminarEnPosicion(int posicionAEliminar) {
        Persona personaEliminada = personasEnFila[posicionAEliminar];
        for (int indiceDeDesplazamiento = posicionAEliminar; indiceDeDesplazamiento < cantidadDePersonasEnFila
                - 1; indiceDeDesplazamiento++) {
            personasEnFila[indiceDeDesplazamiento] = personasEnFila[indiceDeDesplazamiento + 1];
        }
        personasEnFila[cantidadDePersonasEnFila - 1] = null;
        cantidadDePersonasEnFila--;
        return personaEliminada;
    }

    public int posicionTrasUltimoPreferente() {
        for (int indiceRecorrido = cantidadDePersonasEnFila - 1; indiceRecorrido >= 0; indiceRecorrido--) {
            if (personasEnFila[indiceRecorrido].tienePrioridadPreferente()) {
                return indiceRecorrido + 1;
            }
        }
        return 0;
    }
}