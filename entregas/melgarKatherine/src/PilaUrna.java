class PilaUrna {

    private class NodoPapeleta {
        String voto;
        NodoPapeleta abajo;

        NodoPapeleta(String voto) {
            this.voto = voto;
            this.abajo = null;
        }
    }

    private NodoPapeleta cima;
    private int cantidad;

    public void apilar(String voto) {
        NodoPapeleta nueva = new NodoPapeleta(voto);
        nueva.abajo = cima;
        cima = nueva;
        cantidad++;
    }

    public String desapilar() {
        assert cima != null : "Error: no se puede sacar papeleta de urna vacia";
        String voto = cima.voto;
        cima = cima.abajo;
        cantidad--;
        return voto;
    }

    public boolean estaVacia() {
        return cima == null;
    }

    public int obtenerCantidad() {
        return cantidad;
    }
}