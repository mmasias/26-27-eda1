package GarrastazuAsier.Reto004;
public class Historial {

    private NodoHistorial cima;

    private class NodoHistorial {
        Voto voto;
        NodoHistorial siguiente;

        public NodoHistorial(Voto voto) {
            this.voto = voto;
            this.siguiente = null;
        }
    }

    public Historial() {
        cima = null;
    }

    public void apilar(Voto voto) {
        NodoHistorial nuevo = new NodoHistorial(voto);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    public Voto desapilar() {
        if (cima == null) {
            return null;
        }

        Voto voto = cima.voto;
        cima = cima.siguiente;
        return voto;
    }

    public boolean estaVacio() {
        return cima == null;
    }
}
