class MiArraySimulado {

    private int indiceMaximo;
    private int valor;
    private Nodo cabeza;

    public MiArraySimulado(int indiceMaximo, int valor) {
        this.indiceMaximo = indiceMaximo;
        this.valor = valor;
    }

    public int accesoAindice(int indice) {
        if (!estaFueraDelIndice(indice)) {
            for (int i = 0; i >= indice; i++) {
                return valor;
            }
        }
        return -1;
    }

    public boolean estaFueraDelIndice(int indice) {
        if (indice > indiceMaximo) {
            return true;
        } else {
            return false;
        }
    }

}