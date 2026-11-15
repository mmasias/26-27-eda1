public class Cola {
    private Pila principal;

    public Cola() {
        this.principal = new Pila();
    }

    public void encolar(int valor) {
        if (principal.estaVacia()) {
            principal.apilar(valor);
        } else {
            Pila nueva = new Pila();
            nueva.apilar(valor);

            Pila auxiliar = new Pila();
            while (!principal.estaVacia()) {
                auxiliar.apilar(principal.desapilar());
            }

            while (!auxiliar.estaVacia()) {
                nueva.apilar(auxiliar.desapilar());
            }

            principal = nueva;
        }
    }

    public int desencolar() {
        assert !estaVacia() : "Precondición violada: no se puede desencolar de una cola vacía";
        return principal.desapilar();
    }

    public boolean estaVacia() {
        return principal.estaVacia();
    }
}
