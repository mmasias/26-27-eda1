package reto4;

public class Elecciones {

    static String opcion_blanco = "(en blanco)";
    static String opcion_nulo = "(nulo)";
    static int opciones_fijas = 2;
    static int lectura_con_error = 2;
    static String columna_equivocada = "Carlos";

    static class Nodo {

        String dato;
        Nodo siguiente;

        public Nodo(String dato) {
            this.dato = dato;
        }
    }

    static class Lista {

        Nodo primero;
        int tamaño;
        int comparaciones;

        public void agregar(String dato) {
            Nodo nuevo = new Nodo(dato);
            if (primero == null) {
                primero = nuevo;
            } else {
                Nodo actual = primero;
                while (actual.siguiente != null) {
                    actual = actual.siguiente;
                }
                actual.siguiente = nuevo;
            }
            tamaño++;
        }

        public boolean contiene(String dato) {
            Nodo actual = primero;
            while (actual != null) {
                comparaciones++;
                if (actual.dato.equals(dato)) {
                    return true;
                }
                actual = actual.siguiente;
            }
            return false;
        }

        public boolean eliminar(String dato) {
            Nodo anterior = null;
            Nodo actual = primero;
            while (actual != null) {
                comparaciones++;
                if (actual.dato.equals(dato)) {
                    if (anterior == null) {
                        primero = actual.siguiente;
                    } else {
                        anterior.siguiente = actual.siguiente;
                    }
                    tamaño--;
                    return true;
                }
                anterior = actual;
                actual = actual.siguiente;
            }
            return false;
        }

        public String obtener(int posicion) {
            Nodo actual = primero;
            for (int i = 0; i < posicion; i++) {
                actual = actual.siguiente;
            }
            return actual.dato;
        }

        public String[] aArray() {
            String[] copia = new String[tamaño];
            Nodo actual = primero;
            for (int i = 0; i < tamaño; i++) {
                copia[i] = actual.dato;
                actual = actual.siguiente;
            }
            return copia;
        }
    }

    static class Cola {

        Nodo primero;
        Nodo ultimo;
        int tamaño;

        public void encolar(String dato) {
            Nodo nuevo = new Nodo(dato);
            if (ultimo == null) {
                primero = nuevo;
            } else {
                ultimo.siguiente = nuevo;
            }
            ultimo = nuevo;
            tamaño++;
        }

        public String desencolar() {
            if (primero == null) {
                return null;
            }
            String dato = primero.dato;
            primero = primero.siguiente;
            if (primero == null) {
                ultimo = null;
            }
            tamaño--;
            return dato;
        }

        public boolean estaVacia() {
            return primero == null;
        }
    }

    static class Pizarra {

        String[] nombres;
        int[] votos;
        int cantidadCandidatos;
        int comparaciones;

        public Pizarra(String[] candidatos) {
            cantidadCandidatos = candidatos.length;
            nombres = new String[cantidadCandidatos + opciones_fijas];
            votos = new int[cantidadCandidatos + opciones_fijas];
            for (int i = 0; i < cantidadCandidatos; i++) {
                nombres[i] = candidatos[i];
            }
            nombres[cantidadCandidatos] = opcion_blanco;
            nombres[cantidadCandidatos + 1] = opcion_nulo;
        }

        private int indiceDe(String opcion, int limite) {
            for (int i = 0; i < limite; i++) {
                comparaciones++;
                if (nombres[i].equals(opcion)) {
                    return i;
                }
            }
            return -1;
        }

        public String clasificar(String papeleta) {
            if (papeleta.isEmpty()) {
                return opcion_blanco;
            }
            if (indiceDe(papeleta, cantidadCandidatos) >= 0) {
                return papeleta;
            }
            return opcion_nulo;
        }

        public void anotar(String opcion) {
            votos[indiceDe(opcion, nombres.length)]++;
        }

        public void borrar(String opcion) {
            int indice = indiceDe(opcion, nombres.length);
            if (votos[indice] > 0) {
                votos[indice]--;
            }
        }

        public void mostrar() {
            for (int i = 0; i < nombres.length; i++) {
                String palitos = "";
                for (int j = 0; j < votos[i]; j++) {
                    palitos += "|";
                }
                System.out.println(nombres[i] + ": " + palitos + " (" + votos[i] + ")");
            }
        }

        public String proclamar() {
            int maximo = -1;
            for (int i = 0; i < cantidadCandidatos; i++) {
                if (votos[i] > maximo) {
                    maximo = votos[i];
                }
            }
            int empatados = 0;
            String lista = "";
            for (int i = 0; i < cantidadCandidatos; i++) {
                if (votos[i] == maximo) {
                    empatados++;
                    lista += lista.isEmpty() ? nombres[i] : " y " + nombres[i];
                }
            }
            if (empatados > 1) {
                return "Empate a " + maximo + " votos entre " + lista + ": no se proclama ganador";
            }
            return "Delegado: " + lista + " con " + maximo + " votos";
        }
    }

    public static void main(String[] args) {
        System.out.println("Proceso electoral:");
        simularProceso();
    }

