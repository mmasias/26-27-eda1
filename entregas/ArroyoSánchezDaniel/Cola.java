public class Cola {
    int tope;
    Pila Inicial = new Pila();
    Pila AlReves = new Pila();

    public Cola() {
        this.tope = 0;
    }

    public void encolar(int valor) {
        Inicial.apilar(valor);
        tope++;
    }

    public boolean estaVacio() {
        if (this.tope == 0) {
            return true;
        }
        return false;
    }

    public int desencolar() {
        assert !Inicial.estaVacio();
        do {
            int valor = Inicial.desapilar();
            AlReves.apilar(valor);
        } while (!Inicial.estaVacio());
        int valorDesencolar = AlReves.desapilar();
        do {
            int valor = AlReves.desapilar();
            Inicial.apilar(valor);
        } while (!AlReves.estaVacio());
        tope--;
        return valorDesencolar;
    }

}