public class Pila {

    private Nodo cima;
    private int tamano;

    public void apilar(String dato) {
        Nodo nuevo = new Nodo(dato, this.cima);
        this.cima = nuevo;
        this.tamano++;
    }

    public String desapilar() {
        if (this.estaVacia()) {
            return null;
        }
        String dato = this.cima.obtenerDato();
        this.cima = this.cima.obtenerSiguiente();
        this.tamano--;
        return dato;
    }

    public boolean estaVacia() {
        return this.cima == null;
    }

    public int tamano() {
        return this.tamano;
    }
}