    private static void simularProceso() {
        Lista presentes = new Lista();
        Lista yaVotaron = new Lista();
        Lista candidaturas = new Lista();
        Cola papeletas = new Cola();

        System.out.println("\nAlumnos en el aula:");
        String[] alumnos = {"Ana", "Luis", "Marta", "Pedro", "Sofía", "Elena", "Diego"};
        for (int i = 0; i < alumnos.length; i++) {
            presentes.agregar(alumnos[i]);
        }
        System.out.println("Presentes: " + presentes.tamaño);
        llegarTarde(presentes, "Carlos");
        marcharse(presentes, "Pedro");

        System.out.println("\nPlazo de candidaturas abierto:");
        proponerCandidatura(presentes, candidaturas, "Ana");
        proponerCandidatura(presentes, candidaturas, "Luis");
        proponerCandidatura(presentes, candidaturas, "Marta");
        proponerCandidatura(presentes, candidaturas, "Pedro");
        retirarCandidatura(candidaturas, "Marta");
        proponerCandidatura(presentes, candidaturas, "Carlos");

        System.out.println("\nCierre del plazo:");
        String[] candidatos = candidaturas.aArray();
        System.out.println("Elementos copiados de la lista al array: " + candidatos.length);
        Pizarra pizarra = new Pizarra(candidatos);

        System.out.println("\nVotación:");
        votar(presentes, yaVotaron, papeletas, "Pedro", "Ana");
        votar(presentes, yaVotaron, papeletas, "Ana", "Ana");
        votar(presentes, yaVotaron, papeletas, "Ana", "Luis");
        votar(presentes, yaVotaron, papeletas, "Luis", "Luis");
        votar(presentes, yaVotaron, papeletas, "Marta", "");
        votar(presentes, yaVotaron, papeletas, "Sofía", "Zoe");
        votar(presentes, yaVotaron, papeletas, "Elena", "Luis");
        marcharse(presentes, "Elena");
        votar(presentes, yaVotaron, papeletas, "Carlos", "Ana");
        System.out.println("Papeletas en la cola: " + papeletas.tamaño);

        System.out.println("\nLectura y recuento:");
        leerPapeletas(papeletas, pizarra);
        System.out.println("Pizarra final:");
        pizarra.mostrar();

        System.out.println("\nAbstención:");
        informarAbstencion(presentes, yaVotaron);

        System.out.println("\nProclamación:");
        proclamar(pizarra);
    }

    private static void llegarTarde(Lista presentes, String nombre) {
        presentes.agregar(nombre);
        System.out.println(nombre + " llega tarde. Presentes: " + presentes.tamaño);
    }

    private static void marcharse(Lista presentes, String nombre) {
        presentes.eliminar(nombre);
        System.out.println(nombre + " se marcha. Presentes: " + presentes.tamaño);
    }

    private static void proponerCandidatura(Lista presentes, Lista candidaturas, String nombre) {
        presentes.comparaciones = 0;
        if (!presentes.contiene(nombre)) {
            System.out.println("Candidatura de " + nombre + " rechazada: no está presente ("
                    + presentes.comparaciones + " comparaciones)");
            return;
        }
        candidaturas.agregar(nombre);
        System.out.println("Candidatura de " + nombre + " admitida ("
                + presentes.comparaciones + " comparaciones)");
    }

    private static void retirarCandidatura(Lista candidaturas, String nombre) {
        candidaturas.eliminar(nombre);
        System.out.println("Candidatura de " + nombre + " retirada");
    }

    private static void votar(Lista presentes, Lista yaVotaron, Cola papeletas, String nombre, String contenido) {
        presentes.comparaciones = 0;
        if (!presentes.contiene(nombre)) {
            System.out.println(nombre + " intenta votar: rechazado, no está presente ("
                    + presentes.comparaciones + " comparaciones)");
            return;
        }
        yaVotaron.comparaciones = 0;
        if (yaVotaron.contiene(nombre)) {
            System.out.println(nombre + " intenta votar otra vez: rechazado, ya entregó papeleta ("
                    + yaVotaron.comparaciones + " comparaciones)");
            return;
        }
        yaVotaron.agregar(nombre);
        papeletas.encolar(contenido);
        System.out.println(nombre + " entrega su papeleta");
    }

    private static void leerPapeletas(Cola papeletas, Pizarra pizarra) {
        int leidas = 0;
        while (!papeletas.estaVacia()) {
            String papeleta = papeletas.desencolar();
            leidas++;
            pizarra.comparaciones = 0;
            String opcion = pizarra.clasificar(papeleta);
            System.out.println("El secretario canta \"" + papeleta + "\": " + opcion
                    + " (" + pizarra.comparaciones + " comparaciones para clasificar)");
            if (leidas == lectura_con_error) {
                pizarra.anotar(columna_equivocada);
                System.out.println("El presidente se equivoca y anota el palito en " + columna_equivocada);
                pizarra.comparaciones = 0;
                pizarra.borrar(columna_equivocada);
                System.out.println("Borra el palito (" + pizarra.comparaciones
                        + " comparaciones para localizar la columna)");
            }
            pizarra.anotar(opcion);
            System.out.println("Papeletas que quedan en la cola: " + papeletas.tamaño);
        }
    }

    private static void informarAbstencion(Lista presentes, Lista yaVotaron) {
        int abstenciones = 0;
        for (int i = 0; i < presentes.tamaño; i++) {
            if (!yaVotaron.contiene(presentes.obtener(i))) {
                abstenciones++;
            }
        }
        System.out.println("Presentes al cierre: " + presentes.tamaño);
        System.out.println("Papeletas entregadas: " + yaVotaron.tamaño);
        System.out.println("Presentes sin papeleta: " + abstenciones);
        System.out.println("El dato sale de comparar presentes con el registro de quién votó: las papeletas son anónimas");
        System.out.println("Hay más papeletas que presentes votantes porque Elena votó y se marchó");
    }

    private static void proclamar(Pizarra pizarra) {
        System.out.println("Se recorren " + pizarra.cantidadCandidatos + " de "
                + pizarra.nombres.length + " posiciones: blanco y nulo no compiten");
        System.out.println(pizarra.proclamar());
    }
}
