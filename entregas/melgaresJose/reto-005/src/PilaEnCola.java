class PilaEnCola {
    private Cola laCola;

    public PilaEnCola() {
        laCola = new Cola();
    }

    public void apilar(int valor) {
        laCola.encolar(valor);
    }
    public int desapilar() {

    }
    public boolean estaVacia() {
        return laCola.estaVacia();
    }
}