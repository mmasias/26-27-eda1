public class PilaDeCola {
    private Cola entrada;
    private Cola salida;

    public PilaDeCola() {
        this.entrada = new Cola();
        this.salida = new Cola();
    }

    public void apilar(int valor) {
        entrada.encolar(valor);
    }

    public int desapilar() {
        assert !estaVacia() : "Error: La pila esta vacia";

        int candidatoAUltimo = entrada.desencolar();

        while (!entrada.estaVacia()) {
            salida.encolar(candidatoAUltimo);
            
            candidatoAUltimo = entrada.desencolar();
        }

        Cola colaTemporal = entrada;
        entrada = salida;
        salida = colaTemporal;

        return candidatoAUltimo;
    }

    public boolean estaVacia() {
        return entrada.estaVacia();
    }
}