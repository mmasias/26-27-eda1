class MiArraySimulado {

    private int indiceMaximo;
    private Nodo cabeza;

    public MiArraySimulado(int indiceMaximo) {
        this.indiceMaximo = indiceMaximo;
        cabeza = new Nodo(0);
        Nodo actual = cabeza;
        for (int i = 1; i <= indiceMaximo; i++) {
            actual.siguiente = new Nodo(0);
            actual = actual.siguiente;
        }
    }

    public int accesoAindice(int indice) {
        Nodo actual = cabeza;
        if (!estaFueraDelIndice(indice)) {
            for (int i = 0; i < indice; i++) {
                actual = actual.siguiente;

            }
            return actual.valor;
        }
        return -1;
    }

    public void asignarValorAIndice(int indice, int valor) {
        if (!estaFueraDelIndice(indice)) {
            Nodo actual = cabeza;   
                for (int i = 0; i < indice; i++) {
                    actual = actual.siguiente;
                }
                actual.valor = valor;
            
        }
    }

    public boolean estaFueraDelIndice(int indice) {
        return indice > indiceMaximo || indice < 0;
    }

}