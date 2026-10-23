public class Pila {
    private Nodo cima = null;

    public void apilar(String valor) {
        Nodo nuevo = new Nodo(valor);
        nuevo.debajo = cima;
        cima = nuevo;
    }

    public String desapilar() {
        if (estaVacia()) {
            return null;
        }
        String valor = cima.valor;
        cima = cima.debajo;
        return valor;
    }

    public boolean estaVacia() {
        return cima == null;
    }

    private static class Nodo {

        private String valor;
        private Nodo debajo;

        Nodo(String valor) {
            this.valor = valor;
        }
    }
}
