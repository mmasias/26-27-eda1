package GarrastazuAsier.Reto004;


public class ColaVotos {
    private NodoVoto primero;
    private NodoVoto ultimo;

    private class NodoVoto {
        Voto dato;
        NodoVoto siguiente;

        public NodoVoto(Voto dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    public ColaVotos() {
        primero = null;
        ultimo = null;
    }

    public void insertar(Voto voto) {
        NodoVoto nuevo = new NodoVoto(voto);

        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        }
    }

    public Voto extraer() {
        if (primero == null) {
            return null;
        }

        Voto voto = primero.dato;
        primero = primero.siguiente;

        if (primero == null) {
            ultimo = null;
        }

        return voto;
    }

    public boolean estaVacia() {
        return primero == null;
    }
}