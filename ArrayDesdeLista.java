public class ArrayDesdeLista {
    private final ListaEnlazada elementos;

    public ArrayDesdeLista(int longitud) {
        if (longitud < 0) {
            throw new IllegalArgumentException("La longitud no puede ser negativa");
        }
        elementos = new ListaEnlazada();
        for (int i = 0; i < longitud; i++) {
            elementos.agregarAlFinal(0);
        }
    }

    public int length() {
        return elementos.tamano();
    }

    private void comprobarIndice(int indice) {
        if (indice < 0 || indice >= elementos.tamano()) {
            throw new IndexOutOfBoundsException("Indice fuera de rango: " + indice);
        }
    }

    public int get(int indice) {
        comprobarIndice(indice);
        return elementos.obtener(indice);
    }

    public void set(int indice, int valor) {
        comprobarIndice(indice);
        elementos.establecer(indice, valor);
    }

    @Override
    public String toString() {
        return elementos.toString();
    }
}