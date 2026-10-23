package entregas.garciaJacobo;

public class ListaCandidatos {
    private Node primero;
    private Console console;

    public ListaCandidatos() {
        primero = null;
        console = new Console();
    }

    public void insertarInicio(String value) {
        Node nuevo = new Node(value);
        nuevo.setNext(primero);
        primero = nuevo;

    }

    public boolean eliminar(String value) {
        if (primero == null) {
            return false;
        }

        if (primero.getValue().equals(value)) {
            primero = primero.getNext();
            return true;
        }

        Node anterior = primero;
        Node actual = primero.getNext();

        while (actual != null) {
            if (actual.getValue().equals(value)) {
                anterior.setNext(actual.getNext());
                return true;
            }

            anterior = actual;
            actual = actual.getNext();
        }

        return false;
    }

    public boolean contiene(String value) {
        Node actual = primero;

        while (actual != null) {
            if (actual.getValue().equals(value)) {
                return true;
            }

            actual = actual.getNext();
        }

        return false;
    }

    public void mostrar() {
        Node actual = primero;
        while (actual != null) {
            actual.getValue();
            console.writeln("-" + actual.getValue());
            actual = actual.getNext();

        }
    }

    public int tamaño() {
        Node actual = primero;
        int contador = 0;
        while (actual != null) {
            actual = actual.getNext();
            contador++;
        }
        return contador;
    }

    public String[] convertirAArray() {
        String[] candidatos = new String[tamaño()];

        int contador = 0;
        Node actual = primero;

        while (actual != null) {
            candidatos[contador] = actual.getValue();
            actual = actual.getNext();
            contador++;
        }
        return candidatos;
    }

    public void mostrarArray(String[] candidatos) {
        for (int i = 0; i < candidatos.length; i++) {
            console.writeln("-" + candidatos[i]);
        }
    }

    public String[] obtenerOpcionesVotacion(String[] candidatos) {
        String opciones[] = new String[candidatos.length + 2];
        for (int i = 0; i < candidatos.length; i++) {
            opciones[i] = candidatos[i];
        }
        opciones[candidatos.length] = "Blanco";
        opciones[candidatos.length + 1] = "Nulo";

        return opciones;
    }

    public int[] crearRecuento(String[] opciones) {
        int[] recuento = new int[opciones.length];
        return recuento;
    }

    public void mostrarRecuento(int[] recuento) {
        for (int i = 0; i < recuento.length; i++) {
            console.writeln("[" + recuento[i] + "]");
        }
    }

}
