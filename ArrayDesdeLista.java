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
}