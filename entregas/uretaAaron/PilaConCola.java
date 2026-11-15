public class PilaConCola {
    private Cola cPrincipal;
    private Cola cAuxiliar;

    public PilaConCola() {
        cPrincipal = new Cola();
        cAuxiliar = new Cola();
    }

    public void apilar(int valor) {
        cAuxiliar.encolar(valor);

        while (!cPrincipal.estaVacia()) {
            cAuxiliar.encolar(cPrincipal.desencolar());
        }

        Cola temporal = cPrincipal;
        cPrincipal = cAuxiliar;
        cAuxiliar = temporal;
    }

    public int desapilar() {
        assert !estaVacia();
        return cPrincipal.desencolar();
    }

    public boolean estaVacia() {
        return cPrincipal.estaVacia();
    }
}