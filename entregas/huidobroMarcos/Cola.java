public class Cola {

    private Pila pila;
    private Pila auxiliar;

    public Cola() {
        pila = new Pila();
        auxiliar = new Pila();
    }

    public void encolar(int valor) {
        pila.apilar(valor);
    }

    public int desencolar() {

        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }

        int primero = 0;

        while (!pila.estaVacia()) {

            int valor = pila.desapilar();

            if (pila.estaVacia()) {
                primero = valor;
            } else {
                auxiliar.apilar(valor);
            }
        }
        while (!auxiliar.estaVacia()) {
            pila.apilar(auxiliar.desapilar());
        }

        return primero;
    }

    public boolean estaVacia() {
        return pila.estaVacia() && auxiliar.estaVacia();
    }
}