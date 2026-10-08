public class ListaEnlazada {

    private Nodo primero;

    public ListaEnlazada() {
        primero = null;
    }

    public void insertar(int dato) {
        Nodo nuevoNodo = new Nodo(dato);

        if (primero == null) {
            primero = nuevoNodo;
            return;
        }

        Nodo corriente = primero;

        while (corriente.siguiente != null) {
            corriente = corriente.siguiente;
        }

        corriente.siguiente = nuevoNodo;
    }

    public void eliminarRepetidos() {
        Nodo auxiliar = new Nodo(0);
        auxiliar.siguiente = primero;

        Nodo previo = auxiliar;
        Nodo corriente = primero;

        while (corriente != null) {

            if (corriente.siguiente != null && corriente.dato == corriente.siguiente.dato) {

                int valorDuplicado = corriente.dato;

                while (corriente != null && corriente.dato == valorDuplicado) {
                    corriente = corriente.siguiente;
                }

                previo.siguiente = corriente;

            } else {
                previo = corriente;
                corriente = corriente.siguiente;
            }
        }

        primero = auxiliar.siguiente;
    }

    public void eliminarRepetidosSinDummy() {

        while (primero != null && primero.siguiente != null
                && primero.dato == primero.siguiente.dato) {

            int valorDuplicado = primero.dato;

            while (primero != null && primero.dato == valorDuplicado) {
                primero = primero.siguiente;
            }
        }

        Nodo corriente = primero;

        while (corriente != null && corriente.siguiente != null) {

            if (corriente.siguiente.siguiente != null
                    && corriente.siguiente.dato == corriente.siguiente.siguiente.dato) {

                int valorDuplicado = corriente.siguiente.dato;

                while (corriente.siguiente != null
                        && corriente.siguiente.dato == valorDuplicado) {

                    corriente.siguiente = corriente.siguiente.siguiente;
                }

            } else {
                corriente = corriente.siguiente;
            }
        }
    }

    public static ListaEnlazada fusionar(ListaEnlazada primeraLista, ListaEnlazada segundaLista) {

        ListaEnlazada salida = new ListaEnlazada();

        Nodo auxiliar = new Nodo(0);
        Nodo cola = auxiliar;

        Nodo corrienteA = primeraLista.primero;
        Nodo corrienteB = segundaLista.primero;

        while (corrienteA != null && corrienteB != null) {

            if (corrienteA.dato <= corrienteB.dato) {
                cola.siguiente = corrienteA;
                corrienteA = corrienteA.siguiente;
            } else {
                cola.siguiente = corrienteB;
                corrienteB = corrienteB.siguiente;
            }

            cola = cola.siguiente;
        }

        if (corrienteA != null) {
            cola.siguiente = corrienteA;
        } else {
            cola.siguiente = corrienteB;
        }

        salida.primero = auxiliar.siguiente;

        primeraLista.primero = null;
        segundaLista.primero = null;

        return salida;
    }

    @Override
    public String toString() {

        if (primero == null) {
            return "null";
        }

        String salidaTexto = "";
        Nodo corriente = primero;

        while (corriente != null) {
            salidaTexto += corriente.dato;

            if (corriente.siguiente != null) {
                salidaTexto += " -> ";
            }

            corriente = corriente.siguiente;
        }

        return salidaTexto;
    }
}