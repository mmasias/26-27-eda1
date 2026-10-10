import java.util.Arrays;

public class ListaConArray {

    private Object[] elementos;
    private int tamaño;
    public ListaConArray() {
        elementos = new Object[5];
        tamaño = 0;
    }
    public int tamaño() {
        return tamaño;
    }
    public boolean estaVacia() {
        return tamaño == 0;
    }
    private void comprobarIndice(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException(
                "Índice fuera de rango: " + indice
            );
        }
    }
    private void ampliarCapacidad() {
        elementos = Arrays.copyOf(
            elementos,
            elementos.length * 2
        );
    }
    public void añadir(Object elemento) {
        if (tamaño == elementos.length) {
            ampliarCapacidad();
        }

        elementos[tamaño] = elemento;
        tamaño++;
    }
    public void insertar(int indice, Object elemento) {
        if (indice < 0 || indice > tamaño) {
            throw new IndexOutOfBoundsException(
                "Índice fuera de rango: " + indice
            );
        }

        if (tamaño == elementos.length) {
            ampliarCapacidad();
        }
        for (int i = tamaño; i > indice; i--) {
            elementos[i] = elementos[i - 1];
        }

        elementos[indice] = elemento;
        tamaño++;
    }
    public Object obtener(int indice) {
        comprobarIndice(indice);
        return elementos[indice];
    }
    public void modificar(int indice, Object elemento) {
        comprobarIndice(indice);
        elementos[indice] = elemento;
    }
    public Object eliminar(int indice) {
        comprobarIndice(indice);

        Object eliminado = elementos[indice];
        for (int i = indice; i < tamaño - 1; i++) {
            elementos[i] = elementos[i + 1];
        }

        tamaño--;
        elementos[tamaño] = null;

        return eliminado;
    }
    public int buscar(Object elemento) {
        for (int i = 0; i < tamaño; i++) {
            if (java.util.Objects.equals(elementos[i], elemento)) {
                return i;
            }
        }

        return -1;
    }
    public void limpiar() {
        Arrays.fill(elementos, 0, tamaño, null);
        tamaño = 0;
    }
    @Override
    public String toString() {
        return Arrays.toString(
            Arrays.copyOf(elementos, tamaño)
        );
    }

    public static void main(String[] args) {

        ListaConArray lista = new ListaConArray();

    
        lista.añadir(10);
        lista.añadir(20);
        lista.añadir(30);

        System.out.println("Lista inicial: " + lista);

        lista.insertar(1, 15);
        System.out.println("Después de insertar: " + lista);
        System.out.println("Elemento en índice 2: "
            + lista.obtener(2));
        lista.modificar(0, 5);
        System.out.println("Después de modificar: " + lista);
        System.out.println("Posición del 30: " + lista.buscar(30));
        System.out.println("Elemento eliminado: "
            + lista.eliminar(1));

        System.out.println("Después de eliminar: " + lista);
        System.out.println("Tamaño: " + lista.tamaño());

        lista.limpiar();

        System.out.println("Después de limpiar: " + lista);
        System.out.println("¿Está vacía? " + lista.estaVacia());
    }
}