public class Cola {
    private Pila principal;
    private Pila auxiliar;
    public Cola () {
        principal = new Pila();
        auxiliar = new Pila ();
    }
    public void encolar (int valor) {
        principal.apilar (valor) ;
    }
    public int desencolar () {
        assert !estaVacia();
        while (!principal.estaVacia()) {
            auxiliar.apilar (principal.desapilar ());
        }
        int primero =auxiliar.desapilar ();
        while (!auxiliar.estaVacia()) {
            principal.apilar (auxiliar.desapilar());
        }
        return primero;
    }
    public boolean estaVacia () {
        return principal.estaVacia();
    }
}