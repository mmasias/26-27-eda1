public class PilaAnotaciones {
    private NodoAnotacion cima;

    public PilaAnotaciones() {
        this.cima = null;
    }

    public void apilar(String opcion) {
        NodoAnotacion nuevo = new NodoAnotacion(opcion);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    public String desapilar() {
        if (cima == null) {
            return null;
        }
        String opcion = cima.opcion;
        cima = cima.siguiente;
        return opcion;
    }

    public boolean estaVacia() {
        return cima == null;
    }
}
