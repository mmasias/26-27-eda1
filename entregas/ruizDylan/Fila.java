public class Fila {
    private static final int LIMITE_DESISTIR = 30;
    private static final int LIMITE_MEGAFONO = 25;
    private static final double PROBABILIDAD_DESISTIR = 0.50;
    private static final double PROBABILIDAD_ABURRIRSE = 0.30;
    private static final double PROBABILIDAD_ENTREGA = 0.10;
    private static final int TIEMPO_MAXIMO_ESPERA = 8;
    private static final int MAX_PERSONAS_CAJA_EXTRA = 5;

    
    private class Nodo {
        Persona persona;
        Nodo siguiente;

        Nodo(Persona persona) {
            this.persona = persona;
            siguiente = null;
        }
    }

    private Nodo cabeza;
    private int tamano;

    public Fila() {
        cabeza = null;
        tamano = 0;
    }

    public int getTamano() {
        return tamano;
    }

    public boolean estaVacia() {
        return tamano == 0;
    }

    public boolean agregarPersona(Persona nuevaPersona) {
        if (tamano >= LIMITE_DESISTIR) {
            if (Math.random() < PROBABILIDAD_DESISTIR) {
                System.out.println("Una persona vio la fila muy larga (>30m) y desidio no entrar");
                return false;
            }
        }

        if (nuevaPersona.tieneDerechoPreferente()) {
            insertarPreferente(nuevaPersona);
        } else if (nuevaPersona.getConocidoEnFila() != null && contiene(nuevaPersona.getConocidoEnFila())) {
            insertarDetrasDeConocido(nuevaPersona);
        } else {
            insertarAlFinal(nuevaPersona);
            System.out.println("Llegada normal a la fila");
        }
        return true;
    }

    private void insertarAlFinal(Persona nuevaPersona) {
        Nodo nuevoNodo = new Nodo(nuevaPersona);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamano++;
    }

    private void insertarPreferente(Persona nuevaPersona) {
        Nodo nuevoNodo = new Nodo(nuevaPersona);
        if (cabeza == null || !cabeza.persona.tieneDerechoPreferente()) {
            nuevoNodo.siguiente = cabeza;
            cabeza = nuevoNodo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null && actual.siguiente.persona.tieneDerechoPreferente()) {
                actual = actual.siguiente;
            }
            nuevoNodo.siguiente = actual.siguiente;
            actual.siguiente = nuevoNodo;
        }
        tamano++;
        System.out.println("Atencion Preferente: Incorporado tras el ultimo preferente");
    }

    private void insertarDetrasDeConocido(Persona nuevaPersona) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.persona == nuevaPersona.getConocidoEnFila()) {
                Nodo nuevoNodo = new Nodo(nuevaPersona);
                nuevoNodo.siguiente = actual.siguiente;
                actual.siguiente = nuevoNodo;
                tamano++;
                System.out.println("Colado ilicito: Se colo detras de su conocido.");
                return;
            }
            actual = actual.siguiente;
        }
        insertarAlFinal(nuevaPersona);
    }

    public Persona atender() {
        if (estaVacia()) {
            return null; 
        }
        Persona personaAtendida = cabeza.persona;
        cabeza = cabeza.siguiente;
        tamano--;
        return personaAtendida;
    }

    public int procesarAburrimiento(int minutoActual) {
        int personasIdas = 0;
        Nodo actual = cabeza;
        Nodo anterior = null;

        while (actual != null) {
            if ((minutoActual - actual.persona.getMinutoLlegada()) > TIEMPO_MAXIMO_ESPERA) {
                if (Math.random() < PROBABILIDAD_ABURRIRSE) {
                    System.out.println("ABURRIMIENTO! Una persona abandono la fila.");
                    if (anterior == null) {
                        cabeza = actual.siguiente;
                    } else {
                        anterior.siguiente = actual.siguiente;
                    }
                    tamano--;
                    personasIdas++;
                    actual = (anterior == null) ? cabeza : anterior.siguiente;
                    continue;
                }
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        return personasIdas;
    }

    public void simularEntregaCompras() {
        if (tamano > 1 && Math.random() < PROBABILIDAD_ENTREGA) {
            int indice = (int) (Math.random() * tamano);
            eliminarEnIndice(indice);
            System.out.println("Entrega de compras: Alguien entrego sus cosas a otra persona y salio de la fila.");
        }
    }

    public int procesarAvisoCajaExtra() {
        int atendidosExtra = 0;
        if (tamano > LIMITE_MEGAFONO) {
            System.out.println("\n [MEGAFONO CCCF]: \"Pasen por esta caja en orden de fila\"");
            int limite = Math.min(MAX_PERSONAS_CAJA_EXTRA, tamano);
            for (int i = 0; i < limite; i++) {
                if (!estaVacia()) {
                    atender();
                    atendidosExtra++;
                }
            }
        }
        return atendidosExtra;
    }

    public Persona obtenerPersonaAleatoria() {
        if (estaVacia()) {
            return null;
        }
        int indice = (int) (Math.random() * tamano);
        Nodo actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual.persona;
    }

    private boolean contiene(Persona p) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.persona == p) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    private void eliminarEnIndice(int indice) {
        if (indice == 0) {
            cabeza = cabeza.siguiente;
        } else {
            Nodo actual = cabeza;
            for (int i = 0; i < indice - 1; i++) {
                actual = actual.siguiente;
            }
            actual.siguiente = actual.siguiente.siguiente;
        }
        tamano--;
    }

    public void mostrarEstadoFila() {
        StringBuilder sb = new StringBuilder();
        sb.append("   Fila (").append(tamano).append(" m) : [CAJA 1] <-- ");
        if (estaVacia()) {
            sb.append("(Fila vacia)");
        } else {
            Nodo actual = cabeza;
            while (actual != null) {
                if (actual.persona.tieneDerechoPreferente()) {
                    sb.append("[P] ");
                } else if (actual.persona.getConocidoEnFila() != null) {
                    sb.append("[C] ");
                } else {
                    sb.append("[N] ");
                }
                actual = actual.siguiente;
            }
        }
        System.out.println(sb.toString());
    }
}
