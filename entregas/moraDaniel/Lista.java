public class Lista {
    private Nodo primero = null;
    private int cantidad = 0;

    public void insertar(String valor) {
        Nodo nuevo = new Nodo(valor);
        if (primero == null) {
            primero = nuevo;
        } else {
            Nodo actual = primero;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        cantidad++;
    }

    public boolean contiene(String valor) {
        Nodo actual = primero;
        while (actual != null) {
            if (actual.valor.equals(valor)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public boolean eliminar(String valor) {
        Nodo dummy = new Nodo(null);
        dummy.siguiente = primero;
        Nodo anterior = dummy;
        while (anterior.siguiente != null) {
            if (anterior.siguiente.valor.equals(valor)) {
                anterior.siguiente = anterior.siguiente.siguiente;
                primero = dummy.siguiente;
                cantidad--;
                return true;
            }
            anterior = anterior.siguiente;
        }
        return false;
    }

    public int contar() {
        return cantidad;
    }

    public String[] volcar() {
        String[] valores = new String[cantidad];
        Nodo actual = primero;
        for (int posicion = 0; posicion < cantidad; posicion++) {
            valores[posicion] = actual.valor;
            actual = actual.siguiente;
        }
        return valores;
    }

    private static class Nodo {

        private String valor;
        private Nodo siguiente;

        Nodo(String valor) {
            this.valor = valor;
        }
    }
}
