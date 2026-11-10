class Cola {
    private Pila laPila;

    public Cola() {
        laPila = new Pila();
    }

    public void encolar(int valor) {
        laPila.apilar(valor);
    }
    public boolean estaVacia() {
        return laPila.estaVacia();
    }

    public int desencolar() {
        Pila pilaAux = darVueltPila(laPila);
        int desencolado = pilaAux.desapilar();
        laPila = darVueltPila(pilaAux);
        return desencolado;
    }

    private Pila darVueltPila(Pila pila) {
        Pila pilaInvertida = new Pila();
        while(!pila.estaVacia()) {
            pilaInvertida.apilar(pila.desapilar());
        }
        return pilaInvertida;
    }
}