public class PilaEnCola {
    private Cola laCola;
    private int elementos;

    public PilaEnCola() {
        this.laCola = new Cola();
        this.elementos = 0;
    }

    public void apilar(int valor) {
        this.laCola.encolar(valor);
        for (int i = 0; i < this.elementos; i++) {
            this.laCola.encolar(this.laCola.desencolar());
        }
        this.elementos++;
    }

    public int desapilar() {
        assert !this.estaVacia();
        this.elementos--;
        return this.laCola.desencolar();
    }

    public boolean estaVacia() {
        return this.laCola.estaVacia();
    }
}