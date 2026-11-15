class PilaEnCola {
    private Cola laCola;

    private int tamano;

    public PilaEnCola() {
        laCola = new Cola();
        tamano = 0;
    }

    public void apilar(int valor) {
        laCola.encolar(valor);
        tamano++;
    }
    public int desapilar() {
        assert !estaVacia();
        invertirCola();
        tamano--;
        return laCola.desencolar();
    }
    public boolean estaVacia() {
        return laCola.estaVacia();
    }

    private void invertirCola() {
        for (int i = 0; i < tamano - 1; i++) {
            laCola.encolar(laCola.desencolar());
        }
    }
}