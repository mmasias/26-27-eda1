public class ColaInferior {

    private Nodo primero;
    private Nodo ultimo;

    public ColaInferior() {
        primero = null;
        ultimo = null;
    }

    public void encolar(int numero) {
        Nodo elemento = new Nodo(numero, null);

        if (primero == null) {
            primero = elemento;
        } else {
            ultimo.setSiguiente(elemento);
        }

        ultimo = elemento;
    }

    public int desencolar() {
        assert !estaVacia() : "No se puede extraer de una cola vacia";

        int numero = primero.getValor();
        primero = primero.getSiguiente();

        if (primero == null) {
            ultimo = null;
        }

        return numero;
    }

    public boolean estaVacia() {
        return primero == null;
    }
}