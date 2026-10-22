public class ListaSimulada {
    private int[] datos;
    private int n;

    public ListaSimulada(int capacidadInicial) {
        if (capacidadInicial < 1) {
            throw new IllegalArgumentException("La capacidad inicial debe ser al menos 1");
        }
        datos = new int[capacidadInicial];
        n = 0;
    }

    public ListaSimulada() {
        this(4);
    }

    private void crecer() {
        int[] nuevo = new int[datos.length * 2];
        for (int i = 0; i < n; i++) {
            nuevo[i] = datos[i];
        }
        datos = nuevo;
    }

    public void agregar(int valor) {
        insertar(n, valor);
    }

    public void insertar(int pos, int valor) {
        if (pos < 0 || pos > n) {
            throw new IndexOutOfBoundsException("Posicion fuera de rango: " + pos);
        }
        if (n == datos.length) {
            crecer();
        }
        for (int i = n; i > pos; i--) {
            datos[i] = datos[i - 1];
        }
        datos[pos] = valor;
        n++;
    }

    public int eliminar(int pos) {
        if (pos < 0 || pos >= n) {
            throw new IndexOutOfBoundsException("Posicion fuera de rango: " + pos);
        }
        int eliminado = datos[pos];
        for (int i = pos; i < n - 1; i++) {
            datos[i] = datos[i + 1];
        }
        n--;
        return eliminado;
    }

    public int get(int pos) {
        if (pos < 0 || pos >= n) {
            throw new IndexOutOfBoundsException("Posicion fuera de rango: " + pos);
        }
        return datos[pos];
    }

    public void set(int pos, int valor) {
        if (pos < 0 || pos >= n) {
            throw new IndexOutOfBoundsException("Posicion fuera de rango: " + pos);
        }
        datos[pos] = valor;
    }

    public int size() {
        return n;
    }

    public int capacidad() {
        return datos.length;
    }

    public void mostrar() {
        System.out.print("[");
        for (int i = 0; i < n; i++) {
            System.out.print(datos[i]);
            if (i < n - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}